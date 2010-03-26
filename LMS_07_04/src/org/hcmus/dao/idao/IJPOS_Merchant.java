package org.hcmus.dao.idao;

import java.sql.Connection;

public interface IJPOS_Merchant {
	public int checkMerchant(String mid,String tid,Connection con);
}
