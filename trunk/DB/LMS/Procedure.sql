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


if object_id('sp_card_activate') is not null
	drop proc sp_card_activate
go

create procedure sp_card_activate(@cardid varchar(16))
as
begin
	update JPOS_Card set JPOS_IsActivate = '1' where JPOS_CardId = @cardid;
end

/*--test

exec sp_card_activate '8765432112345678';
select * from JPOS_card;
update JPOS_Card set JPOS_IsActivate = '0' where JPOS_CardId = '8765432112345678';

*/



