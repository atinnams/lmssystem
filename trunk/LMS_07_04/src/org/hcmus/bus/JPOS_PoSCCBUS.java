package org.hcmus.bus;

import java.sql.Connection;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_PoSCC;

public class JPOS_PoSCCBUS {
	public static int checkPoSCC(String posccid,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_PoSCC poscc = factory.getJPOS_PoSCC();
		return poscc.checkPoSCC(posccid,con);
	}
}
