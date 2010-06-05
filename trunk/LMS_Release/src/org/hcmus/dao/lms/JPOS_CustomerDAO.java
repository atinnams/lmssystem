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
 * 
 * @author HUNGPT
 * 
 */
public class JPOS_CustomerDAO implements IJPOS_Customer {

	@Override
	public JPOS_CustomerDTO getCustomer(int customerId, Connection con) {
		JPOS_CustomerDTO customer = new JPOS_CustomerDTO();
		String SQL = "select * from JPOS_Customer where JPOS_CustomerID = "
				+ customerId;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			if (rs.next()) {
				customer.setJPOSID_customer(customerId);
				customer.setFirstName(rs.getString("JPOS_FirstName"));
				customer.setLastName(rs.getString("JPOS_LastName"));
				customer.setAddress(rs.getString("JPOS_Address"));
				customer.setEmail(rs.getString("JPOS_Email"));
				customer.setDateJoin(rs.getDate("JPOS_DateJoin"));
				customer.setBirthDay(rs.getDate("JPOS_BirthDay"));
				customer.setGender(rs.getBoolean("JPOS_Gender"));
				customer.setFavorite(rs.getNString("JPOS_Favorite"));
				customer.setJPOS_CurrentPoint(rs.getInt("JPOS_CurrentPoint"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customer;
	}

	@Override
	public int getCurrentPoint(String cardId, Connection con) {
		int result = -1;
		try {
			if (con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_balance_inquiry(?)}");
				cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
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
}
