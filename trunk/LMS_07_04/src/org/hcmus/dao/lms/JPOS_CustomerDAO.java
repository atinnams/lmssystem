package org.hcmus.dao.lms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hcmus.bus.JPOS_CustomerDTO;
import org.hcmus.dao.idao.IJPOS_Customer;

public class JPOS_CustomerDAO implements IJPOS_Customer {

	Connection con = null;
	
	@Override
	public JPOS_CustomerDTO getCustomer(int customerId) {
		// TODO Auto-generated method stub
		JPOS_CustomerDTO customer = new JPOS_CustomerDTO();
		String SQL = "select * from JPOS_Customer where JPOS_IDCustomer = " +  customerId;
		Statement st;
		try {
			con = DataProvider.getConnection();
			st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()) {
				customer.setJPOSID_customer(customerId);
				customer.setJPOS_Barcode(rs.getString("JPOS_CardId"));
				customer.setJPOS_CurrentPoint(rs.getInt("JPOS_CurrentPoint"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
	}

	@Override
	public int subtractPoint(JPOS_CustomerDTO customer, int taskid, int point,
			String mid, String tid, String poscc) {
		// TODO Auto-generated method stub
		int result = -1;
		try {
			con = DataProvider.getConnection();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = -1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int addPoint(JPOS_CustomerDTO customer, int taskid, int point,
			String mid, String tid, String poscc) {
		// TODO Auto-generated method stub
		int result = -1;
		try {
			con = DataProvider.getConnection();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = -1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
