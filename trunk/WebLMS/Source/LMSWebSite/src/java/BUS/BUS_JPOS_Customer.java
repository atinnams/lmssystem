package BUS;

import DTO.DTO_JPOS_Customer;
import java.sql.Connection;

import DAO.FACTORY.LMSDAOFactory;
import DAO.iDAO.IJPOS_Customer;
import java.util.ArrayList;

/**
 * 
 * @author HUNGPT
 * Customer Bus Service for Customer DAO .
 */
public class BUS_JPOS_Customer {
	
	/**
	 * Add point of customer to database.
	 * @param customer information of customer.
	 * @param taskid which business id executed.
	 * @param point to add.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public static int addNormalPoint(String cardNumber,int taskid, int point,
			String mid,String tid,String poscc,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.addNormalPoint(cardNumber, taskid, point, mid,tid,poscc,con);
	}
	
	/**
	 * Get event point and log it in log event table
	 * @param card number to add point
	 * @param money money to calculate point
	 * @param logId Log identify of Log table
	 * @param con Connection to LMSDB( Loyalty Manage System Database)
	 * @return point event
	 */
	public static int addEventPoint(String cardNumber,float money,int logId,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.addEventPoint(cardNumber,money, logId, con);
	}
	
	/**
	 * Subtract point of customer to database.
	 * @param customer customer information of customer.
	 * @param taskid which business id executed.
	 * @param point point to add.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public static int subtractPoint(String cardNumber,int taskid, int point,
			String mid,String tid,String poscc,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.subtractPoint(cardNumber, taskid, point, mid,tid,poscc,con);
	}
	
	/**
	 * Get customer from customer identify.
	 * @param customerId Identify of customer.
	 * @param con Connection.
	 * @return Customer.
	 */
	public static DTO_JPOS_Customer getCustomer(int customerId,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.getCustomer(customerId,con);
	}
	
	/**
	 * Get current point of customer.
	 * @param cardId Card id of customer.
	 * @param con Connection
	 * @return Current point
	 */
	public static int getCurrentPoint(String cardId,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.getCurrentPoint(cardId, con);
	}
	
	/**
	 * Check point of customer to exchange gift.
	 * @param cardNumber Identify of card
	 * @param giftType Gift type which want to exchange
	 * @param con Connection of SQL server.
	 * @return 1 is okay and 0 is not.
	 */
	public static int checkRedemptionPoint(String cardNumber,int giftType,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.checkRedemptionPoint(cardNumber, giftType, con);
	}
	
	/**
	 * Redemption business
	 * @param cardNumber Identify of card
	 * @param taskid Task identify
	 * @param giftType Type of gift
	 * @param mid Merchant identify
	 * @param tid Terminal Identify
	 * @param poscc Point of Service Condition Code
	 * @param con Connection
	 * @return Result of Redemption business
	 */
	public static int redemption(String cardNumber, int taskid, int giftType,
			String mid, String tid, String poscc, Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.redemption(cardNumber, taskid, giftType, mid, tid, poscc, con);
	}

        public static ArrayList<DTO_JPOS_Customer> Search_Customer(int iCustomerID,String strFirstName,String strLastName,String strAddress,String strEmail,String strDateJoin,String strBirthDay,boolean blGender, String strFavorite,int iCurrentPoint,Connection conn)
        {
            LMSDAOFactory factory = LMSDAOFactory.getInstances();
            IJPOS_Customer myCustomer = factory.getJPOS_Customer();
            return myCustomer.Search_Customer(iCustomerID, strFirstName, strLastName, strAddress, strEmail, strDateJoin, strBirthDay, blGender, strFavorite, iCurrentPoint, conn);
        }
}
