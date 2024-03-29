use LMSDB;

Declare @Result int
set @Result = 0;
exec sp_add_n_log_point '9704215000000046', '1','10000','052514412207', '1', '000000000000001','00000001','01', @Result output
select * from JPOS_Customer
select * from JPOS_Log
select @Result Result
go

exec sp_balance_inquiry_log '9704215000000046', '4','200000', '000000000000001','00000001','01'
select * from JPOS_Customer
select * from JPOS_Log
go


Declare @Result int
set @Result = 0;
exec sp_update_Point '1234567812345678','4', @Result output
select * from JPOS_Customer
select * from JPOS_Log
select @Result Result

go

Declare @Result int
set @Result = 0;
exec sp_get_n_log_event_point '1234567812345678','200000','14', @Result output
select * from JPOS_Customer
select * from JPOS_Log
select * from JPOS_Log_Event
select @Result Result



go
declare @point int;
exec sp_get_n_log_event_point '298000' , '1' ,@point output;
select * from JPOS_Log_Event
select @point point;


--test
Declare @Result int
set @Result = 0
exec sp_Sub_Point '1234567812345678', '2', '10', '000000000000001','00000001','01', @Result output
select * from JPOS_Customer
select * from JPOS_Log
select @Result
select * from JPOS_Card

go 
update JPOS_Customer set JPOS_CurrentPoint = 50 where JPOS_CardId = '8765432112345678';
select * from JPOS_Customer;

select * from JPOS_Card
update JPOS_Customer set JPOS_CurrentPoint='50' where JPOS_IDCustomer='41';
select * from JPOS_Card

Declare @Result int
set @Result = 0
exec sp_redemption '1234567812345678', '3', '10', '000000000000001','00000001','01', @Result output
select * from JPOS_Customer
select * from JPOS_Log
select * from JPOS_Log_Exchange
select @Result


select * from JPOS_Gift

select *
from JPOS_Gift
where JPOS_PointForGift < (select jCustomer.JPOS_CurrentPoint
								from JPOS_Customer jCustomer,JPOS_Card jCard
								where jCustomer.JPOS_CustomerID = jCard.JPOS_CustomerID
								and jCard.JPOS_CardId = '1234567812345678')

select * from JPOS_Customer
select * from JPOS_Card 
select * from JPOS_Log
select * from JPOS_Log_Event


exec sp_Search_Customer -1,'','','','','','','false','',-1
select * from JPOS_Customer where JPOS_Gender = ''

update JPOS_Card set JPOS_Monetary = '200000' where JPOS_CardId = '9704215000000046';
update JPOS_Card set JPOS_Monetary = '200000' where JPOS_CardId = '8704353300000015';

declare @result int
exec sp_redeem_Card '9704215000000046','20000',@result output
select @result
select * from JPOS_Card 

declare @result int
exec sp_reload_Card '9704215000000046','20000',@result output
select @result
select * from JPOS_Card

select * from JPOS_Log;
delete from JPOS_Log where JPOS_LogId = '15';

declare @result int;
set @result = 0;
select * from JPOS_Card;
exec sp_void_card '9704215000000046','052514412207','20000',@result output;
select * from JPOS_Card;
select @result;


Declare @Result int
set @Result = 0;
exec sp_add_n_log_point '9704215000000046', '1','10000','052514412221', '1', '000000000000001','00000001','01', @Result output
select * from JPOS_Customer
select * from JPOS_Log
select @Result Result
go


exec sp_reversal_redeem '9704215000000046', '5','052514412212', '000000000000001','00000001','01';
select * from JPOS_Customer
select * from JPOS_Card;
select * from JPOS_Log;

exec sp_reversal_reload_log '9704215000000046', '3','10000','052514412217', '000000000000001','00000001','01';
exec sp_reversal_reload_log '9704215000000046', '3','10000','052514412218', '000000000000001','00000001','01';

exec sp_reversal_reload '9704215000000046', '5','052514412214', '000000000000001','00000001','01';

exec sp_reversal_void_redeem '9704215000000046', '5','052514412217', '000000000000001','00000001','01';
exec sp_reversal_void_reload '9704215000000046', '5','052514412218', '000000000000001','00000001','01';


