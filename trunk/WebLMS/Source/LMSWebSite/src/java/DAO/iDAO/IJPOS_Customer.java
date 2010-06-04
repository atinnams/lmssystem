package DAO.iDAO;

import java.sql.Connection;


import DTO.*;
import java.util.ArrayList;

/**
 * 
 * @author HUNGPT
 * Customer interface stub.
 */
public interface IJPOS_Customer {
	
	/**
	 * Get customer with customer identify.
	 * @param customerId Identify of customer.
	 * @param con connection.
	 * @return Customer result.
	 */
	public DTO_JPOS_Customer getCustomer(int customerId,Connection con);
	
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
	public int addNormalPoint(String cardNumber,int taskid, int point,String mid,String tid,String poscc,Connection con);
	
	/**
	 * Get event point and log it in log event table
	 * @param cardNumber Card number to add point
	 * @param money money to calculate point
	 * @param logId Log identify of Log table
	 * @param con Connection to LMSDB( Loyalty Manage System Database)
	 * @return point event
	 */
	public int addEventPoint(String cardNumber,float money,int logId,Connection con);
	
	
	/**
	 * Subtract point of customer to database.
	 * @param cardNumber Card Number.
	 * @param taskid which business id executed.
	 * @param point point to add.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public int subtractPoint(String cardNumber,int taskid, int point,String mid,String tid,String poscc,Connection con);
	
	/**
	 * Get current point of customer with their card id
	 * @param cardId CardId of customer
	 * @param con Connection to loyalty manage system
	 * @return Current point
	 */
	public int getCurrentPoint(String cardId,Connection con);
	
	/**
	 * Check point of customer to exchange gift.
	 * @param cardNumber Identify of card
	 * @param giftType Gift type which want to exchange
	 * @param con Connection of SQL server.
	 * @return
	 */
	public int checkRedemptionPoint(String cardNumber,int giftType,Connection con);
	
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
	public int redemption(String cardNumber,int taskid, int giftType,String mid,String tid,String poscc,Connection con);


        public ArrayList<DTO_JPOS_Customer> Search_Customer(int CustomerID,String FirstName,String LastName,String Address,String Email,String DateJoin,String BirthDay,boolean Gender, String Favorite,int CurrentPoint,Connection con);
        public ArrayList<DTO_Report> Transaction_Detail(int iCustomerID,Connection conn) ;
        public DTO_JPOS_Customer GetCustomerInfor(int iCustomerID,Connection conn) ;
        public ArrayList<DTO_JPOS_Customer> GetCustomerList(Connection conn);
        public int GenerateCustomerID(Connection conn);
        public boolean AddCustomer(DTO_JPOS_Customer customer, Connection conn);
        public boolean DeleteCustomer(int CustomerID, Connection conn);
        public boolean UpdateCustomer(DTO_JPOS_Customer customer, Connection conn);
        public boolean CheckCustomerEmailExist(String strEmail,Connection conn);
        public ArrayList<DTO_JPOS_Customer> Search_Customer(String strKey, Connection conn);
        

}
