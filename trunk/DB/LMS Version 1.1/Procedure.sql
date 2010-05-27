use LMSDB;

if object_id('sp_add_n_log_point') is not null
	drop proc sp_add_n_log_point
go

create procedure sp_add_n_log_point(@CardId varchar(16),@TaskID int,@Amount int,@InvoiceId varchar(16),@Point int,@MID varchar(15),@TID varchar(8),@PoSCC varchar(2),@Result int output)
as
begin	
	declare @Customer int;
	declare @CurrentPoint int;
	begin transaction;	
	select @Customer = jCustomer.JPOS_CustomerID,@CurrentPoint = jCustomer.JPOS_CurrentPoint from JPOS_Customer jCustomer,JPOS_Card jCard where jCard.JPOS_CardId = @CardId and jCard.JPOS_CustomerID = jCustomer.JPOS_CustomerID;
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
	
	insert into JPOS_Log(JPOS_Date,JPOS_Task,JPOS_CardID,JPOS_InvoiceId,JPOS_Amount,JPOS_PointGain,JPOS_PointLoss,JPOS_MID,JPOS_TID,JPOS_PoSCC_ID) 
	values (getdate(),@TaskID,@CardId,@InvoiceId,@Amount,@Point,0,@MID,@TID,@PoSCC)				
	if  (@@rowcount = 0)
	begin
		set @Result = 0;
		rollback transaction;
	end
	set @Result = @CurrentPoint + @Point;
	commit transaction;
end
go

--=========================================================================

if object_id('sp_balance_inquiry_log') is not null
	drop proc sp_balance_inquiry_log
go

create procedure sp_balance_inquiry_log(@CardId varchar(16),@TaskID int,@Amount int,@MID varchar(15),@TID varchar(8),@PoSCC varchar(2))
as
begin
	insert into JPOS_Log(JPOS_Date,JPOS_Task,JPOS_CardID,JPOS_InvoiceId,JPOS_Amount,JPOS_PointGain,JPOS_PointLoss,JPOS_MID,JPOS_TID,JPOS_PoSCC_ID) 
	values (getdate(),@TaskID,@CardId,NULL,@Amount,0,0,@MID,@TID,@PoSCC)
end
go

--=========================================================================

if object_id('sp_void_log') is not null
	drop proc sp_void_log
go

create procedure sp_void_log(@CardId varchar(16),@TaskID int,@Amount int,@InvoiceId varchar(16),@MID varchar(15),@TID varchar(8),@PoSCC varchar(2))
as
begin
	insert into JPOS_Log(JPOS_Date,JPOS_Task,JPOS_CardID,JPOS_InvoiceId,JPOS_Amount,JPOS_PointGain,JPOS_PointLoss,JPOS_MID,JPOS_TID,JPOS_PoSCC_ID) 
	values (getdate(),@TaskID,@CardId,@InvoiceId,@Amount,0,0,@MID,@TID,@PoSCC)
end
go

--=========================================================================

if object_id('sp_reload_log') is not null
	drop proc sp_reload_log
go

create procedure sp_reload_log(@CardId varchar(16),@TaskID int,@Amount int,@InvoiceId varchar(16),@MID varchar(15),@TID varchar(8),@PoSCC varchar(2))
as
begin
	insert into JPOS_Log(JPOS_Date,JPOS_Task,JPOS_CardID,JPOS_InvoiceId,JPOS_Amount,JPOS_PointGain,JPOS_PointLoss,JPOS_MID,JPOS_TID,JPOS_PoSCC_ID) 
	values (getdate(),@TaskID,@CardId,@InvoiceId,@Amount,0,0,@MID,@TID,@PoSCC)
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

if object_id('sp_redeem_Card') is not null
	drop proc sp_redeem_Card
go

create procedure sp_redeem_Card (@CardID varchar(16),@Amount int,@result int output)
as
begin
	declare @card_amount int;
	begin transaction;
	set @card_amount = dbo.fn_get_amount_card(@CardID);
	if(@card_amount < @Amount)
	begin
		set @result = -1;
		return;
	end
	set @card_amount = @card_amount - @Amount;
	update JPOS_Card Set JPOS_Monetary = @card_amount where JPOS_CardId = @CardID;
	set @result = @card_amount;
	commit transaction;
end
go

-------------------------------------------------------------------------------------------------------------------------------

if object_id('sp_reload_Card') is not null
	drop proc sp_reload_Card
go

create procedure sp_reload_Card (@CardID varchar(16),@Amount int,@result int output)
as
begin
	declare @card_amount int;
	begin transaction;
	set @card_amount = dbo.fn_get_amount_card(@CardID);
	set @card_amount = @card_amount + @Amount;
	update JPOS_Card Set JPOS_Monetary = @card_amount where JPOS_CardId = @CardID;
	set @result = @card_amount;
	commit transaction;
end
go

-------------------------------------------------------------------------------------------------------------------------------

if object_id('sp_void_card') is not null
	drop proc sp_void_card;
go

create proc sp_void_card(@cardId varchar(16),@invoiceId varchar(16),@amount int,@result int output)
as
begin
	declare @task int ;
	set @result = 0;
	select @task = JPOS_Task from JPOS_Log where JPOS_CardId = @cardId and JPOS_InvoiceId = @invoiceId and JPOS_Task <> 3 ;
	if(@task = 1) --The prior transaction is redeem.
	begin
		exec sp_reload_Card @cardId , @amount , @result output;
	end
	else if (@task = 2) -- The prior transaction is reload.
	begin
		exec sp_redeem_Card @cardId, @amount, @result output;
	end
end
go
