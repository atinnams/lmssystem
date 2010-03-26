package org.hcmus.dao.idao;

import java.sql.Connection;


public interface IJPOS_Card {
	public int checkCard(String cardId,Connection con);
	public int checkExpire(String cardId,Connection con);
}
