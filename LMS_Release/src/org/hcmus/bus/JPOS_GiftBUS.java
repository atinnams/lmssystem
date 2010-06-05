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
	 * Check point of gift to exchange
	 * @param giftPoint Point of Gift
	 * @param con Connection
	 * @return Where gift corresponds with specific point or not
	 */
	public static int checkGiftPoint(int giftPoint, Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Gift myGift = factory.getJPOS_Gift();
		return myGift.checkGiftPoint(giftPoint, con);
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
	
	/**
	 * Get gift name from gift point
	 * @param giftPoint Point of gift
	 * @return Gift Name
	 */
	public static String getGiftName(int giftPoint,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Gift myGift = factory.getJPOS_Gift();
		return myGift.getGiftName(giftPoint, con);
	}
}
