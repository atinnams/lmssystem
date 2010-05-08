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
go
-------------------------------------------------------------------------------------------------------------------------------

if object_id('sp_Sub_Point') is not null
	drop proc sp_Sub_Point;
go

create procedure sp_Sub_Point(@CardId varchar(16),@TaskID int,@Point int,@MID varchar(15),@TID varchar(8),@PoSCC varchar(2),@Result int output)
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

	update JPOS_Customer set JPOS_CurrentPoint = JPOS_CurrentPoint - @Point where JPOS_CustomerID = @Customer
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	insert into JPOS_Log(JPOS_Date,JPOS_Task,JPOS_CardID,JPOS_PointGain,JPOS_PointLoss,JPOS_MID,JPOS_TID,JPOS_PoSCC_ID) 
	values (getdate(),@TaskID,@CardId,0,@Point,@MID,@TID,@PoSCC)							
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
/* 
Modify by Khuyen Nguyen 
Day Modified : 24/4/2010
*/
create procedure sp_card_activate(@cardid varchar(16))
as
begin
	update JPOS_Card set JPOS_Status = '2' where JPOS_CardId = @cardid;
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_redemption') is not null
	drop proc sp_redemption
go

create proc sp_redemption(@CardId varchar(16),@TaskID int,@giftPoint int,@MID varchar(15),@TID varchar(8),@PoSCC varchar(2),@Result int output)
as
begin
	declare @Customer int;
	declare @giftType int;
	declare @id_log int;
	begin transaction;	
	select @Customer = jCustomer.JPOS_CustomerID from JPOS_Customer jCustomer,JPOS_Card jCard where jCard.JPOS_CardId = @CardId and jCard.JPOS_CustomerID = jCustomer.JPOS_CustomerID;
		if  (@@rowcount = 0)
		begin
			set @Result = 0;
			rollback transaction;
		end
	
	update JPOS_Customer set JPOS_CurrentPoint = JPOS_CurrentPoint - @giftPoint where JPOS_CustomerID = @Customer
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	insert into JPOS_Log(JPOS_Date,JPOS_Task,JPOS_CardID,JPOS_PointGain,JPOS_PointLoss,JPOS_MID,JPOS_TID,JPOS_PoSCC_ID) 
	values (getdate(),@TaskID,@CardId,0,@giftPoint,@MID,@TID,@PoSCC)							

	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	set @id_log = @@identity
	
	set @giftType = dbo.fn_get_gift_id(@giftPoint);
	insert into JPOS_Log_Exchange(JPOS_LogID,JPOS_Gift) values(@id_log,@giftType)
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	set @Result = 1;
	commit transaction;
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Login') is not null
	drop proc sp_Login
go
create procedure sp_Login(@Username nvarchar(50),@Password nvarchar(50),@Result int Output)
as
begin
	select * from JPOS_Admin where JPOS_Username = @Username and JPOS_Password = @Password
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	update JPOS_Admin set 
	JPOS_LoginCount = JPOS_LoginCount + 1,
	JPOS_LastLogin = getdate()
	where JPOS_Username = @Username and JPOS_Password = @Password
	
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end

	set @Result = 1;
	commit transaction;
end