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
	public int addPoint(JPOS_CustomerDTO customer,int taskid, int point,String mid,String tid,String poscc,Connection con);
	
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
	public int subtractPoint(JPOS_CustomerDTO customer,int taskid, int point,String mid,String tid,String poscc,Connection con);
	
	/**
	 * Get current point of customer with their card id
	 * @param cardId CardId of customer
	 * @param con Connection to loyalty manage system
	 * @return Current point
	 */
	public int getCurrentPoint(String cardId,Connection con);
}
