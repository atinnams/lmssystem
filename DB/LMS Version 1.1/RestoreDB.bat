sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i DropDB.sql
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i CreateLMS.sql
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i Table_LMSDB.sql
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i FK_LMSDB.sql
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i InsertData.sql
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i function.sql
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i Procedure.sql