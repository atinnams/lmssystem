/*
* LMS Database.
* v1.1
* Create By : Khuyen Nguyen
* Review By: Hung Pham
* Approve : ...
*/

use LMSDB
go

if object_id('JPOS_Card') is not null
	drop table JPOS_Card

create table JPOS_Card
(
	JPOS_CardId  varchar(16) not null,
	JPOS_ExpireDay datetime not null,
	JPOS_IsActivate int not null,
	Primary Key(JPOS_CardId)
)

if object_id('JPOS_Merchant') is not null
	drop table JPOS_Merchant

create table JPOS_Merchant
(
	JPOS_MID varchar(15) not null,
	JPOS_TID varchar(8) not null,
	JPOS_MerchantName varchar(200),
	Primary Key(JPOS_MID,JPOS_TID)
)

if object_id('JPOS_PoSCC') is not null
	drop table JPOS_PoSCC

create table JPOS_PoSCC
(
	JPOS_PoSCC_ID varchar(2) not null,
	JPOS_PoSCC_Name varchar(100),
	primary key (JPOS_PoSCC_ID)
)

if object_id('JPOS_Customer') is not null
	drop table JPOS_Customer

create table JPOS_Customer
(
	JPOS_IDCustomer	int not null,				--references to Customer ID in Customer Table in NopDB
	JPOS_CardId	varchar(16) unique,				--references to JPOS_Card
	JPOS_CurrentPoint	int not null,
	primary key (JPOS_IDCustomer)
)
go

if object_id('JPOS_Log') is not null
	drop table JPOS_Log

create table JPOS_Log
(
	JPOS_IDLog int not null IDENTITY,
	JPOS_Date	datetime not null,
	JPOS_Task	int not null,					--references to JPOS_Task
	JPOS_Customer	int not null,
	JPOS_PointGain	int not null,
	JPOS_PointLoss	int not null,
	JPOS_TID varchar(8) not null,				--references to JPOS_Merchant
	JPOS_MID varchar(15) not null,				--references to JPOS_Merchant
	JPOS_PoSCC_ID varchar(2) not null,			--references to JPOS_PoSCC
	primary key(JPOS_IDLog)
)
go

if object_id('JPOS_Task') is not null
	drop table JPOS_Task

create table JPOS_Task
(
	JPOS_IDTask int not null,
	JPOS_TaskName nvarchar(200) not null,
	primary key(JPOS_IDTask)
)
go

if object_id('JPOS_Log_Exchange') is not null
	drop table JPOS_Log_Exchange

create table JPOS_Log_Exchange
(
	JPOS_IDLog	int not null,
	JPOS_Gift	int not null,
	primary key (JPOS_IDLog)
)
go

if object_id('JPOS_Gift') is not null
	drop table JPOS_Gift

create table JPOS_Gift
(
	JPOS_IDGift		int not null,
	JPOS_GiftName	nvarchar(200) not null,
	JPOS_PointForGift	int not null,
	primary key (JPOS_IDGift)
)
go