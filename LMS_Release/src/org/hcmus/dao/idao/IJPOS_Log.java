package org.hcmus.dao.idao;

import java.sql.Connection;

public interface IJPOS_Log {
	
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
	public void balanceInquiryLog(String cardNumber,int taskid,int amount,String mid,String tid,String poscc,Connection con);
	
	/**
	 * Add log point of customer to database.
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
	public int addRedeemLog(String cardNumber,int taskid,int amount,String invoiceId,int point,String mid,String tid,String poscc,Connection con);
	
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
	public void addVoidLog(String cardNumber,int taskid,int amount,String invoiceId,String mid,String tid,String poscc,Connection con);
	
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
	public void addReloadLog(String cardNumber,int taskid,int amount,String invoiceId,String mid,String tid,String poscc,Connection con);
}
