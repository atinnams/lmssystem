use LMSDB

/*insert into JPOS_Status*/

insert into JPOS_Status values('1','Inactivated Card','JPOS_Card');
insert into JPOS_Status values('2','Activated Card','JPOS_Card');
insert into JPOS_Status values('3','Lock Card','JPOS_Card');
insert into JPOS_Status values('10','Deleted Card','JPOS_Card');
insert into JPOS_Status values('4','Inactivated Terminal','JPOS_Terminal');
insert into JPOS_Status values('5','Activated Terminal','JPOS_Terminal');
insert into JPOS_Status values('6','Lock Terminal','JPOS_Terminal');
insert into JPOS_Status values('11','Deleted Terminal','JPOS_Terminal');
insert into JPOS_Status values('7','Inactivated Customer','JPOS_Customer');
insert into JPOS_Status values('8','Activated Customer','JPOS_Customer');
insert into JPOS_Status values('9','Lock Customer','JPOS_Customer');
insert into JPOS_Status values('12','Deleted Customer','JPOS_Customer');
insert into JPOS_Status values('13','Working Merchant','JPOS_Merchant');
insert into JPOS_Status values('14','New Merchant','JPOS_Merchant');
insert into JPOS_Status values('15','Deleted Merchant','JPOS_Merchant');

/* insert data */
insert into JPOS_Customer values ('1','Hung','123456','Hung','Pham The','Tran Quoc Thao Q3 TP HCM','pthung2902@gmail.com','5/22/2010','2/29/1988',1,NULL,0,8);
insert into JPOS_Customer values ('2','Khuyen','123456','Khuyen','Nguyen','86 KP5 Trang Bom Dong Nai','nguyenkhuyan263@gmail.com','5/22/2010','3/26/1988',1,NULL,0,8);
insert into JPOS_Customer values ('3','Sui','123456','Sui','Lai','San francisco','suilai@gmail.com','5/24/2010','4/22/1984',0,NULL,0,8);
/*insert into JPOS_Card */


insert into JPOS_Card values('1234567812345678','12-31-2010','2',NULL,'0','1');
insert into JPOS_Card values('8765432187654321','12-31-2010','2',NULL,'0','1');
insert into JPOS_Card values('1234567887654321','01-31-2010','2',NULL,'0','2');
insert into JPOS_Card values('8765432112345678','12-31-2010','2',NULL,'0','2');
insert into JPOS_Card values('0246810121416180','12-31-2010','2',NULL,'0','2');
insert into JPOS_Card values('1357911131517191','12-31-2010','2',NULL,'0','1');
insert into JPOS_Card values('1111111111111111','01-31-2010','2',NULL,'0','2');
insert into JPOS_Card values('2222222222222222','12-31-2010','2',NULL,'0','2');
insert into JPOS_Card values('3333333333333333','12-31-2010','2',NULL,'0','1');
insert into JPOS_Card values('4444444444444444','12-31-2010','2',NULL,'0','2');
insert into JPOS_Card values('5555555555555555','12-31-2010','2',NULL,'0','2');
insert into JPOS_Card values('6666666666666666','12-31-2010','2',NULL,'0','2');
insert into JPOS_Card values('7777777777777777','12-31-2010','2',NULL,'0','1');
insert into JPOS_Card values('8888888888888888','12-31-2010','2',NULL,'0','2');
insert into JPOS_Card values('9999999999999999','12-31-2010','2',NULL,'0','2');
insert into JPOS_Card values('9604215000000046','12-31-2010','2',NULL,'0','2');
insert into JPOS_Card values('9704215000000046','12-31-2010','2',NULL,'0','2');
insert into JPOS_Card values('8704353300000015','12-31-2010','2',NULL,'200000','2');

/* insert into JPOS_Admin */

insert into JPOS_Admin values('admin','adminadmin','Hung','Pham',NULL,'pthung2902@gmail.com',0,'12-12-2009');

/* insert into JPOS_Issuer */

insert into JPOS_Issuer values('1','Nguyen Van Cu BookStore','231 Nguyen Van Cu 5 District HCMC VietNam','7-7-2009');

/* insert into JPOS_Merchant */

insert into JPOS_Merchant values('000000000000001',NULL,NULL,'1','13');
insert into JPOS_Merchant values('000000000000002',NULL,NULL,'1','14');
insert into JPOS_Merchant values('000000000000003',NULL,NULL,'1','14');
insert into JPOS_Merchant values('000000000000004',NULL,NULL,'1','14');
insert into JPOS_Merchant values('2              ',NULL,NULL,'1','13');
insert into JPOS_Merchant values('811009999900001',NULL,NULL,'1','13');

/* insert into JPOS_Terminal */

insert into JPOS_Terminal values('00000001','5','','','','000000000000001');
insert into JPOS_Terminal values('00000002','5','','','','000000000000001');
insert into JPOS_Terminal values('00000003','5','','','','000000000000001');
insert into JPOS_Terminal values('00000004','5','','','','000000000000002');
insert into JPOS_Terminal values('00000005','5','','','','000000000000002');
insert into JPOS_Terminal values('00000006','5','','','','000000000000002');
insert into JPOS_Terminal values('22222222','5','','','','2              ');
insert into JPOS_Terminal values('81101001','5','','','','811009999900001');


/* insert data JPOS_PoSCC */

insert into JPOS_PoSCC values('00','Loyaty Card');
insert into JPOS_PoSCC values('01','ATM Card');
insert into JPOS_PoSCC values('02','Phone bar reader');

/*insert data JPOS_Task */

insert into JPOS_Task(JPOS_TaskID,JPOS_TaskName) values('1','Redeem');
insert into JPOS_Task(JPOS_TaskID,JPOS_TaskName) values('2','Reload');
insert into JPOS_Task(JPOS_TaskID,JPOS_TaskName) values('3','Void');
insert into JPOS_Task(JPOS_TaskID,JPOS_TaskName) values('4','Balance Inquiry');
insert into JPOS_Task(JPOS_TaskID,JPOS_TaskName) values('5','Reversal');

/* Insert data JPOS_Gift */

insert into JPOS_Gift values('1','Ban Nhan Duoc Ao Thun','10',1);
insert into JPOS_Gift values('2','Ban Nhan Duoc Xe Dap','15',1);
insert into JPOS_Gift values('3','Ban Nhan Duoc May Anh','50',1);
insert into JPOS_Gift values('4','Ban Nhan Duoc Tivi','100',1);

/*Insert data JPOS_Rule */

insert into JPOS_Rule values('1','100000','1','Mua Hang Tri Gia 100000 Duoc Them Mot Diem');

/* Insert data JPOS_Event */
insert into JPOS_Event values('1','MUA HE DOC SACH 2010','04-20-2010','8-31-2010','MUA HE DOC SACH 2010','1','1');
