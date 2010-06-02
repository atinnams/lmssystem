sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i "%~dp0DropDB.sql"
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i "%~dp0CreateLMS.sql"
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i "%~dp0Table_LMSDB.sql"
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i "%~dp0FK_LMSDB.sql"
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i "%~dp0InsertData.sql"
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i "%~dp0function.sql"
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i "%~dp0Procedure.sql"
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i "%~dp0web_procedure.sql"
sqlcmd -S  %computername%\SQLEXPRESS -U sa -P matkhau -i "%~dp0web_function.sql"

pause