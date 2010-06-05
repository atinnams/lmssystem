package org.hcmus.util;

public class Constant {
	public final static String REQUEST = "REQUEST";
	public final static String SOURCE = "SOURCE";
	public final static String QUEUE = "TXNMGRTEST";
	public final static String RC = "RESPONSECODE";
	public final static String CONN = "CONNECTION";
	public final static String POINT = "POINT";
	public final static String AMOUNT = "AMOUNT";
	public final static String ADVERTISE = "ADVERTISEMENT";
	
	//Message which send back to client
	public final static String MID_OR_TID_NOT_FOUND = "MID OR TID NOT FOUND";
	public final static String CARD_NOT_FOUND = "INVALID CARD NUMBER";
	public final static String POSCC_NOT_FOUND = "POSCC NOT FOUND";
	public final static String OTHER_ERROR = "OTHER ERROR";
	public final static String EXPIRE_CARD = "EXPIRE CARD";
	public final static String SUCCESFULL = "TRANSACTION SUCESSFULLY";
	public final static String INVALID_FIELD = "INVALID FIELD";
	public final static String ACTIVATED_CARD = "CARD WAS ACTIVATED";
	public final static String FORWARD_FAIL = "FORWARD_FAIL";
	public final static String NO_ACTIVATED_CARD = "CARD WASN'T ACTIVATED ";
	public final static String NOT_ENOUGH_POINT = "NOT_ENOUGH_POINT";
	public final static String NO_GIFT = "BAN CHUA DU DIEM DE NHAN QUA.";
	public final static String NOT_ENOUGH_MONEY = "NOT_ENOUGH_MONEY";
	public final static String INVOICE_FAIL = "INVOICE_FAIL";
	public final static String RELOAD_FAIL = "RELOAD_FAIL";
	public final static String TRANSACTION_REVERSAL = "REVERSALING_TRANSACTION";
	
	public final static String ERROR_FLOW = "000000" ;
	public final static String REDEEM_PROCESS = "027207";
	public final static String BALANCE_INQUIRY_PROCESS = "027567";
	public final static String VOID_REDEEM_PROCESS = "027208";
	public final static String VOID_RELOAD_PROCESS = "027708";
	public final static String RELOAD_PROCESS = "027707";
	public final static String REVERSAL_PROCESS = "020400";
	public final static String REVERSAL_MTI = "0400";
	public final static String SQL_DB = "SQL";
}
