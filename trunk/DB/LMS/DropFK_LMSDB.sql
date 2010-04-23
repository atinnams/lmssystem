use LMSDB

/* for drop table and foreign key in database */
/*alter table JPOS_Customer
drop constraint FK_JPOS_Customer_NOP_Customer*/

alter table JPOS_Card
drop constraint FK_JPOS_Card_JPOS_Status,
	 constraint KF_JPOS_Card_JPOS_Customer
go

alter table JPOS_Merchant
drop constraint FK_JPOS_Merchant_JPOS_Issuer
go

alter table JPOS_Terminal
drop constraint FK_JPOS_Terminal_JPOS_Merchant,
	 constraint KF_JPOS_Terminal_JPOS_Status
go 

alter table JPOS_Log
drop constraint FK_JPOS_Log_JPOS_Card,
	 constraint FK_JPOS_Log_JPOS_Task,
	 constraint FK_JPOS_Log_JPOS_Terminal,
	 constraint FK_JPOS_Log_JPOS_PoSCC
go
	
alter table JPOS_Log_Exchange
drop constraint FK_JPOS_Log_Exchange_JPOS_Log,
	constraint FK_JPOS_Log_Exchange_JPOS_Gift
go

alter table JPOS_Event
drop constraint FK_JPOS_Event_JPOS_Task