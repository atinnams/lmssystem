package org.hcmus.bus;

public class JPOS_CustomerDTO {
	
	private int jPOSID_Customer;
	private String jPOS_Barcode;
	private int jPOS_CurrentPoint;
	
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
