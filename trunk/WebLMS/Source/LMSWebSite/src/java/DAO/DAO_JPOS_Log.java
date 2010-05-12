package DAO;

import DTO.DTO_JPOS_Log;
import DAO.iDAO.IJPOS_Log;
import DTO.*;
import java.sql.CallableStatement;
import java.util.ArrayList ;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_JPOS_Log implements IJPOS_Log {

	@Override
	public DTO_JPOS_Log getLog(int logId) {
		// TODO Auto-generated method stub
		return null;
	}
        @Override
        public ArrayList<DTO.DTO_Report> getReportLog(Connection conn){
            ArrayList ArrayResult = null ;
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Transaction_Report()}");
                
                boolean HasRow = stmt.execute();
                if (HasRow) {
                    ArrayResult = new ArrayList();
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        DTO_Report report = new DTO_Report();

                        report.setCardID(rs.getString("JPOS_CardID"));
                        report.setCustomerID(rs.getInt("JPOS_CustomerID"));
                        report.setDate(rs.getDate("JPOS_Date"));
                        report.setGiftName(rs.getString("JPOS_GiftName"));
                        report.setLogID(rs.getInt("JPOS_LogID"));
                        report.setMerchantAddress(rs.getString("JPOS_Address"));
                        report.setMerchantName(rs.getString("JPOS_MerchantName"));
                        report.setPoSCCName(rs.getString("JPOS_PoSCC_Name"));
                        report.setPointLoss(rs.getInt("JPOS_PointLoss"));
                        report.setPointGain(rs.getInt("JPOS_PointGain"));
                        report.setTID(rs.getString("JPOS_TID"));
                        report.setTask(rs.getString("JPOS_TaskName"));

                        ArrayResult.add(report);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error!!!!!!" + e);
                ArrayResult = null;
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                }

                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                }

                return ArrayResult;
            }
        }
	
}
