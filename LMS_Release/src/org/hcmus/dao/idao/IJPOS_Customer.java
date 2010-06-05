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
	 * Get current point of customer with their card id
	 * @param cardId CardId of customer
	 * @param con Connection to loyalty manage system
	 * @return Current point
	 */
	public int getCurrentPoint(String cardId,Connection con);
	
}
