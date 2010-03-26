package org.hcmus.dao.lms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.hcmus.dao.idao.IJPOS_PoSCC;

public class JPOS_PoSCCDAO implements IJPOS_PoSCC {
	
	@Override
	public int checkPoSCC(String posccid,Connection con) {
		// TODO Auto-generated method stub
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_check_poscc(?)}");
				cstmt.registerOutParameter(1,java.sql.Types.INTEGER );
				cstmt.setString(2, posccid);
				cstmt.execute();
				
				result = cstmt.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = -1;
		} 
		
		return result;
	}

}
