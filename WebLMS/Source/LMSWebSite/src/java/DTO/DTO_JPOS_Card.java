package DTO;

import java.sql.Date;

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
        private String _Status;
        private String _ActiveCode;
        private int _CustomerOwnerID;
        

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

    /**
     * @return the _Status
     */
    public String getStatus() {
        return _Status;
    }

    /**
     * @param Status the _Status to set
     */
    public void setStatus(String Status) {
        this._Status = Status;
    }

    /**
     * @return the _ActiveCode
     */
    public String getActiveCode() {
        return _ActiveCode;
    }

    /**
     * @param ActiveCode the _ActiveCode to set
     */
    public void setActiveCode(String ActiveCode) {
        this._ActiveCode = ActiveCode;
    }

    /**
     * @return the _CustomerOwnerID
     */
    public int getCustomerOwnerID() {
        return _CustomerOwnerID;
    }

    /**
     * @param CustomerOwnerID the _CustomerOwnerID to set
     */
    public void setCustomerOwnerID(int CustomerOwnerID) {
        this._CustomerOwnerID = CustomerOwnerID;
    }

}
