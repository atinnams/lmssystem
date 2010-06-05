package org.hcmus.bus;

import java.sql.Connection;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_Log;

public class JPOS_LogBUS {
	
	/**
	 * Add point of customer to database.
	 * @param cardNumber card identify.
	 * @param taskid which business id executed.
	 * @param amount Amount of redeem.
	 * @param invoiceId identify of invoice. 
	 * @param point to add.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public static void balanceInquiryLog(String cardNumber,int taskid,int amount,String mid,String tid,String poscc,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Log myLog = factory.getJPOS_Log();
		myLog.balanceInquiryLog(cardNumber, taskid, amount, mid, tid, poscc, con);
	}
	
	/**
	 * Add point of customer to database.
	 * 
	 * @param customer information of customer.
	 * @param taskid which business id executed.
	 * @param point to add.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public static int addRedeemLog(String cardNumber, int taskid, int amount,
			String invoiceId, int point, String mid, String tid, String poscc,
			Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Log myLog = factory.getJPOS_Log();
		return myLog.addRedeemLog(cardNumber, taskid,amount,invoiceId, point, mid, tid,
				poscc, con);
	}
	
	/**
	 * Add point of customer to database.
	 * @param customer information of customer.
	 * @param taskid which business id executed.
	 * @param amount Amount of redeem.
	 * @param invoiceId identify of invoice. 
	 * @param point to add.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public static void addVoidLog(String cardNumber,int taskid,int amount,String invoiceId,String mid,String tid,String poscc,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Log myLog = factory.getJPOS_Log();
		myLog.addVoidLog(cardNumber, taskid, amount, invoiceId, mid, tid, poscc, con);
	}
	
	/**
	 * Add point of customer to database.
	 * @param customer information of customer.
	 * @param taskid which business id executed.
	 * @param amount Amount of redeem.
	 * @param invoiceId identify of invoice. 
	 * @param point to add.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param poscc POS of service condition.
	 * @param con Connection.
	 * @return result of add point business.
	 */
	public static void addReloadLog(String cardNumber,int taskid,int amount,String invoiceId,String mid,String tid,String poscc,Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Log myLog = factory.getJPOS_Log();
		myLog.addReloadLog(cardNumber, taskid, amount, invoiceId, mid, tid, poscc, con);
	}
}
