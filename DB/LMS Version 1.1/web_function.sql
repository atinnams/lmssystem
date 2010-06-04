/*
function list :

01. fn_Generate_CustomerID()
	Generate new customerID
02.	fn_Check_Merchant_exist(@MID varchar(15))
	Check merchant is existed or not
*/
--===========================================================================
if object_id('fn_Generate_CustomerID') is not null
	drop function fn_Generate_CustomerID
go

create function fn_Generate_CustomerID()
returns int
as
begin
	declare @result int ;
	set @result = (select Max(JPOS_CustomerID)  from JPOS_Customer);
	return @result+1;
end
go
/*
declare @result int;
set @result = dbo.fn_Generate_CustomerID();
select @result;
*/
--===========================================================================
if object_id('fn_Check_Merchant_exist') is not null
	drop function fn_Check_Merchant_exist
go

create function fn_Check_Merchant_exist(@MID varchar(15))
returns int
as
begin	
	return ( select count(*) from JPOS_Merchant where JPOS_MID = @MID);
end
go
/*
declare @result int;
set @result = dbo.fn_Check_Merchant_exist('000000000000001');
select @result;
*/
--===========================================================================
if object_id('fn_Check_Terminal_exist') is not null
	drop function fn_Check_Terminal_exist
go

create function fn_Check_Terminal_exist(@TID varchar(8))
returns int
as
begin	
	return ( select count(*) from JPOS_Terminal where JPOS_TID = @TID);
end
go
/*
declare @result int;
set @result = dbo.fn_Check_Terminal_exist('00000001');
select @result;
*/
--===========================================================================
if object_id('fn_Convert_String_Int') is not null
	drop function fn_Convert_String_Int
go

create function fn_Convert_String_Int(@String varchar(8))
returns int
as
begin	
	if (Isnumeric(@String) = 1)
	begin
		return (CONVERT(INT, @String))
	end	
	return -1	
end
go
/*
declare @result int;
set @result = dbo.fn_Convert_String_Int('00000001');
select @result;
*/