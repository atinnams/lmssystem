package org.hcmus.bus;

/**
 * 
 * @author HUNGPT
 * Customer Data Transfer Object
 */
public class JPOS_CustomerDTO {
	
	/****************** Attribute *****************/
	
	//Customer Identify.
	private int jPOSID_Customer;
	
	//Bar code of Card of customer.
	private String jPOS_Barcode;
	
	//Current point of customer.
	private int jPOS_CurrentPoint;
	
	
	/**************** Constructor ************************/
	
	public JPOS_CustomerDTO() {
		jPOSID_Customer = 0;
		jPOS_Barcode = "";
		jPOS_CurrentPoint = 0;
	}
	
	public JPOS_CustomerDTO(int jposid_customer,String barcode,int currentPoint) {
		jPOSID_Customer = jposid_customer;
		jPOS_Barcode = barcode;
		jPOS_CurrentPoint = currentPoint;
	}
	
	
	/******************** Properties ************************/
	
	public int getJPOSID_Customer() {
		return jPOSID_Customer;
	}
	
	public void setJPOSID_customer(int jposid_customer) {
		jPOSID_Customer = jposid_customer;
	}
	
	public String getJPOS_Barcode() {
		return jPOS_Barcode;
	}
	
	public void setJPOS_Barcode(String barcode) {
		jPOS_Barcode = barcode;
	}
	
	public int getJPOS_CurrentPoint() {
		return jPOS_CurrentPoint;
	}
	
	public void setJPOS_CurrentPoint(int currentPoint) {
		jPOS_CurrentPoint = currentPoint;
	}
}
