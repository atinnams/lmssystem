package org.hcmus.dao.lms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hcmus.bus.JPOS_CustomerDTO;
import org.hcmus.dao.idao.IJPOS_Customer;

/**
 * JPOS Customer Data Access Player
 * @author HUNGPT
 *
 */
public class JPOS_CustomerDAO implements IJPOS_Customer {

	
	@Override
	public JPOS_CustomerDTO getCustomer(int customerId,Connection con) {
		JPOS_CustomerDTO customer = new JPOS_CustomerDTO();
		String SQL = "select * from JPOS_Customer where JPOS_IDCustomer = " +  customerId;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()) {
				customer.setJPOSID_customer(customerId);
				customer.setJPOS_Barcode(rs.getString("JPOS_CardId"));
				customer.setJPOS_CurrentPoint(rs.getInt("JPOS_CurrentPoint"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}

	@Override
	public int subtractPoint(JPOS_CustomerDTO customer, int taskid, int point,
			String mid, String tid, String poscc,Connection con) {
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_Sub_Point(?,?,?,?,?,?,?)}");
				cstmt.setString("CardId", customer.getJPOS_Barcode());
				cstmt.setInt("TaskID", taskid);
				cstmt.setInt("Point", point);
				cstmt.setString("MID", mid);
				cstmt.setString("TID", tid);
				cstmt.setString("PoSCC", poscc);
				cstmt.registerOutParameter("Result",java.sql.Types.INTEGER );
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
	public int addPoint(JPOS_CustomerDTO customer, int taskid, int point,
			String mid, String tid, String poscc,Connection con) {
		
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_Add_Point(?,?,?,?,?,?,?)}");
				cstmt.setString("CardId", customer.getJPOS_Barcode());
				cstmt.setInt("TaskID", taskid);
				cstmt.setInt("Point", point);
				cstmt.setString("MID", mid);
				cstmt.setString("TID", tid);
				cstmt.setString("PoSCC", poscc);
				cstmt.registerOutParameter("Result",java.sql.Types.INTEGER );
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
	public int getCurrentPoint(String cardId,Connection con){
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_balance_inquiry(?)}");
				cstmt.registerOutParameter(1,java.sql.Types.INTEGER );
				cstmt.setString(2, cardId);
				cstmt.execute();
				result = cstmt.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}
	
	@Override
	public int checkRedemptionPoint(String cardNumber,int giftType,Connection con){
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_check_redemption_point(?,?)}");
				cstmt.registerOutParameter(1,java.sql.Types.INTEGER );
				cstmt.setString(2, cardNumber);
				cstmt.setInt(3, giftType);
				cstmt.execute();
				result = cstmt.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}

	@Override
	public int redemption(String cardNumber, int taskid, int giftType,
			String mid, String tid, String poscc, Connection con) {
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_redemption(?,?,?,?,?,?,?)}");
				cstmt.setString(1, cardNumber);
				cstmt.setInt(2, taskid);
				cstmt.setInt(3, giftType);
				cstmt.setString(4, mid);
				cstmt.setString(5, tid);
				cstmt.setString(6,poscc);
				cstmt.registerOutParameter(7,java.sql.Types.INTEGER );
				cstmt.execute();
				result = cstmt.getInt(7);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}
}
