package org.hcmus.dao.lms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.hcmus.bus.JPOS_GiftDTO;
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

	@Override
	public ArrayList<JPOS_GiftDTO> getGifts(String cardId,Connection con) {
		ArrayList<JPOS_GiftDTO> lstGifts = new ArrayList<JPOS_GiftDTO>();
		String SQL = "select * " +
					 " from JPOS_Gift " +
					 " where JPOS_PointForGift < (select jCustomer.JPOS_CurrentPoint " + 
										"from JPOS_Customer jCustomer,JPOS_Card jCard " + 
										"where jCustomer.JPOS_CustomerID = jCard.JPOS_CustomerID " +
										"and jCard.JPOS_CardId = " + cardId +")";
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			JPOS_GiftDTO gift = null;
			while(rs.next()){
				gift = new JPOS_GiftDTO();
				gift.setGiftId(rs.getInt("JPOS_GiftID"));
				gift.setGiftName(rs.getNString("JPOS_GiftName"));
				gift.setPointForGift(rs.getInt("JPOS_PointForGift"));
				lstGifts.add(gift);
			}
			if(rs.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lstGifts;
	}

}
