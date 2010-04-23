package org.hcmus.bus;

import java.sql.Connection;
import java.util.ArrayList;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_Gift;

/**
 * Gift BUS service
 * @author HUNGPT
 *
 */
public class JPOS_GiftBUS {
	
	/**
	 * Get point of gift to exchange
	 * @param giftType Type of Gift
	 * @param con Connection
	 * @return Point of Gift
	 */
	public static int getGiftPoint(int giftType, Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Gift myGift = factory.getJPOS_Gift();
		return myGift.getGiftPoint(giftType, con);
	}
	
	/**
	 * Get all gifts from the point of card number.
	 * @param cardId Card Number
	 * @return All gifts.
	 */
	public static ArrayList<JPOS_GiftDTO> getGifts(String cardId,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Gift myGift = factory.getJPOS_Gift();
		return myGift.getGifts(cardId, con);
	}
}
