package org.hcmus.dao.idao;

import java.sql.Connection;

/**
 * 
 * @author HUNGPT
 * Card Interface stub.
 */

public interface IJPOS_Card {
	/**
	 * Check card with identify of card.
	 * @param cardId Card identify.
	 * @param con Connection.
	 * @return result.
	 */
	public int checkCard(String cardId,Connection con);
	
	/**
	 * Check expire card with card identify.
	 * @param cardId Identify of card.
	 * @param con Connection.
	 * @return result : 1 means expired and 0 didn't 
	 */
	public int checkExpire(String cardId,Connection con);
	
	/**
	 * Check card was activated.
	 * @param cardId Identify of card
	 * @param con Connection of database server.
	 * @return Checking result. 0 means that card has been activated yet but 1 means by contrast.
	 */
	public int checkActivatedCard(String cardId,Connection con);
	
	/**
	 * Activate card.
	 * @param cardId Identify of card.
	 * @param con Connection which connect to database server.
	 */
	public void activateCard(String cardId,Connection con);
	
}
