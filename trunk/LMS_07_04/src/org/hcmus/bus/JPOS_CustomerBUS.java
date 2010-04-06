package org.hcmus.bus;

import java.sql.Connection;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_Customer;

/**
 * 
 * @author HUNGPT
 * Customer Bus Service for Customer DAO .
 */
public class JPOS_CustomerBUS {
	
	/**
	 * Add point of customer to database.
	 * @param customer information of customer.
	 * @param taskid which business id executed.
	 * @param point to add.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public static int addPoint(JPOS_CustomerDTO customer,int taskid, int point,
			String mid,String tid,String poscc,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.addPoint(customer, taskid, point, mid,tid,poscc,con);
	}
	
	/**
	 * Subtract point of customer to database.
	 * @param customer customer information of customer.
	 * @param taskid which business id executed.
	 * @param point point to add.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public static int subtractPoint(JPOS_CustomerDTO customer,int taskid, int point,
			String mid,String tid,String poscc,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.subtractPoint(customer, taskid, point, mid,tid,poscc,con);
	}
	
	/**
	 * Get customer from customer identify.
	 * @param customerId Identify of customer.
	 * @param con Connection.
	 * @return Customer.
	 */
	public static JPOS_CustomerDTO getCustomer(int customerId,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.getCustomer(customerId,con);
	}
	
	/**
	 * Get current point of customer.
	 * @param cardId Card id of customer.
	 * @param con Connection
	 * @return Current point
	 */
	public static int getCurrentPoint(String cardId,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.getCurrentPoint(cardId, con);
	}
	
	/**
	 * Check point of customer to exchange gift.
	 * @param cardNumber Identify of card
	 * @param giftType Gift type which want to exchange
	 * @param con Connection of SQL server.
	 * @return 1 is okay and 0 is not.
	 */
	public static int checkRedemptionPoint(String cardNumber,int giftType,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.checkRedemptionPoint(cardNumber, giftType, con);
	}
	
	/**
	 * Redemption business
	 * @param cardNumber Identify of card
	 * @param taskid Task identify
	 * @param giftType Type of gift
	 * @param mid Merchant identify
	 * @param tid Terminal Identify
	 * @param poscc Point of Service Condition Code
	 * @param con Connection
	 * @return Result of Redemption business
	 */
	public static int redemption(String cardNumber, int taskid, int giftType,
			String mid, String tid, String poscc, Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.redemption(cardNumber, taskid, giftType, mid, tid, poscc, con);
	}
}
