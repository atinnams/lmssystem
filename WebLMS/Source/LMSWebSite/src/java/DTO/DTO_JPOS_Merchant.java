package DTO;

/**
 * 
 * @author HUNGPT
 * Merchant Data Transfer Object.
 */
public class DTO_JPOS_Merchant {
	/**************** Attribute *********************/
	
	//Merchant Identify.
	private String jPOS_MIT;
	
	//Terminal Identify.
	private String jPOS_TID;
	
	//Merchant name.
	private String jPOS_MerchantName;
	
	/******************************Properties *********************/
	public String getJPOS_MIT() {
		return jPOS_MIT;
	}
	public void setJPOS_MIT(String jpos_mit) {
		jPOS_MIT = jpos_mit;
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
