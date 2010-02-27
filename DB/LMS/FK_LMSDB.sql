/*
* LMS Database.
* v1.1
* Create By : Khuyen Nguyen
* Review : Hung Pham
* Approve : ...
*/

use LMSDB

/*create foreign key */

--use later
/*alter table JPOS_Customer
add constraint FK_JPOS_Customer_NOP_Customer foreign key(JPOS_IDCustomer) references NOP_Customer(CustomerID)
go
*/

alter table JPOS_Customer
add constraint FK_JPOS_Customer_JPOS_Card foreign key(JPOS_CardId) references JPOS_Card(JPOS_CardId)
go

alter table JPOS_Log
add constraint FK_JPOS_Log_JPOS_Customer foreign key(JPOS_Customer) references JPOS_Customer(JPOS_IDCustomer),
	constraint FK_JPOS_Log_JPOS_Task foreign key (JPOS_Task) references JPOS_Task(JPOS_IDTask),
	constraint FK_JPOS_Log_JPOS_Merchant foreign key (JPOS_MID,JPOS_TID) references JPOS_Merchant(JPOS_MID,JPOS_TID),
	constraint FK_JPOS_Log_JPOS_PoSCC foreign key (JPOS_PoSCC_ID) references JPOS_PoSCC(JPOS_PoSCC_ID)
	
go

alter table JPOS_Log_Exchange
add constraint FK_JPOS_Log_Exchange_JPOS_Log foreign key(JPOS_IDLog) references JPOS_Log(JPOS_IDLog),
	constraint FK_JPOS_Log_Exchange_JPOS_Gift foreign key(JPOS_Gift) references JPOS_Gift(JPOS_IDGift)
go
