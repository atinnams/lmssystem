package org.hcmus.bus;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_PoSCC;

public class JPOS_PoSCCBUS {
	public static int checkPoSCC(String posccid) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_PoSCC poscc = factory.getJPOS_PoSCC();
		return poscc.checkPoSCC(posccid);
	}
}
