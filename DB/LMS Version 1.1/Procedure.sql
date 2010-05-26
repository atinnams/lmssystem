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
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Search_Customer') is not null
	drop proc sp_Search_Customer
go
create procedure sp_Search_Customer(@CustomerID int,@FirstName nvarchar(50),@LastName nvarchar(50), @Address nvarchar(200),@Email varchar(100), @DateJoin datetime, @BirthDay datetime, @Gender bit, @Favorite nvarchar,@CurrentPoint int)
as
begin
	select * from JPOS_Customer
	where 
	(JPOS_CustomerID = @CustomerID or @CustomerID = -1 ) and
	(JPOS_FirstName like '%'+@FirstName+'%' or JPOS_FirstName is NULL or @FirstName = '') and
	(JPOS_LastName like ('%'+@LastName+'%') or JPOS_LastName is NULL or @LastName = '')and
	(JPOS_Address like '%'+@Address+'%' or JPOS_Address is NULL or @Address = '') and
	(JPOS_Email like '%'+@Email+'%' or JPOS_Email is NULL or @Email = '') and
	(JPOS_DateJoin = @DateJoin or JPOS_DateJoin is NULL or @DateJoin = '') and
	(JPOS_BirthDay = @BirthDay or JPOS_BirthDay is NULL or @BirthDay = '') and
	(JPOS_Gender = @Gender or JPOS_Gender is NULL or @Gender is NULL) and
	(JPOS_Favorite like '%'+@Favorite+'%' or JPOS_Favorite is NULL or @Favorite = '') and
	(JPOS_CurrentPoint = @CurrentPoint or @CurrentPoint = -1)
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Transaction_Detail') is not null
	drop proc sp_Transaction_Detail
go
create procedure sp_Transaction_Detail(@CustomerID int)
as
begin
	Select JPOS_Log.JPOS_LogID,JPOS_Log.JPOS_Date,JPOS_Task.JPOS_TaskName,JPOS_Log.JPOS_CardID,JPOS_Customer.JPOS_CustomerID,JPOS_LOG.JPOS_PointGain,JPOS_LOG.JPOS_PointLoss,JPOS_PoSCC.JPOS_PoSCC_Name,JPOS_Terminal.JPOS_TID,JPOS_Merchant.JPOS_MerchantName,JPOS_Merchant.JPOS_Address,JPOS_Gift.JPOS_GiftName
	from 
	JPOS_Log left join JPOS_Log_Exchange on JPOS_Log.JPOS_LogID = JPOS_Log_Exchange.JPOS_LogID
	join jpos_card on JPOS_Log.JPOS_CardID = JPOS_Card.JPOS_CardID
	join JPOS_Customer on JPOS_Card.JPOS_CustomerID = JPOS_Customer.JPOS_CustomerID 
	left join JPOS_Gift on JPOS_Log_Exchange.JPOS_Gift = JPOS_Gift.JPOS_GiftID
	join JPOS_Task on JPOS_Log.JPOS_Task = JPOS_Task.JPOS_TaskID	
	join JPOS_Terminal on JPOS_Log.JPOS_TID = JPOS_Terminal.JPOS_TID and JPOS_Log.JPOS_MID = JPOS_Terminal.JPOS_MID
	join JPOS_Merchant on JPOS_Merchant.JPOS_MID = JPOS_Log.JPOS_MID
	join JPOS_PoSCC on JPOS_Log.JPOS_PoSCC_ID = JPOS_PoSCC.JPOS_PoSCC_ID
	where JPOS_Customer.JPOS_CustomerID = @CustomerID	
	order by JPOS_Log.JPOS_Date DESC
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Customer_Infor') is not null
	drop proc sp_Customer_Infor
go
create procedure sp_Customer_Infor(@CustomerID int)
as
begin
	select * from JPOS_Customer	left join JPOS_Status on JPOS_Status = JPOS_StatusID
	where JPOS_CustomerID = @CustomerID	and JPOS_StatusName not like '%Delete%'
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Customer_Report') is not null
	drop proc sp_Customer_Report
go
create procedure sp_Customer_Report
as
begin
	select * from JPOS_Customer		
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Card_Report') is not null
	drop proc sp_Card_Report
go
create procedure sp_Card_Report
as
begin
	select * from JPOS_Card left join JPOS_Status on JPOS_Card.JPOS_Status = JPOS_Status.JPOS_StatusID 
	where JPOS_Status.JPOS_TableName = 'JPOS_Card' and JPOS_Status.JPOS_StatusName <> 'Deleted Card'
end 
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Terminal_Report') is not null
	drop proc sp_Terminal_Report
go
create procedure sp_Terminal_Report
as
begin
	select JPOS_TID,JPOS_PIN,JPOS_Status,JPOS_StatusName,JPOS_ActivateCode,JPOS_RetryLimit,JPOS_Terminal.JPOS_MID,JPOS_MerchantName,JPOS_Address from JPOS_Terminal left join JPOS_Merchant on JPOS_Terminal.JPOS_MID = JPOS_Merchant.JPOS_MID left join JPOS_Status on JPOS_Terminal.JPOS_Status = JPOS_Status.JPOS_StatusID
end
go 
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Transaction_Report') is not null
	drop proc sp_Transaction_Report
go
create procedure sp_Transaction_Report
as
begin
	Select JPOS_Log.JPOS_LogID,JPOS_Log.JPOS_Date,JPOS_Task.JPOS_TaskName,JPOS_Log.JPOS_CardID,JPOS_Customer.JPOS_CustomerID,JPOS_LOG.JPOS_PointGain,JPOS_LOG.JPOS_PointLoss,JPOS_PoSCC.JPOS_PoSCC_Name,JPOS_Terminal.JPOS_TID,JPOS_Merchant.JPOS_MerchantName,JPOS_Merchant.JPOS_Address,JPOS_Gift.JPOS_GiftName
	from 
	JPOS_Log left join JPOS_Log_Exchange on JPOS_Log.JPOS_LogID = JPOS_Log_Exchange.JPOS_LogID
	join jpos_card on JPOS_Log.JPOS_CardID = JPOS_Card.JPOS_CardID
	join JPOS_Customer on JPOS_Card.JPOS_CustomerID = JPOS_Customer.JPOS_CustomerID 
	left join JPOS_Gift on JPOS_Log_Exchange.JPOS_Gift = JPOS_Gift.JPOS_GiftID
	join JPOS_Task on JPOS_Log.JPOS_Task = JPOS_Task.JPOS_TaskID	
	join JPOS_Terminal on JPOS_Log.JPOS_TID = JPOS_Terminal.JPOS_TID and JPOS_Log.JPOS_MID = JPOS_Terminal.JPOS_MID
	join JPOS_Merchant on JPOS_Merchant.JPOS_MID = JPOS_Log.JPOS_MID
	join JPOS_PoSCC on JPOS_Log.JPOS_PoSCC_ID = JPOS_PoSCC.JPOS_PoSCC_ID
	order by JPOS_Log.JPOS_Date DESC
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_New_Card') is not null
	drop proc sp_New_Card
go
create procedure sp_New_Card (@CardID varchar(16), @ExpireDay datetime,@ActivateCode varchar(16))
as
begin	
	insert into JPOS_Card(JPOS_CardId,JPOS_ExpireDay,JPOS_Status,JPOS_ActivateCode) values (@CardID,@ExpireDay,'1',@ActivateCode)	
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Delete_Card') is not null
	drop proc sp_Delete_Card
go
create procedure sp_Delete_Card (@CardID varchar(16))
as
begin	
	delete from JPOS_Card where JPOS_CardId = @CardID
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Get_Card') is not null
	drop proc sp_Get_Card
go
create procedure sp_Get_Card (@CardID varchar(16))
as
begin	
	select * from JPOS_Card left join JPOS_Status on JPOS_Status.JPOS_StatusID = JPOS_Card.JPOS_Status where JPOS_CardID = @CardID
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Get_Status') is not null
	drop proc sp_Get_Status
go
create procedure sp_Get_Status (@TableName varchar(50))
as
begin	
	select * from JPOS_Status where JPOS_TableName = @TableName and JPOS_StatusName not like '%Delete%'
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Update_Card') is not null
	drop proc sp_Update_Card
go
create procedure sp_Update_Card (@CardID varchar(16), @ExpireDay datetime,@ActivateCode varchar(16),@Status int)
as
begin	
	update JPOS_Card
	Set 
	JPOS_ExpireDay = @ExpireDay,
	JPOS_Status = @Status,
	JPOS_ActivateCode = @ActivateCode 
	where JPOS_CardId = @CardID
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Assign_Card') is not null
	drop proc sp_Assign_Card
go
create procedure sp_Assign_Card (@CardID varchar(16), @CustomerID int)
as
begin	
	update JPOS_Card
	Set 
	JPOS_CustomerID = @CustomerID
	where JPOS_CardId = @CardID
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Stop_Assign_Card') is not null
	drop proc sp_Stop_Assign_Card
go
create procedure sp_Stop_Assign_Card (@CardID varchar(16))
as
begin	
	update JPOS_Card
	Set 
	JPOS_CustomerID = NULL
	where JPOS_CardId = @CardID
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
	set @card_amount = dbo.fn_get_amount_card(@CardID);
	if(@card_amount < @Amount)
	begin
		set @result = -1;
		return;
	end
	set @card_amount = @card_amount - @Amount;
	update JPOS_Card Set JPOS_Monetary = @card_amount where JPOS_CardId = @CardID;
	set @result = 1;
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
	set @card_amount = dbo.fn_get_amount_card(@CardID);
	set @card_amount = @card_amount + @Amount;
	update JPOS_Card Set JPOS_Monetary = @card_amount where JPOS_CardId = @CardID;
	set @result = 1;
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_New_Customer') is not null
	drop proc sp_New_Customer
go

create procedure sp_New_Customer (@CustomerID int,@FirstName varchar(50),@LastName varchar(50),@Address varchar(200),@Email varchar(200),@BirthDay Datetime,@Gender bit,@Favorite varchar(100),@Point int)
as
begin
	insert into JPOS_Customer values (@CustomerID,@FirstName,@LastName,@Address,@Email,getdate(),@BirthDay,@Gender,@Favorite,@Point,7)
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Delete_Customer') is not null
	drop proc sp_Delete_Customer
go

create procedure sp_Delete_Customer (@CustomerID int)
as
begin
	Update JPOS_Customer set JPOS_Status = 12 where JPOS_Customer.JPOS_CustomerID = @CustomerID
end
go

-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Update_Customer') is not null
	drop proc sp_New_Customer
go

create procedure sp_Update_Customer (@CustomerID int,@FirstName varchar(50),@LastName varchar(50),@Address varchar(200),@Email varchar(200),@BirthDay Datetime,@Gender bit,@Favorite varchar(100),@Point int,@Status int)
as
begin
	Update JPOS_Customer 
	Set 
	JPOS_FirstName = @FirstName,
	JPOS_LastName = @LastName,
	JPOS_Address = @Address,
	JPOS_Email = @Email,
	JPOS_BirthDay = @BirthDay,
	JPOS_Gender = @Gender,
	JPOS_Favorite = @Favorite,
	JPOS_CurrentPoint = @Point,
	JPOS_Status = @Status
	where
	JPOS_CustomerID = @CustomerID
end
go
-------------------------------------------------------------------------------------------------------------------------------