use LMSDB

/*insert into JPOS_Card */

insert into JPOS_Card values('1234567812345678','12-31-2010','0');
insert into JPOS_Card values('8765432187654321','12-31-2010','0');
insert into JPOS_Card values('1234567887654321','01-31-2010','0');
insert into JPOS_Card values('8765432112345678','12-31-2010','0');
insert into JPOS_Card values('0246810121416180','12-31-2010','0');
insert into JPOS_Card values('1357911131517191','12-31-2010','0');
insert into JPOS_Card values('1111111111111111','01-31-2010','0');
insert into JPOS_Card values('2222222222222222','12-31-2010','0');
insert into JPOS_Card values('3333333333333333','12-31-2010','0');
insert into JPOS_Card values('4444444444444444','12-31-2010','0');
insert into JPOS_Card values('5555555555555555','12-31-2010','0');
insert into JPOS_Card values('6666666666666666','12-31-2010','0');
insert into JPOS_Card values('7777777777777777','12-31-2010','0');
insert into JPOS_Card values('8888888888888888','12-31-2010','0');
insert into JPOS_Card values('9999999999999999','12-31-2010','0');

/* insert into JPOS_Merchant */
insert into JPOS_Merchant values('000000000000000','00000000','Reserve for Mobile Devide');
insert into JPOS_Merchant values('000000000000001','00000001','Nguyen Van Cu District 5. Pos 1');
insert into JPOS_Merchant values('000000000000001','00000002','Nguyen Van Cu District 5. Pos 2');
insert into JPOS_Merchant values('000000000000001','00000003','Nguyen Van Cu District 5. Pos 3');
insert into JPOS_Merchant values('000000000000001','00000004','Nguyen Van Cu District 5. Pos 4');
insert into JPOS_Merchant values('000000000000001','00000005','Nguyen Van Cu District 5. Pos 5');
insert into JPOS_Merchant values('000000000000002','00000001','Nguyen Van Cu District 1. Pos 1');
insert into JPOS_Merchant values('000000000000002','00000002','Nguyen Van Cu District 1. Pos 2');
insert into JPOS_Merchant values('000000000000002','00000003','Nguyen Van Cu District 1. Pos 3');
insert into JPOS_Merchant values('000000000000002','00000004','Nguyen Van Cu District 1. Pos 4');
insert into JPOS_Merchant values('000000000000002','00000005','Nguyen Van Cu District 1. Pos 5');
insert into JPOS_Merchant values('000000000000003','00000001','Nguyen Van Cu Tan Binh District. Pos 1');
insert into JPOS_Merchant values('000000000000003','00000002','Nguyen Van Cu Tan Binh District 5. Pos 2');
insert into JPOS_Merchant values('000000000000003','00000003','Nguyen Van Cu Tan Binh District 5. Pos 3');
insert into JPOS_Merchant values('000000000000003','00000004','Nguyen Van Cu Tan Binh District 5. Pos 4');
insert into JPOS_Merchant values('000000000000003','00000005','Nguyen Van Cu Tan Binh District 5. Pos 5');

/* insert data JPOS_PoSCC */

insert into JPOS_PoSCC values('00','Loyaty Card');
insert into JPOS_PoSCC values('01','ATM Card');
insert into JPOS_PoSCC values('02','Phone bar reader');


/* insert data */
insert into JPOS_Customer values ('38','1234567812345678','0');
insert into JPOS_Customer values ('39','1234567887654321','0');
insert into JPOS_Customer values ('40','8765432187654321','0');
insert into JPOS_Customer values ('41','8765432112345678','0');
insert into JPOS_Customer values ('42','0246810121416180','0');
insert into JPOS_Customer values ('43','1357911131517191','0');
insert into JPOS_Customer values ('44','1111111111111111','0');
insert into JPOS_Customer values ('45','2222222222222222','0');
insert into JPOS_Customer values ('46','3333333333333333','0');
insert into JPOS_Customer values ('47','4444444444444444','0');
insert into JPOS_Customer values ('48','5555555555555555','0');
insert into JPOS_Customer values ('49','6666666666666666','0');
insert into JPOS_Customer values ('50','7777777777777777','0');
insert into JPOS_Customer values ('51','8888888888888888','0');
insert into JPOS_Customer values ('52','9999999999999999','0');

insert into JPOS_Task(JPOS_IDTask,JPOS_TaskName) values('1','Add Point');
insert into JPOS_Task(JPOS_IDTask,JPOS_TaskName) values('2','Sub Point');
insert into JPOS_Task(JPOS_IDTask,JPOS_TaskName) values('3','Repdemtion');
insert into JPOS_Task(JPOS_IDTask,JPOS_TaskName) values('4','Balance Inquiry');

insert into JPOS_Gift values('1',N'Sony Cybershot 7.2','100');
insert into JPOS_Gift values('2',N'LCD Sony 32 Inch','150');
insert into JPOS_Gift values('3',N'Mesides Ben','240');
insert into JPOS_Gift values('4',N'Phu My Hung Home','300');