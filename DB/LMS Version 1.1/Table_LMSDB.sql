/*
* LMS Database.
* v1.1
* Create By : Khuyen Nguyen,Hung Pham
* Review By: Dat Tran
* Approve : ...
*/

use LMSDB
go

if object_id('JPOS_Card') is not null
	drop table JPOS_Card
go

create table JPOS_Card
(
	JPOS_CardId  varchar(16) not null,
	JPOS_ExpireDay datetime not null,
	JPOS_Status int,					--references to Status table
	JPOS_ActivateCode varchar(16),
	JPOS_Monetary int,  
	JPOS_CustomerID int,				--references to Customer table
	Primary Key(JPOS_CardId)
)

-----------------------------------------------------------------------------------------------------

if object_id('JPOS_Customer') is not null
	drop table JPOS_Customer
go

create table JPOS_Customer
(
	JPOS_CustomerID	int not null,
	JPOS_FirstName nvarchar(50) not null,
	JPOS_LastName nvarchar(50),
	JPOS_Address nvarchar(200),
	JPOS_Email varchar(100) unique,
    JPOS_DateJoin datetime,
	JPOS_BirthDay datetime,
	JPOS_Gender bit,
	JPOS_Favorite nvarchar(100),
	JPOS_CurrentPoint	int not null,
	JPOS_Status		int not null,
	primary key (JPOS_CustomerID)
)
go

-----------------------------------------------------------------------------------------------------

if object_id('JPOS_Admin') is not null
	drop table JPOS_Admin
go

create table JPOS_Admin
(
	JPOS_Username nvarchar(50) not null,
	JPOS_Password nvarchar(50) not null,
	JPOS_FirstName nvarchar(50) not null,
	JPOS_LastName nvarchar(50) not null,
	JPOS_DateJoin datetime,	
	JPOS_Email varchar(100) unique,
	JPOS_LoginCount int,
	JPOS_LastLogin datetime,
	
	primary key(JPOS_Username)
)
go

-----------------------------------------------------------------------------------------------------

if object_id('JPOS_Issuer') is not null
	drop table JPOS_Issuer
go

create table JPOS_Issuer
(
	JPOS_IssuerID int not null,
	JPOS_IssuerName nvarchar(100),
	JPOS_IssuerAddress nvarchar(200),
	JPOS_DateFound datetime,
	primary key(JPOS_IssuerID)
)
go

-----------------------------------------------------------------------------------------------------

if object_id('JPOS_Merchant') is not null
	drop table JPOS_Merchant
go

create table JPOS_Merchant
(
	JPOS_MID varchar(15) not null,
	JPOS_MerchantName nvarchar(200),
	JPOS_Address nvarchar(200),
	JPOS_IssuerID int not null, --references to IssuerID of Issuer table
	Primary Key(JPOS_MID)
)
go

-----------------------------------------------------------------------------------------------------

if object_id('JPOS_Terminal') is not null
	drop table JPOS_Terminal
go

create table JPOS_Terminal
(
	JPOS_TID varchar(8) not null,
	JPOS_Status int not null,
	JPOS_PIN varchar(16),
	JPOS_RetryLimit int,
	JPOS_ActivateCode varchar(16),
	JPOS_MID varchar(15) not null,   --references to Merchant ID
	primary key(JPOS_TID,JPOS_MID)
)
go

-----------------------------------------------------------------------------------------------------

if object_id('JPOS_PoSCC') is not null
	drop table JPOS_PoSCC
go

create table JPOS_PoSCC
(
	JPOS_PoSCC_ID varchar(2) not null,
	JPOS_PoSCC_Name varchar(100),
	primary key (JPOS_PoSCC_ID)
)
go

-----------------------------------------------------------------------------------------------------

if object_id('JPOS_Log') is not null
	drop table JPOS_Log
go

create table JPOS_Log
(
	JPOS_LogID int not null IDENTITY,
	JPOS_Date	datetime not null,
	JPOS_Task	int not null,					--references to JPOS_Task
	JPOS_CardID	varchar(16) not null,
	JPOS_InvoiceId varchar(16),
	JPOS_Amount int,
	JPOS_PointGain	int not null,
	JPOS_PointLoss	int not null,
	JPOS_TID varchar(8) not null,				--references to JPOS_Terminal
	JPOS_MID varchar(15) not null,				--references to JPOS_Terminal
	JPOS_PoSCC_ID varchar(2) not null,			--references to JPOS_PoSCC
	primary key(JPOS_LogID)
)
go

if object_id('JPOS_Log_Event') is not null
	drop table JPOS_Log_Event
go

create table JPOS_Log_Event
(
	JPOS_LogID int not null, 			--refernces to JPOS_Log
	JPOS_EventID int not null,
	JPOS_PointGain int,
	JPOS_PointLoss int,
	primary key(JPOS_LogID,JPOS_EventID)
)
go

-----------------------------------------------------------------------------------------------------

if object_id('JPOS_Task') is not null
	drop table JPOS_Task
go

create table JPOS_Task
(
	JPOS_TaskID int not null,
	JPOS_TaskName nvarchar(200) not null,
	primary key(JPOS_TaskID)
)
go

-----------------------------------------------------------------------------------------------------

if object_id('JPOS_Event') is not null
	drop table JPOS_Event
go

create table JPOS_Event
(
	JPOS_EventID int not null,
	JPOS_EventName nvarchar(100),
	JPOS_DateStart datetime not null,
	JPOS_DateEnd datetime not null,
	JPOS_EventMessage nvarchar(200),
	JPOS_TaskID int not null,    --references to Task table
	JPOS_RuleID int not null, --references to Rule table
	primary key(JPOS_EventID)
)
go

-----------------------------------------------------------------------------------------------------

if object_id('JPOS_Log_Exchange') is not null
	drop table JPOS_Log_Exchange
go

create table JPOS_Log_Exchange
(
	JPOS_LogID	int not null,
	JPOS_Gift	int not null,
	primary key (JPOS_LogID)
)
go

-----------------------------------------------------------------------------------------------------

if object_id('JPOS_Gift') is not null
	drop table JPOS_Gift
go

create table JPOS_Gift
(
	JPOS_GiftID		int not null,
	JPOS_GiftName	nvarchar(200) not null,
	JPOS_PointForGift	int not null,
	JPOS_Status		bit not null,
	primary key (JPOS_GiftID)
)
go

-----------------------------------------------------------------------------------------------------

if object_id('JPOS_Status') is not null
	drop table JPOS_Status
go

create table JPOS_Status
(
	JPOS_StatusID int not null,
	JPOS_StatusName varchar(50),
	JPOS_TableName varchar(50),
	primary key(JPOS_statusID)
)

if object_id('JPOS_Rule') is not null
	drop table JPOS_Rule
go

create table JPOS_Rule
(
	JPOS_RuleID int not null,
	JPOS_Money float not null,
	JPOS_Point int not null,
	JPOS_Description	varchar(200),
	primary key(JPOS_RuleID)
)