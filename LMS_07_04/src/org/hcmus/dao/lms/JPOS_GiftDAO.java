package org.hcmus.dao.lms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.hcmus.dao.idao.IJPOS_Gift;

public class JPOS_GiftDAO implements IJPOS_Gift {

	@Override
	public int getGiftPoint(int giftType, Connection con) {
		int result = -1;
		try {
			if (con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_get_gift_point(?)}");
				cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
				cstmt.setInt(2, giftType);
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
