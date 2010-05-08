package DAO.iDAO;

import java.sql.Connection;

/**
 * 
 * @author HUNGPT
 * Merchant interface stub.
 */
public interface IJPOS_Merchant {
	
	/**
	 * Check merchant.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param con Connection.
	 * @return result.
	 */
	public int checkMerchant(String mid,String tid,Connection con);
}
