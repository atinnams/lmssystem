package DTO;

/**
 * 
 * @author HUNGPT
 * Merchant Data Transfer Object.
 */
public class DTO_JPOS_Merchant {
	/**************** Attribute *********************/
	
	//Merchant Identify.
	private String _jPOS_MID;
	
	//Terminal Identify.
	private String jPOS_TID;
	
	//Merchant name.
	private String jPOS_MerchantName;
        private String _Status;
 
	
	/******************************Properties *********************/
	public String getjPOS_MID() {
		return _jPOS_MID;
	}
	public void setjPOS_MID(String jPOS_MID) {
		_jPOS_MID = jPOS_MID;
	}
	
	public String getJPOS_TID() {
		return jPOS_TID;
	}
	
	public void setJPOS_TID(String jpos_tid) {
		jPOS_TID = jpos_tid;
	}
	
	public String getJPOS_MerchantName() {
		return jPOS_MerchantName;
	}
	
	public void setJPOS_MerchantName(String merchantName) {
		jPOS_MerchantName = merchantName;
	}    
}
