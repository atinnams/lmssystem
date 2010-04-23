use LMSDB;

if object_id('sp_add_n_log_point') is not null
	drop proc sp_add_n_log_point
go

create procedure sp_add_n_log_point(@CardId varchar(16),@TaskID int,@Point int,@MID varchar(15),@TID varchar(8),@PoSCC varchar(2),@Result int output)
as
begin	
	declare @Customer int;
	begin transaction;	
	select @Customer = jCustomer.JPOS_CustomerID from JPOS_Customer jCustomer,JPOS_Card jCard where jCard.JPOS_CardId = @CardId and jCard.JPOS_CustomerID = jCustomer.JPOS_CustomerID;
		if  (@@rowcount = 0)
		begin
			set @Result = 0;
			rollback transaction;
		end

	update JPOS_Customer set JPOS_CurrentPoint = JPOS_CurrentPoint + @Point where JPOS_CustomerID = @Customer					
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	insert into JPOS_Log(JPOS_Date,JPOS_Task,JPOS_CardID,JPOS_PointGain,JPOS_PointLoss,JPOS_MID,JPOS_TID,JPOS_PoSCC_ID) 
	values (getdate(),@TaskID,@CardId,@Point,0,@MID,@TID,@PoSCC)				
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	set @Result = @@identity;
	commit transaction;
end
go

--=========================================================================

if object_id('sp_add_log_event') is not null
	drop proc sp_add_log_event
go

create proc sp_add_log_event(@LogId int,@EventId int,@PointGain int,@PointLoss int)
as
begin
	insert into JPOS_Log_Event values(@LogID,@EventId,@PointGain,@PointLoss);
end
go

--=========================================================================

if object_id('sp_update_point') is not null
	drop proc sp_update_point
go

create proc sp_update_point(@CardId varchar(16),@Point int,@Result int out)
as
begin
	declare @Customer int;
	begin transaction;	
	select @Customer = jCustomer.JPOS_CustomerID from JPOS_Customer jCustomer,JPOS_Card jCard where jCard.JPOS_CardId = @CardId and jCard.JPOS_CustomerID = jCustomer.JPOS_CustomerID;
		if  (@@rowcount = 0)
		begin
			set @Result = 0;
			rollback transaction;
		end

	update JPOS_Customer set JPOS_CurrentPoint = JPOS_CurrentPoint + @Point where JPOS_CustomerID = @Customer					
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	set @Result = 1;
	commit transaction;
end
go

--===============================================================================

if object_id('sp_get_n_log_event_point') is not null
	drop proc sp_get_n_log_event_point;
go

create proc sp_get_n_log_event_point(@CardId varchar(16),@money float,@LogId int,@Point int out)
as
begin
	declare @RuleID int;
	declare @EventID int;
	declare @TempPoint int;
	declare @C cursor;
	declare @Result int;
	set @Point = 0;
	set @TempPoint = 0;
	set @C = cursor for select JPOS_EventID,JPOS_RuleID
						from JPOS_Event
						Where getdate() > JPOS_DateStart
						and getdate() < JPOS_DateEnd
	open @C
	fetch next from @C into @EventID,@RuleID
	while  @@fetch_status = 0
	begin
		set @TempPoint = dbo.fn_get_rule_point(@money,@RuleID);
		exec sp_add_log_event @LogID,@EventID,@TempPoint,0;
		set @Point = @Point + @TempPoint;
		fetch next from @C into @EventID,@RuleID
	end
	close @C
	deallocate @C

	exec sp_update_point @CardId, @Point, @Result output;
end

-------------------------------------------------------------------------------------------------------------------------------

if object_id('sp_Sub_Point') is not null
	drop proc sp_Sub_Point
go

create procedure sp_Sub_Point(@CardId varchar(16),@TaskID int,@Point int,@MID varchar(15),@TID varchar(8),@PoSCC varchar(2),@Result int output)
as
begin	
	declare @Customer int;
	begin transaction;	
	select @Customer = JPOS_IDCustomer from JPOS_Customer where JPOS_CardId = @CardId
		if  (@@rowcount = 0)
		begin
			set @Result = 0;
			rollback transaction;
		end

	update JPOS_Customer set JPOS_CurrentPoint = JPOS_CurrentPoint - @Point where JPOS_CardId = @CardId
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	insert into JPOS_Log(JPOS_Date,JPOS_Task,JPOS_Customer,JPOS_PointGain,JPOS_PointLoss,JPOS_MID,JPOS_TID,JPOS_PoSCC_ID) 
	values (getdate(),@TaskID,@Customer,0,@Point,@MID,@TID,@PoSCC)				
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end

	set @Result = @@identity;
	commit transaction;
end
go

-------------------------------------------------------------------------------------------------------------------------------

if object_id('sp_card_activate') is not null
	drop proc sp_card_activate
go

create procedure sp_card_activate(@cardid varchar(16))
as
begin
	update JPOS_Card set JPOS_IsActivate = '1' where JPOS_CardId = @cardid;
end

-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_redemption') is not null
	drop proc sp_redemption
go

create proc sp_redemption(@CardId varchar(16),@TaskID int,@giftType int,@MID varchar(15),@TID varchar(8),@PoSCC varchar(2),@Result int output)
as
begin
	declare @Customer int;
	declare @giftPoint int;
	declare @id_log int;
	begin transaction;	
	select @Customer = JPOS_IDCustomer from JPOS_Customer where JPOS_CardId = @CardId
		if  (@@rowcount = 0)
		begin
			set @Result = 0;
			rollback transaction;
		end
	
	set @giftPoint = dbo.fn_get_gift_point(@giftType);
	update JPOS_Customer set JPOS_CurrentPoint = JPOS_CurrentPoint - @giftPoint where JPOS_CardId = @CardId
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	insert into JPOS_Log(JPOS_Date,JPOS_Task,JPOS_Customer,JPOS_PointGain,JPOS_PointLoss,JPOS_MID,JPOS_TID,JPOS_PoSCC_ID) 
	values (getdate(),@TaskID,@Customer,0,@giftPoint,@MID,@TID,@PoSCC)				
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	set @id_log = @@identity
	insert into JPOS_Log_Exchange(JPOS_IDLog,JPOS_Gift) values(@id_log,@giftType)
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	set @Result = 1;
	commit transaction;
end


