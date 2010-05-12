package DAO.iDAO;


import DTO.*;
import java.sql.*;
import java.util.ArrayList ;

public interface IJPOS_Log {
	public DTO_JPOS_Log getLog(int logId);
        public ArrayList<DTO.DTO_Report> getReportLog(Connection conn);
}
