use LMSDB

if object_id('fn_check_card') is not null
	drop function fn_check_card
go

create function  fn_check_card(@cardid varchar(16))
returns int
as	
begin	
	return (select count(*) from JPOS_Card where JPOS_CardId = @cardid);
end
go

--test fn_check_card
/*
declare @result int;
select @result = dbo.fn_check_card('1234567887654321');
select @result; -- expected to 1

select @result = dbo.fn_check_card('1234567887654320');
select @result; -- expected to 0

*/

--===========================================================================

if object_id('fn_check_card_expire') is not null
	drop function fn_check_card_expire
go

create function  fn_check_card_expire(@cardid varchar(16))
returns int
as	
begin	
	return (select count(*) from JPOS_Card where JPOS_CardId = @cardid and getdate() > JPOS_ExpireDay);
end
go

--test
/*
declare @result int;
select @result = dbo.fn_check_card_expire('1111111111111111');
select @result ; --expected to 1

select @result = dbo.fn_check_card_expire('1234567812345678');
select @result ; --expected to 0

*/
--===========================================================================

if object_id('fn_check_merchant') is not null
	drop function fn_check_merchant_n_terminal
go

create function  fn_check_merchant_n_terminal(@MID varchar(15),@TID varchar(8))
returns int
as	
begin	
	return (select count(*) from JPOS_Terminal where JPOS_TID = @TID and JPOS_MID = @MID);
end
go

--test fn_check_merchant
/*
declare @result int;
select @result = dbo.fn_check_merchant_n_terminal('000000000000001','00000001');
select @result; --expected to 1;

select @result = dbo.fn_check_merchant_n_terminal('000000000000010','00000001');
select @result; --expected to 0;
*/
--===========================================================================

if object_id('fn_check_poscc') is not null
	drop function fn_check_poscc
go

create function  fn_check_poscc(@PoSCCID varchar(2))
returns int
as	
begin	
	return (select count(*) from JPOS_PoSCC where JPOS_PoSCC_ID = @PoSCCID) ;
end
go

--test
/*
declare @result int;
select @result = dbo.fn_check_poscc('01');
select @result; --expected to 1;

select @result = dbo.fn_check_poscc('04');
select @result; --expected to 0;
*/ 

--===========================================================================

if object_id('fn_balance_inquiry') is not null
	drop function fn_balance_inquiry
go

create function fn_balance_inquiry(@cardid varchar(16))
returns int
as
begin
	declare @result int ;
	set @result = (select customer.JPOS_CurrentPoint from JPOS_Customer customer,JPOS_Card myCard where myCard.JPOS_CardId = @cardid and myCard.JPOS_CustomerID = customer.JPOS_CustomerID ) ;
	return @result;
end
go
/*

--test
declare @result int;
select @result = dbo.fn_balance_inquiry('1234567812345678');
select @result;
select * from JPOS_Card;
select * from JPOS_Customer;
*/

--===========================================================================

if object_id('fn_check_activated') is not null
	drop function fn_check_activated
go

create function fn_check_activated(@cardid varchar(16))
returns int
as
begin
	return (select count(*)
			from JPOS_Card
			where JPOS_CardId = @cardid
			and JPOS_Status = 2);
end
go
/*
--test
declare @result int;
select @result = dbo.fn_check_activated('1234567812345678');
select @result;

select @result = dbo.fn_check_activated('1234567887654321');
select @result;

*/

--===========================================================================

if object_id('fn_get_gift_point') is not null
	drop function fn_get_gift_point
go

create function fn_get_gift_point(@giftType int)
returns int
as
begin
	return (select JPOS_PointForGift from JPOS_Gift where JPOS_GiftID = @giftType);
end
go
/*
--test
select * from JPOS_Gift;
declare @result int;
select @result = dbo.fn_get_gift_point(1);
select @result;
*/

--===========================================================================

if object_id('fn_check_redemption_point') is not null
	drop function fn_check_redemption_point
go

create function fn_check_redemption_point(@cardid varchar(16),@giftType int)
returns int
as
begin
	declare @tmp_point int;
	declare @gift_point int;
	set @tmp_point = (	select jCustomer.JPOS_CurrentPoint 
						from JPOS_Customer jCustomer,JPOS_Card jCard
						where jCustomer.JPOS_CustomerID = jCard.JPOS_CustomerID
						and jCard.JPOS_CardId = @cardid);
	set @gift_point = dbo.fn_get_gift_point(@giftType);
	if( @gift_point is NULL)
		return 0;
	if( @tmp_point >= @gift_point)
		return 1;
	return 0;
end
go
/*
--test
select * from JPOS_Card;
select * from JPOS_Customer;
declare @result int;
update JPOS_Customer set JPOS_CurrentPoint = '80' where JPOS_CustomerID = '1';
select @result = dbo.fn_check_redemption_point('1234567812345678',1);
select @result;

update JPOS_Customer set JPOS_CurrentPoint = '450' where JPOS_CustomerID = '1';
select @result = dbo.fn_check_redemption_point('1234567812345678',10);
select @result;

*/

if object_id('fn_get_rule_point') is not null
	drop function fn_get_rule_point;
go

create function fn_get_rule_point(@money float,@RuleID int)
returns int
as
begin
	declare @RuleMoney float;
	declare @PointRule int;
	declare @result int;
	set @RuleMoney = (select JPOS_Money from JPOS_Rule where JPOS_RuleID = @RuleID);
	set @PointRule = (select JPOS_Point from JPOS_Rule where JPOS_RuleID = @RuleID);
	set @result = cast((@Money / @RuleMoney) as int) * @PointRule;
	return @result;
end
go

/*test
declare @Point int;
select @Point = dbo.fn_get_rule_point(300000,1);
select @Point; --expect 3

select @Point = dbo.fn_get_rule_point(298000,1);
select @Point; --expected 2

*/