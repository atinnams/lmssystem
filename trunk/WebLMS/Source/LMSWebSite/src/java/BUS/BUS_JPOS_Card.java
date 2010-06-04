package BUS;

import java.sql.Connection;

import DAO.FACTORY.LMSDAOFactory;
import DAO.iDAO.IJPOS_Card;
import DTO.DTO_JPOS_Card;
import java.util.ArrayList;

/**
 * 
 * @author HUNGPT
 * Bus service for JPos DAO Card
 */
public class BUS_JPOS_Card {
	
	/**
	 * Check Card ID gets from ISO 8583 message.
	 * @param cardId Card Identify.
	 * @param con  Connection.
	 * @return result after check :  1 is good and 0 is bad.
	 */
	public static int checkCard(String cardId,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.checkCard(cardId,con);
	}
	
	/**
	 * Check Expire Card with Card Id gets from ISO 8583 Message
	 * @param cardId Card Identify.
	 * @param con Connection
	 * @return 1 means expire card and 0 doesn't.
	 */
	public static int checkExpire(String cardId,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.checkExpire(cardId,con);
	}
	
	/**
	 * Check card was activated.
	 * @param cardId Identify of card
	 * @param con Connection of database server.
	 * @return Checking result. 0 means that card has been activated yet but 1 means by contrast.
	 */
	public static int checkActivatedCard(String cardId,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.checkActivatedCard(cardId, con);
	}
	
	/**
	 * Activate card.
	 * @param cardId Identify of card.
	 * @param con Connection which connect to database server.
	 */
	public static void activateCard(String cardId,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		myCard.activateCard(cardId, con);
	}
        public static ArrayList<DTO_JPOS_Card> getListCard(Connection conn){
            	LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.getListCard(conn);
        }
        public static boolean NewCard(DTO_JPOS_Card card,Connection conn){
            	LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.NewCard(card, conn);
        }
        public static boolean DeleteCard(String strCardID,Connection conn){
            	LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.DeleteCard(strCardID, conn);
        }
        public static DTO_JPOS_Card GetCard(String strCardID,Connection conn){
            	LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.GetCard(strCardID, conn);
        }
        public static boolean UpdateCard(DTO_JPOS_Card card,Connection conn){
            	LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.UpdateCard(card, conn);
        }
        public static boolean AssignCard(String strCardID,int strCustomerID,Connection conn){
            	LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.AssignCard(strCardID, strCustomerID, conn);
        }
        public static boolean StopAssignCard(String strCardID,Connection conn){
            	LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.StopAssignCard(strCardID, conn);
        }
        public static ArrayList<DTO_JPOS_Card> searchCard(String strKey,Connection con){
                LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Card myCard = factory.getJPOS_Card();
		return myCard.searchCard(strKey, con);
        }

}
