package org.hcmus.bus;

import java.util.Date;

/**
 * 
 * @author HUNGPT
 * Customer Data Transfer Object
 */
public class JPOS_CustomerDTO {
	
	/****************** Attribute *****************/
	
	//Customer Identify.
	private int jPOS_CustomerID;
	
	//First Name
	private String firstName;
	
	//Last Name
	private String lastName;
	
	//Address
	private String address;
	
	//Email
	private String email;
	
	//Date
	private Date dateJoin;
	
	//Birthday
	private Date birthDay;
	
	//Gender
	private boolean gender;
	
	//Favorite
	private String favorite;
	
	//Current point of customer.
	private int jPOS_CurrentPoint;
	
	
	/**************** Constructor ************************/
	
	public JPOS_CustomerDTO() {
		jPOS_CustomerID = 0;
		firstName = "";
		lastName = "";
		address = "";
		email = "";
		gender = false;
		favorite = "";
		jPOS_CurrentPoint = 0;
	}
	
	public JPOS_CustomerDTO(int jpos_CustomerId,String barcode,int currentPoint) {
		jPOS_CustomerID = jpos_CustomerId;
		jPOS_CurrentPoint = currentPoint;
	}
	
	
	/******************** Properties ************************/
	
	public int getJPOSID_Customer() {
		return jPOS_CustomerID;
	}
	
	public void setJPOSID_customer(int jpos_CustomerId) {
		jPOS_CustomerID = jpos_CustomerId;
	}
	
	public int getJPOS_CustomerID() {
		return jPOS_CustomerID;
	}

	public void setJPOS_CustomerID(int customerID) {
		jPOS_CustomerID = customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateJoin() {
		return dateJoin;
	}

	public void setDateJoin(Date dateJoin) {
		this.dateJoin = dateJoin;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public int getJPOS_CurrentPoint() {
		return jPOS_CurrentPoint;
	}
	
	public void setJPOS_CurrentPoint(int currentPoint) {
		jPOS_CurrentPoint = currentPoint;
	}
}
