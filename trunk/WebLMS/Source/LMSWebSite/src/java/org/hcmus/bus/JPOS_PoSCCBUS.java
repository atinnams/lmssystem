package org.hcmus.bus;

import java.sql.Connection;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_PoSCC;

/**
 * 
 * @author HUNGPT
 * PoSCC Bus service for PoSCC DAO.
 */
public class JPOS_PoSCCBUS {
	
	/**
	 * Check PoSCC
	 * @param posccid Identify of POS of service condition code .
	 * @param con Connection.
	 * @return check result.
	 */
	public static int checkPoSCC(String posccid,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_PoSCC poscc = factory.getJPOS_PoSCC();
		return poscc.checkPoSCC(posccid,con);
	}
}
