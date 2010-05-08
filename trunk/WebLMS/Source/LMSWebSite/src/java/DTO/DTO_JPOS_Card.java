package DTO;

import java.util.Date;

/**
 * 
 * @author HUNGPT Card Data Transfer Object
 */
public class DTO_JPOS_Card {

	/********** Attribute ****************/

	// Identify of Card
	private String jPOS_CardId;

	// Expire Day of Card
	private Date jPOS_ExpireDay;

	// Either activation or not
	private int jPOS_IsActivate;

	/*************** Properties *******************/

	public String getJPOS_CardId() {
		return jPOS_CardId;
	}

	public void setJPOS_CardId(String cardId) {
		jPOS_CardId = cardId;
	}

	public Date getJPOS_ExpireDay() {
		return jPOS_ExpireDay;
	}

	public void setJPOS_ExpireDay(Date expireDay) {
		jPOS_ExpireDay = expireDay;
	}

	public int getJPOS_IsActivate() {
		return jPOS_IsActivate;
	}

	public void setJPOS_IsActivate(int isActivate) {
		jPOS_IsActivate = isActivate;
	}

}
