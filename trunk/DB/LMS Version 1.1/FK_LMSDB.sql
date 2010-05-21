/*
* LMS Database.
* v1.1
* Create By : Khuyen Nguyen
* Review : Hung Pham
* Approve : ...
*/

use LMSDB

/*create foreign key */

alter table JPOS_Card
add constraint FK_JPOS_Card_JPOS_Status foreign key(JPOS_Status) references JPOS_Status(JPOS_StatusID),
	constraint KF_JPOS_Card_JPOS_Customer foreign key(JPOS_CustomerID) references JPOS_Customer(JPOS_CustomerID)

go

alter table JPOS_Merchant
add constraint FK_JPOS_Merchant_JPOS_Issuer foreign key(JPOS_IssuerID) references JPOS_Issuer(JPOS_IssuerID)

go

alter table JPOS_Terminal
add constraint FK_JPOS_Terminal_JPOS_Merchant foreign key(JPOS_MID) references JPOS_Merchant(JPOS_MID),
	constraint KF_JPOS_Terminal_JPOS_Status foreign key(JPOS_Status) references JPOS_Status(JPOS_StatusID)

go

alter table JPOS_Log
add constraint FK_JPOS_Log_JPOS_Card foreign key(JPOS_CardID) references JPOS_Card(JPOS_CardID),
	constraint FK_JPOS_Log_JPOS_Task foreign key (JPOS_Task) references JPOS_Task(JPOS_TaskID),
	constraint FK_JPOS_Log_JPOS_Terminal foreign key (JPOS_TID,JPOS_MID) references JPOS_Terminal(JPOS_TID,JPOS_MID),
	constraint FK_JPOS_Log_JPOS_PoSCC foreign key (JPOS_PoSCC_ID) references JPOS_PoSCC(JPOS_PoSCC_ID)
	
go

alter table JPOS_Log_Exchange
add constraint FK_JPOS_Log_Exchange_JPOS_Log foreign key(JPOS_LogID) references JPOS_Log(JPOS_LogID),
	constraint FK_JPOS_Log_Exchange_JPOS_Gift foreign key(JPOS_Gift) references JPOS_Gift(JPOS_GiftID)
go

alter table JPOS_Event
add constraint FK_JPOS_Event_JPOS_Task foreign key(JPOS_TaskID) references JPOS_Task(JPOS_TaskID),
	constraint FK_JPOS_Event_JPOS_Rule foreign key (JPOS_RuleID) references JPOS_Rule (JPOS_RuleID)
go

alter table JPOS_Log_Event
add constraint FK_JPOS_Log_Event_JPOS_Event foreign key (JPOS_EventID) references JPOS_Event(JPOS_EventID),
	constraint FK_JPOS_Log_Event_JPOS_Log foreign key (JPOS_LogID) references JPOS_Log(JPOS_LogID)
go

alter table JPOS_Customer
add constraint FK_JPOS_Customer foreign key (JPOS_Status) references JPOS_Status(JPOS_StatusID)
go
