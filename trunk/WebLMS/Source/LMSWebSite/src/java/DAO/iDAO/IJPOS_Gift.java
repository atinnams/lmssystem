package DAO.iDAO;

import java.sql.Connection;
import java.util.ArrayList;

import DTO.DTO_JPOS_Gift;

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
	
	/**
	 * Get all gifts from the point of card number.
	 * @param cardId Card Number
	 * @return All gifts.
	 */
	public ArrayList<DTO_JPOS_Gift> getGifts(String cardId,Connection con);
}
