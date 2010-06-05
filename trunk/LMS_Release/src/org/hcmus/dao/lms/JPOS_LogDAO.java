package org.hcmus.dao.lms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.hcmus.dao.idao.IJPOS_Log;

public class JPOS_LogDAO implements IJPOS_Log {

	@Override
	public void balanceInquiryLog(String cardNumber, int taskid,int amount, String mid,
			String tid, String poscc, Connection con) {
		try {
			if (con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_balance_inquiry_log(?,?,?,?,?,?)}");
				cstmt.setString("CardId", cardNumber);
				cstmt.setInt("TaskID", taskid);
				cstmt.setInt("Amount", amount);
				cstmt.setString("MID", mid);
				cstmt.setString("TID", tid);
				cstmt.setString("PoSCC", poscc);
				cstmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addRedeemLog(String cardNumber, int taskid, int amount,
			String invoiceId, int point, String mid, String tid, String poscc,
			Connection con) {

		int result = -1;
		try {
			if (con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_add_n_log_point(?,?,?,?,?,?,?,?,?)}");
				cstmt.setString("CardId", cardNumber);
				cstmt.setInt("TaskID", taskid);
				cstmt.setInt("Amount", amount);
				cstmt.setString("invoiceId",invoiceId );
				cstmt.setInt("Point", point);
				cstmt.setString("MID", mid);
				cstmt.setString("TID", tid);
				cstmt.setString("PoSCC", poscc);
				cstmt.registerOutParameter("Result", java.sql.Types.INTEGER);
				cstmt.execute();

				result = cstmt.getInt("Result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}

		return result;
	}

	@Override
	public void addVoidLog(String cardNumber, int taskid, int amount,
			String invoiceId, String mid, String tid, String poscc,
			Connection con) {
		try {
			if (con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_void_log(?,?,?,?,?,?,?)}");
				cstmt.setString("CardId", cardNumber);
				cstmt.setInt("TaskID", taskid);
				cstmt.setInt("Amount", amount);
				cstmt.setString("invoiceId",invoiceId );
				cstmt.setString("MID", mid);
				cstmt.setString("TID", tid);
				cstmt.setString("PoSCC", poscc);
				cstmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
	}

	@Override
	public void addReloadLog(String cardNumber, int taskid, int amount,
			String invoiceId, String mid, String tid, String poscc,
			Connection con) {
		try {
			if (con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_reload_log(?,?,?,?,?,?,?)}");
				cstmt.setString("CardId", cardNumber);
				cstmt.setInt("TaskID", taskid);
				cstmt.setInt("Amount", amount);
				cstmt.setString("InvoiceId",invoiceId );
				cstmt.setString("MID", mid);
				cstmt.setString("TID", tid);
				cstmt.setString("PoSCC", poscc);
				cstmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
	}
}
