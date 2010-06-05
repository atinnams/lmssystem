package org.hcmus.dao.idao;

import java.sql.Connection;
import java.util.ArrayList;

import org.hcmus.bus.JPOS_GiftDTO;

/**
 * Gift stub
 * @author HUNGPT
 *
 */
public interface IJPOS_Gift {
	
	/**
	 * Check point of gift to exchange
	 * @param giftPoint Point of Gift
	 * @param con Connection
	 * @return Where have a gift corresponding with specific point 
	 */
	public int checkGiftPoint(int giftPoint,Connection con);
	
	/**
	 * Get all gifts from the point of card number.
	 * @param cardId Card Number
	 * @return All gifts.
	 */
	public ArrayList<JPOS_GiftDTO> getGifts(String cardId,Connection con);
	
	/**
	 * Get gift name from gift point
	 * @param giftPoint Point of gift
	 * @return Gift Name
	 */
	public String getGiftName(int giftPoint,Connection con);
}
