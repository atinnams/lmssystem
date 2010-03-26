package org.hcmus.dao.idao;

import java.sql.Connection;

public interface IJPOS_PoSCC {
	public int checkPoSCC(String posccid,Connection con);
}
