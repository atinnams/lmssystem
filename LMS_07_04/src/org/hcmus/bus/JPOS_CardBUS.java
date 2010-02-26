package org.hcmus.bus;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_Card;

public class JPOS_CardBUS {
	public static int checkCard(String cardId) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.checkCard(cardId);
	}
	
	public static int checkExpire(String cardId) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.checkExpire(cardId);
	}
}
