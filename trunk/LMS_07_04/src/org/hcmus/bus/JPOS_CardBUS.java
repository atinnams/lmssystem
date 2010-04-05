package org.hcmus.bus;

import java.sql.Connection;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_Card;

/**
 * 
 * @author HUNGPT
 * Bus service for JPos DAO Card
 */
public class JPOS_CardBUS {
	
	/**
	 * Check Card ID gets from ISO 8583 message.
	 * @param cardId Card Identify.
	 * @param con  Connection.
	 * @return result after check :  1 is good and 0 is bad.
	 */
	public static int checkCard(String cardId,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.checkCard(cardId,con);
	}
	
	/**
	 * Check Expire Card with Card Id gets from ISO 8583 Message
	 * @param cardId Card Identify.
	 * @param con Connection
	 * @return 1 means expire card and 0 doesn't.
	 */
	public static int checkExpire(String cardId,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.checkExpire(cardId,con);
	}
	
	/**
	 * Check card was activated.
	 * @param cardId Identify of card
	 * @param con Connection of database server.
	 * @return Checking result. 0 means that card has been activated yet but 1 means by contrast.
	 */
	public static int checkActivatedCard(String cardId,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.checkActivatedCard(cardId, con);
	}
	
	/**
	 * Activate card.
	 * @param cardId Identify of card.
	 * @param con Connection which connect to database server.
	 */
	public static void activateCard(String cardId,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		myCard.activateCard(cardId, con);
	}
}
