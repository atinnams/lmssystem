package org.hcmus.bus;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_Merchant;

public class JPOS_MerchantBUS {
	public static int checkMerchant(String mid,String tid) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Merchant merchant = factory.getJPOS_Merchant();
		return merchant.checkMerchant(mid, tid);
	}
}
