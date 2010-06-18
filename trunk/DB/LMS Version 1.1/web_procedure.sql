/*
Procedure List:

01. sp_Login(@Username nvarchar(50),@Password nvarchar(50),@Result int Output)
	Record login for admin
02. sp_Search_Customer(@CustomerID int,@FirstName nvarchar(50),@LastName nvarchar(50), @Address nvarchar(200),@Email varchar(100), @DateJoin datetime, @BirthDay datetime, @Gender bit, @Favorite nvarchar,@CurrentPoint int)
	Search customer
03. sp_Transaction_Detail(@CustomerID int)
	Transaction detail of customer
04. sp_Customer_Infor(@CustomerID int)
	Customer's infor
05. sp_Customer_Report()
	Customer list report
06. sp_Card_Report()
	Card list report
07. sp_Terminal_Report()
	Terminal list report
08. sp_Transaction_Report()
	Transaction list report
09. sp_New_Card (@CardID varchar(16), @ExpireDay datetime,@ActivateCode varchar(16))
	Add new card
10. sp_Delete_Card (@CardID varchar(16))
	Delete card with CardID
11. sp_Get_Card (@CardID varchar(16))
	Get card information
12. sp_Get_Status (@TableName varchar(50))
	Get list status available for table have name @tablename
13. sp_Update_Card (@CardID varchar(16), @ExpireDay datetime,@ActivateCode varchar(16),@Status int,@Monetary int)
	Update card
14. sp_Assign_Card (@CardID varchar(16), @CustomerID int)
	Assign Card for customer
15. sp_Stop_Assign_Card (@CardID varchar(16))
	Stop assign card
16. sp_New_Customer (@CustomerID int,@FirstName varchar(50),@LastName varchar(50),@Address varchar(200),@Email varchar(200),@BirthDay Datetime,@Gender bit,@Favorite varchar(100),@Point int)
	Add new Customer
17. sp_Delete_Customer (@CustomerID int)
	Delete customer
18. sp_Update_Customer (@CustomerID int,@FirstName varchar(50),@LastName varchar(50),@Address varchar(200),@Email varchar(200),@BirthDay Datetime,@Gender bit,@Favorite varchar(100),@Point int,@Status int)
	Update customer information
19. sp_Merchant_List
	Get merchant list
20. sp_New_Merchant(@MID varchar(15),@Name nvarchar(200),@Address nvarchar(200))
	Add new Merchant
21. sp_Delete_Merchant(@MID varchar(15))
	Delete a merchant
22. sp_Update_Merchant(@MID varchar(15),@Name nvarchar(200),@Address nvarchar(200),@Status int)
	Update a merchant
23. sp_Get_Merchant(@MID varchar(15))
	Get merchant information
24. sp_Terminal_Search(@key varchar(200))
	Search Terminal
25. sp_New_Terminal(@TID varchar(8),@Status int,@PIN varchar(16),@Retry int,@ActiveCode varchar(16))
	Add new terminal
26. sp_Update_Terminal(@TID varchar(8),@Status int,@PIN varchar(16),@Retry int,@ActiveCode varchar(16))
	update terminal
27. sp_Delete_Terminal(@TID varchar(8))
	delete terminal
28. sp_Get_Terminal(@TID varchar(8))
	get terminal information
29. sp_Assign_Terminal(@TID varchar(8),@MID varchar(15))
	assign terminal to merchant
30. sp_Stop_Assign_Terminal(@TID varchar(8))
	stop assign a terminal
31. sp_Customer_Search(@Key varchar(200))
	search customer
32. sp_Card_Search(@Key varchar(200))
	search card
33. sp_Merchant_Search(@Key varchar(200))
	search merchant

*/

use LMSDB;
go

if object_id('sp_Login') is not null
	drop proc sp_Login
go
create procedure sp_Login(@Username nvarchar(50),@Password nvarchar(50),@Result int Output)
as
begin
	select * from JPOS_Admin where JPOS_Username = @Username and JPOS_Password = @Password
	if  (@@rowcount = 0)
	begin		
		select * from JPOS_Customer where JPOS_Username = @Username and JPOS_Password = @Password
		if (@@rowcount = 0)
			set @Result = 0;
		else
			set @Result = 2;
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
	Select JPOS_Log.JPOS_LogID,JPOS_Log.JPOS_Date,JPOS_Task.JPOS_TaskName,JPOS_Log.JPOS_CardID,JPOS_Customer.JPOS_CustomerID,JPOS_LOG.JPOS_PointGain,JPOS_LOG.JPOS_PointLoss,JPOS_PoSCC.JPOS_PoSCC_Name,JPOS_Terminal.JPOS_TID,JPOS_Merchant.JPOS_MerchantName,JPOS_Merchant.JPOS_Address,JPOS_Gift.JPOS_GiftName,JPOS_Log.JPOS_Amount
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
	select * from JPOS_Customer	C left join JPOS_Status on C.JPOS_Status = JPOS_StatusID where JPOS_StatusName not like '%Delete%'
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
	select JPOS_TID,JPOS_PIN,JPOS_Terminal.JPOS_Status,JPOS_StatusName,JPOS_ActivateCode,JPOS_RetryLimit,JPOS_Terminal.JPOS_MID,JPOS_MerchantName,JPOS_Address from JPOS_Terminal left join JPOS_Merchant on JPOS_Terminal.JPOS_MID = JPOS_Merchant.JPOS_MID left join JPOS_Status on JPOS_Terminal.JPOS_Status = JPOS_Status.JPOS_StatusID
	where JPOS_StatusName not like '%Delete%'
end
go 
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Transaction_Report') is not null
	drop proc sp_Transaction_Report
go
create procedure sp_Transaction_Report
as
begin
	Select JPOS_Log.JPOS_LogID,JPOS_Log.JPOS_Date,JPOS_Task.JPOS_TaskName,JPOS_Log.JPOS_CardID,JPOS_Customer.JPOS_CustomerID,JPOS_LOG.JPOS_PointGain,JPOS_LOG.JPOS_PointLoss,JPOS_PoSCC.JPOS_PoSCC_Name,JPOS_Terminal.JPOS_TID,JPOS_Merchant.JPOS_MerchantName,JPOS_Merchant.JPOS_Address,JPOS_Gift.JPOS_GiftName,JPOS_Log.JPOS_InvoiceId,JPOS_Log.JPOS_Amount
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
	insert into JPOS_Card(JPOS_CardId,JPOS_ExpireDay,JPOS_Status,JPOS_ActivateCode,JPOS_Monetary) values (@CardID,@ExpireDay,'1',@ActivateCode,0)	
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
create procedure sp_Update_Card (@CardID varchar(16), @ExpireDay datetime,@ActivateCode varchar(16),@Status int,@Monetary int)
as
begin	
	update JPOS_Card
	Set 
	JPOS_ExpireDay = @ExpireDay,
	JPOS_Status = @Status,
	JPOS_ActivateCode = @ActivateCode ,
	JPOS_Monetary = @Monetary
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

if object_id('sp_New_Customer') is not null
	drop proc sp_New_Customer
go

create procedure sp_New_Customer (@CustomerID int,@Username varchar(50),@Password varchar(50),@FirstName nvarchar(50),@LastName nvarchar(50),@Address nvarchar(200),@Email varchar(200),@BirthDay Datetime,@Gender bit,@Favorite nvarchar(100),@Point int)
as
begin
	declare @result int;
	set @result = dbo.fn_Generate_CustomerID();	
	insert into JPOS_Customer values (@result,@Username,@Password,@FirstName,@LastName,@Address,@Email,getdate(),@BirthDay,@Gender,@Favorite,@Point,7);
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
	drop proc sp_Update_Customer
go

create procedure sp_Update_Customer (@CustomerID int,@Username varchar(50),@Password varchar(50),@FirstName nvarchar(50),@LastName nvarchar(50),@Address nvarchar(200),@Email varchar(200),@BirthDay Datetime,@Gender bit,@Favorite nvarchar(100),@Point int,@Status int)
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
	JPOS_Status = @Status,
	JPOS_Password = @Password
	where
	JPOS_CustomerID = @CustomerID
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Merchant_List') is not null
	drop proc sp_Merchant_List
go

create procedure sp_Merchant_List
as
begin
	select * from JPOS_Merchant M left join JPOS_Issuer I on M.JPOS_IssuerID = I.JPOS_IssuerID left join JPOS_Status S on M.JPOS_Status = S.JPOS_StatusID
	where S.JPOS_StatusName not like '%Delete%'
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_New_Merchant') is not null
	drop proc sp_New_Merchant
go

create procedure sp_New_Merchant(@MID varchar(15),@Name nvarchar(200),@Address nvarchar(200))
as
begin
	insert into JPOS_Merchant(JPOS_MID,JPOS_MerchantName,JPOS_Address,JPOS_Status,JPOS_IssuerID) values (@MID,@Name,@Address,'14',1)
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Delete_Merchant') is not null
	drop proc sp_Delete_Merchant
go

create procedure sp_Delete_Merchant(@MID varchar(15))
as
begin
	update JPOS_Merchant
	set JPOS_Status = 15
	where JPOS_MID = @MID
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Update_Merchant') is not null
	drop proc sp_Update_Merchant
go

create procedure sp_Update_Merchant(@MID varchar(15),@Name nvarchar(200),@Address nvarchar(200),@Status int)
as
begin
	update JPOS_Merchant
	set JPOS_Status = @Status,
	JPOS_MerchantName = @Name,
	JPOS_Address = @Address
	where JPOS_MID = @MID
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Get_Merchant') is not null
	drop proc sp_Get_Merchant
go

create procedure sp_Get_Merchant(@MID varchar(15))
as
begin
	select * from JPOS_Merchant M left join JPOS_Issuer I on M.JPOS_IssuerID = I.JPOS_IssuerID left join JPOS_Status S on M.JPOS_Status = S.JPOS_StatusID
	where S.JPOS_StatusName not like '%Delete%' and M.JPOS_MID = @MID
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Terminal_Search') is not null
	drop proc sp_Terminal_Search
go

create procedure sp_Terminal_Search(@key varchar(200))
as
begin
	declare @result int;
	begin try
		set @result = dbo.fn_Convert_String_Int('asdasdas1');
	end try
	begin catch
		set @result = -1;
	end catch
	
	select * from JPOS_Terminal T left join JPOS_Merchant M on T.JPOS_MID = M.JPOS_MID left join JPOS_Status on T.JPOS_Status = JPOS_StatusID
	where 
	(JPOS_TID like '%'+ @key +'%' or
	JPOS_StatusName like '%'+ @key +'%' or
	JPOS_PIN like '%'+ @key +'%' or
	JPOS_ActivateCode like '%'+ @key +'%' or
	T.JPOS_MID like '%'+ @key +'%'
	or JPOS_RetryLimit = @result)
	and JPOS_StatusName not like '%Delete%'
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_New_Terminal') is not null
	drop proc sp_New_Terminal
go

create procedure sp_New_Terminal(@TID varchar(8),@Status int,@PIN varchar(16),@Retry int,@ActiveCode varchar(16))
as
begin
	insert into JPOS_Terminal(JPOS_TID, JPOS_Status, JPOS_PIN, JPOS_RetryLimit, JPOS_ActivateCode)
	values (@TID,'4',@PIN,@Retry,@ActiveCode)
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Update_Terminal') is not null
	drop proc sp_Update_Terminal
go

create procedure sp_Update_Terminal(@TID varchar(8),@Status int,@PIN varchar(16),@Retry int,@ActiveCode varchar(16))
as
begin
	Update JPOS_Terminal
	set JPOS_TID = @TID,
	JPOS_Status = @Status,
	JPOS_PIN = @PIN,
	JPOS_RetryLimit = @Retry,
	JPOS_ActivateCode = @ActiveCode
	where JPOS_TID = @TID
end
go

-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Delete_Terminal') is not null
	drop proc sp_Delete_Terminal
go

create procedure sp_Delete_Terminal(@TID varchar(8))
as
begin
	update JPOS_Terminal
	set JPOS_Status = 11
	where JPOS_TID = @TID
end
go

-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Get_Terminal') is not null
	drop proc sp_Get_Terminal
go

create procedure sp_Get_Terminal(@TID varchar(8))
as
begin
	select * from JPOS_Terminal T left join JPOS_Merchant M on T.JPOS_MID = M.JPOS_MID left join JPOS_Status on T.JPOS_Status = JPOS_StatusID where JPOS_TID = @TID 
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Assign_Terminal') is not null
	drop proc sp_Assign_Terminal
go

create procedure sp_Assign_Terminal(@TID varchar(8),@MID varchar(15))
as
begin
	update JPOS_Terminal 
	set JPOS_MID = @MID
	where JPOS_TID = @TID
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Stop_Assign_Terminal') is not null
	drop proc sp_Stop_Assign_Terminal
go

create procedure sp_Stop_Assign_Terminal(@TID varchar(8))
as
begin
	update JPOS_Terminal 
	set JPOS_MID = null
	where JPOS_TID = @TID
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Customer_Search') is not null
	drop proc sp_Customer_Search
go
create procedure sp_Customer_Search(@Key varchar(200))
as
begin
	begin try
		select * from JPOS_Customer	C left join JPOS_Status on C.JPOS_Status = JPOS_StatusID 
		where 
		(JPOS_CustomerID = dbo.fn_Convert_String_Int(@Key) or
		JPOS_LastName like '%'+@Key+'%' or
		JPOS_FirstName like '%'+@Key+'%' or
		JPOS_Address like '%'+@Key+'%' or
		JPOS_Email like '%'+@Key+'%' or
		JPOS_Favorite like '%'+@Key+'%' or
		JPOS_CurrentPoint = dbo.fn_Convert_String_Int(@Key) or
		JPOS_Username like '%'+@Key+ '%' or	
		JPOS_StatusName like '%'+@Key+'%') and
		JPOS_StatusName not like '%Delete%'
	end try
	begin catch
		select * from JPOS_Customer	C left join JPOS_Status on C.JPOS_Status = JPOS_StatusID 
		where 
		(JPOS_CustomerID = dbo.fn_Convert_String_Int(@Key) or
		JPOS_LastName like '%'+@Key+'%' or
		JPOS_FirstName like '%'+@Key+'%' or
		JPOS_Address like '%'+@Key+'%' or
		JPOS_Email like '%'+@Key+'%' or
		JPOS_Favorite like '%'+@Key+'%' or
		JPOS_Username like '%'+@Key+ '%' or		
		JPOS_StatusName like '%'+@Key+'%') and
		JPOS_StatusName not like '%Delete%'		
	end catch
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Card_Search') is not null
	drop proc sp_Card_Search
go
create procedure sp_Card_Search(@Key varchar(200))
as
begin
	begin try
		select * from JPOS_Card	C left join JPOS_Status on C.JPOS_Status = JPOS_StatusID 
		where 
		(JPOS_CardID like '%'+@Key+'%' or
		JPOS_ActivateCode like '%'+@Key+'%' or
		JPOS_Monetary = dbo.fn_Convert_String_Int(@Key) or	
		JPOS_StatusName like '%'+@Key+'%') and
		JPOS_StatusName not like '%Delete%'
	end try
	begin catch
		select * from JPOS_Card	C left join JPOS_Status on C.JPOS_Status = JPOS_StatusID 
		where 
		(JPOS_CardID like '%'+@Key+'%' or
		JPOS_ActivateCode like '%'+@Key+'%' or		
		JPOS_StatusName like '%'+@Key+'%') and
		JPOS_StatusName not like '%Delete%'
	end catch
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Merchant_Search') is not null
	drop proc sp_Merchant_Search
go
create procedure sp_Merchant_Search(@Key varchar(200))
as
begin
	begin try
		select * from JPOS_Merchant M left join JPOS_Issuer I on M.JPOS_IssuerID = I.JPOS_IssuerID left join JPOS_Status S on M.JPOS_Status = S.JPOS_StatusID
		where 
		(JPOS_MID like '%'+@Key+'%' or
		JPOS_MerchantName like '%'+@Key+'%' or
		M.JPOS_IssuerID = dbo.fn_Convert_String_Int(@Key) or	
		JPOS_Address like '%'+@Key+'%' or
		JPOS_StatusName like '%'+@Key+'%') and
		JPOS_StatusName not like '%Delete%'
	end try
	begin catch
		select * from JPOS_Merchant M left join JPOS_Issuer I on M.JPOS_IssuerID = I.JPOS_IssuerID left join JPOS_Status S on M.JPOS_Status = S.JPOS_StatusID
		where 
		(JPOS_MID like '%'+@Key+'%' or
		JPOS_MerchantName like '%'+@Key+'%' or		
		JPOS_Address like '%'+@Key+'%' or
		JPOS_StatusName like '%'+@Key+'%') and
		JPOS_StatusName not like '%Delete%'
	end catch
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_New_Admin') is not null
	drop proc sp_New_Admin
go
create procedure sp_New_Admin(@Username nvarchar(50), @Password nvarchar(50), @FirstName nvarchar(50), @LastName nvarchar(50), @Email varchar(100))
as
begin
	insert into JPOS_Admin(JPOS_Username,JPOS_Password,JPOS_FirstName,JPOS_LastName,JPOS_DateJoin,JPOS_Email,JPOS_LoginCount,JPOS_LastLogin) values (@Username, @Password, @FirstName, @LastName, getdate(),@Email, 0, getdate())
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Delete_Admin') is not null
	drop proc sp_Delete_Admin
go
create procedure sp_Delete_Admin(@Username nvarchar(50))
as
begin
	delete from JPOS_Admin
	where JPOS_Username = @Username
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Update_Admin') is not null
	drop proc sp_Update_Admin
go
create procedure sp_Update_Admin(@Username nvarchar(50), @Password nvarchar(50), @FirstName nvarchar(50), @LastName nvarchar(50), @Email varchar(100))
as
begin
	Update JPOS_Admin 
	set 
	JPOS_Password = @Password,
	JPOS_FirstName = @FirstName,
	JPOS_LastName = @LastName,	
	JPOS_Email = @Email
	where JPOS_Username = @Username
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Admin_Search') is not null
	drop proc sp_Admin_Search
go
create procedure sp_Admin_Search(@Key nvarchar(100))
as
begin
	Select * from JPOS_Admin 
	where
	JPOS_Username like '%'+@Key+'%'or
	JPOS_FirstName like '%'+@Key + '%' or
	JPOS_LastName like '%'+@Key+'%' or	
	JPOS_Email like '%'+@Key+'%'
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Admin_List') is not null
	drop proc sp_Admin_List
go
create procedure sp_Admin_List
as
begin
	Select * from JPOS_Admin 	
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Get_Admin') is not null
	drop proc sp_Get_Admin
go
create procedure sp_Get_Admin (@Username nvarchar(50))
as
begin
	Select * from JPOS_Admin where JPOS_Username = @Username
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Get_Issuer') is not null
	drop proc sp_Get_Issuer
go
create procedure sp_Get_Issuer
as
begin
	Select * from JPOS_Issuer
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Update_Issuer') is not null
	drop proc sp_Update_Issuer
go
create procedure sp_Update_Issuer (@Name nvarchar(100),@Address nvarchar(200), @Date Datetime)
as
begin
	update JPOS_Issuer 
	set JPOS_IssuerName = @Name, JPOS_IssuerAddress = @Address, JPOS_DateFound = @Date
	where JPOS_IssuerID = 1
end
go

-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_User_Login') is not null
	drop proc sp_User_Login
go
create procedure sp_User_Login (@Username varchar(50), @Password varchar(50))
as
begin
	select * from JPOS_Customer	left join JPOS_Status on JPOS_Status = JPOS_StatusID
	where
	JPOS_StatusName not like '%Delete%' and JPOS_Username = @Username and JPOS_Password = @Password
end
go
-------------------------------------------------------------------------------------------------------------------------------
if object_id('sp_Get_Card_Belong_Customer') is not null
	drop proc sp_Get_Card_Belong_Customer
go
create procedure sp_Get_Card_Belong_Customer(@CustomerID int)
as
begin
	select * from JPOS_Card left join JPOS_Status on JPOS_Status.JPOS_StatusID = JPOS_Card.JPOS_Status where JPOS_CustomerID = @CustomerID
end
go