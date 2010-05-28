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
	
	/**
	 * Get amount of the card.
	 * @param cardNumber Card number.
	 * @param con Connection to database.
	 * @return amount of card.
	 */
	public static int getAmountCard(String cardNumber,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.getAmountCard(cardNumber, con);
	}
	
	/**
	 * subtract amount of card
	 * @param cardNumber Card Number
	 * @param amount Amount that is subtracted
	 * @param con Connection that connect to database
	 * @return
	 */
	public static int redeem(String cardNumber,int amount,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.redeem(cardNumber, amount, con);
	}
	
	/**
	 * Add amount of card
	 * @param cardNumber Card Number
	 * @param amount Amount that is subtracted
	 * @param con Connection that connect to database
	 * @return
	 */
	public static int reloadCard(String cardNumber,int amount,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.reloadCard(cardNumber, amount, con);
	}
	
	/**
	 * Check card was activated.
	 * @param cardId Identify of card
	 * @param invoiceId invoice of the log
	 * @param con Connection of database server.
	 * @return Checking result. 0 means that card has been activated yet but 1 means by contrast.
	 */
	public static int checkInvoice(String cardId,String invoiceId,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.checkInvoice(cardId, invoiceId, con);
	}
	
	/**
	 * Void amount of card
	 * @param cardNumber Card Number
	 * @param amount Amount that is subtracted
	 * @param con Connection that connect to database
	 * @return
	 */
	public static int voidCard(String cardNumber,String invoiceId,int amount,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.voidCard(cardNumber, invoiceId, amount, con);
	}
	
	/**
	 * Reversal reload.
	 * @param customer information of customer.
	 * @param taskid which business id executed.
	 * @param invoiceId identify of invoice. 
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public static void reversalRedeem(String cardNumber,int taskid,String invoiceId,String mid,String tid,String poscc,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		myCard.reversalRedeem(cardNumber, taskid, invoiceId, mid, tid, poscc, con);
	}
	
	/**
	 * Reversal reload.
	 * @param customer information of customer.
	 * @param taskid which business id executed.
	 * @param invoiceId identify of invoice. 
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public static void reversalReload(String cardNumber,int taskid,String invoiceId,String mid,String tid,String poscc,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		myCard.reversalReload(cardNumber, taskid, invoiceId, mid, tid, poscc, con);
	}
	
	/**
	 * Reversal reload.
	 * @param customer information of customer.
	 * @param taskid which business id executed.
	 * @param invoiceId identify of invoice. 
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public static void reversalVoidRedeem(String cardNumber,int taskid,String invoiceId,String mid,String tid,String poscc,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		myCard.reversalVoidRedeem(cardNumber, taskid, invoiceId, mid, tid, poscc, con);
	}
	
	/**
	 * Reversal reload.
	 * @param customer information of customer.
	 * @param taskid which business id executed.
	 * @param invoiceId identify of invoice. 
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public static void reversalVoidReload(String cardNumber,int taskid,String invoiceId,String mid,String tid,String poscc,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		myCard.reversalVoidReload(cardNumber, taskid, invoiceId, mid, tid, poscc, con);
	}
	
	
}
