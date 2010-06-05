package org.hcmus.bus;

import java.sql.Connection;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_Merchant;

/**
 * 
 * @author HUNGPT
 * Merchant BUS service for Merchant DAO.
 */
public class JPOS_MerchantBUS {
	
	/**
	 * Check exist merchant.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param con Connection
	 * @return result of check : 1 pass and 0 fail 
	 */
	public static int checkMerchant(String mid,String tid,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Merchant merchant = factory.getJPOS_Merchant();
		return merchant.checkMerchant(mid, tid,con);
	}
}
