package org.hcmus.dao.lms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.hcmus.dao.idao.IJPOS_Card;

/**
 * 
 * @author HUNGPT
 * Card Data access player.
 */
public class JPOS_CardDAO implements IJPOS_Card {

	@Override
	public int checkCard(String cardId,Connection con) {
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_check_card(?)}");
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
	public int checkExpire(String cardId,Connection con) {
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_check_card_expire(?)}");
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
	public int checkActivatedCard(String cardId, Connection con){
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_check_activated(?)}");
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
	public void activateCard(String cardId,Connection con){
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_card_activate(?)}");
				cstmt.setString("cardid", cardId);
				cstmt.execute();
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
	}
	
	@Override
	public int getAmountCard(String cardNumber, Connection con) {
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_get_amount_card(?)}");
				cstmt.registerOutParameter(1,java.sql.Types.INTEGER );
				cstmt.setString(2, cardNumber);
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
	public int redeem(String cardNumber, int amount, Connection con) {
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ call dbo.sp_redeem_Card(?,?,?)}");
				cstmt.registerOutParameter(3,java.sql.Types.INTEGER );
				cstmt.setString(1, cardNumber);
				cstmt.setInt(2, amount);
				cstmt.execute();
				result = cstmt.getInt(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}

	@Override
	public int reloadCard(String cardNumber, int amount, Connection con) {
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ call dbo.sp_reload_Card(?,?,?)}");
				cstmt.registerOutParameter(3,java.sql.Types.INTEGER );
				cstmt.setString(1, cardNumber);
				cstmt.setInt(2, amount);
				cstmt.execute();
				result = cstmt.getInt(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}
}
