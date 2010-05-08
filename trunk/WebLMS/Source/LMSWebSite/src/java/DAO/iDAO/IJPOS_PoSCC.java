package DAO.iDAO;

import java.sql.Connection;

/**
 * 
 * @author HUNGPT
 * PoSCC interface stub.
 */
public interface IJPOS_PoSCC {
	
	/**
	 * Check PoSCC.
	 * @param posccid POS of service condition code identify.
	 * @param con Connection.
	 * @return result.
	 */
	public int checkPoSCC(String posccid,Connection con);
}
