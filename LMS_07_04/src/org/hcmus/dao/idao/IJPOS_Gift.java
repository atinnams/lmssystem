package org.hcmus.dao.idao;

import java.sql.Connection;

/**
 * Gift stub
 * @author HUNGPT
 *
 */
public interface IJPOS_Gift {
	
	/**
	 * Get point of gift to exchange
	 * @param giftType Type of Gift
	 * @param con Connection
	 * @return Point of Gift
	 */
	public int getGiftPoint(int giftType,Connection con);
}
