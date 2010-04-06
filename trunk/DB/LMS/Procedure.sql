use LMSDB;

if object_id('sp_Add_Point') is not null
	drop proc sp_Add_Point
go

create procedure sp_Add_Point(@CardId varchar(16),@TaskID int,@Point int,@MID varchar(15),@TID varchar(8),@PoSCC varchar(2),@Result int output)
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

	update JPOS_Customer set JPOS_CurrentPoint = JPOS_CurrentPoint + @Point where JPOS_CardId = @CardId					
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	
	insert into JPOS_Log(JPOS_Date,JPOS_Task,JPOS_Customer,JPOS_PointGain,JPOS_PointLoss,JPOS_MID,JPOS_TID,JPOS_PoSCC_ID) 
	values (getdate(),@TaskID,@Customer,@Point,0,@MID,@TID,@PoSCC)				
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

	set @Result = 1;
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


