package org.hcmus.dao.lms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.hcmus.dao.idao.IJPOS_Merchant;

public class JPOS_MerchantDAO implements IJPOS_Merchant {

	Connection con = null;
	
	@Override
	public int checkMerchant(String mid, String tid) {
		// TODO Auto-generated method stub
		int result = -1;
		try {
			con = DataProvider.getConnection();
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_check_merchant(?,?)}");
				cstmt.registerOutParameter(1,java.sql.Types.INTEGER );
				cstmt.setString(2, mid);
				cstmt.setString(3, tid);
				cstmt.execute();
				
				result = cstmt.getInt(1);
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
