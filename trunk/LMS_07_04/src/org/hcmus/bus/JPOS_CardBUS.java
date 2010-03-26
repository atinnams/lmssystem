package org.hcmus.bus;

import java.sql.Connection;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_Card;

public class JPOS_CardBUS {
	public static int checkCard(String cardId,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.checkCard(cardId,con);
	}
	
	public static int checkExpire(String cardId,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.checkExpire(cardId,con);
	}
}
