use LMSDB

/* for drop table and foreign key in database */
/*alter table JPOS_Customer
drop constraint FK_JPOS_Customer_NOP_Customer*/

alter table JPOS_Customer
drop constraint FK_JPOS_Customer_JPOS_Card
go

alter table JPOS_Log
drop constraint FK_JPOS_Log_JPOS_Customer,
	constraint FK_JPOS_Log_JPOS_Task
go
	
alter table JPOS_Log_Exchange
drop constraint FK_JPOS_Log_Exchange_JPOS_Log,
	constraint FK_JPOS_Log_Exchange_JPOS_Gift