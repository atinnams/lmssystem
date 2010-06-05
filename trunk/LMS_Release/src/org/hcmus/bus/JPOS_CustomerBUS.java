package org.hcmus.bus;

import java.sql.Connection;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_Customer;

/**
 * 
 * @author HUNGPT Customer Bus Service for Customer DAO .
 */
public class JPOS_CustomerBUS {

	/**
	 * Get customer from customer identify.
	 * @param customerId Identify of customer.
	 * @param con Connection.
	 * @return Customer.
	 */
	public static JPOS_CustomerDTO getCustomer(int customerId, Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.getCustomer(customerId, con);
	}

	/**
	 * Get current point of customer.
	 * 
	 * @param cardId Card id of customer.
	 * @param con Connection
	 * @return Current point
	 */
	public static int getCurrentPoint(String cardId, Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.getCurrentPoint(cardId, con);
	}
}
