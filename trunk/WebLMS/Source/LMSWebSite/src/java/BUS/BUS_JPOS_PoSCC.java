package BUS;

import java.sql.Connection;

import DAO.FACTORY.LMSDAOFactory;
import DAO.iDAO.IJPOS_PoSCC;

/**
 * 
 * @author HUNGPT
 * PoSCC Bus service for PoSCC DAO.
 */
public class BUS_JPOS_PoSCC {
	
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
