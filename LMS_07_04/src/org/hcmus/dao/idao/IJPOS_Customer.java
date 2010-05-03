package org.hcmus.dao.idao;

import java.sql.Connection;

import org.hcmus.bus.JPOS_CustomerDTO;

/**
 * 
 * @author HUNGPT
 * Customer interface stub.
 */
public interface IJPOS_Customer {
	
	/**
	 * Get customer with customer identify.
	 * @param customerId Identify of customer.
	 * @param con connection.
	 * @return Customer result.
	 */
	public JPOS_CustomerDTO getCustomer(int customerId,Connection con);
	
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
	public int addNormalPoint(String cardNumber,int taskid, int point,String mid,String tid,String poscc,Connection con);
	
	/**
	 * Get event point and log it in log event table
	 * @param cardNumber Card number to add point
	 * @param money money to calculate point
	 * @param logId Log identify of Log table
	 * @param con Connection to LMSDB( Loyalty Manage System Database)
	 * @return point event
	 */
	public int addEventPoint(String cardNumber,float money,int logId,Connection con);
	
	
	/**
	 * Subtract point of customer to database.
	 * @param cardNumber Card Number.
	 * @param taskid which business id executed.
	 * @param point point to add.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public int subtractPoint(String cardNumber,int taskid, int point,String mid,String tid,String poscc,Connection con);
	
	/**
	 * Get current point of customer with their card id
	 * @param cardId CardId of customer
	 * @param con Connection to loyalty manage system
	 * @return Current point
	 */
	public int getCurrentPoint(String cardId,Connection con);
	
	/**
	 * Check point of customer to exchange gift.
	 * @param cardNumber Identify of card
	 * @param giftPoint Gift point which want to exchange
	 * @param con Connection of SQL server.
	 * @return
	 */
	public int checkRedemptionPoint(String cardNumber,int giftPoint,Connection con);
	
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
	public int redemption(String cardNumber,int taskid, int giftType,String mid,String tid,String poscc,Connection con);
	
}
