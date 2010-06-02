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
        private String _Address;
        private String _Status;
        private int _StatusCode;
        private int _IssuerCode;
        private String _IssuerName;
        
 
	
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

    /**
     * @return the _Address
     */
    public String getAddress() {
        return _Address;
    }

    /**
     * @param Address the _Address to set
     */
    public void setAddress(String Address) {
        this._Address = Address;
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
     * @return the _StatusCode
     */
    public int getStatusCode() {
        return _StatusCode;
    }

    /**
     * @param StatusCode the _StatusCode to set
     */
    public void setStatusCode(int StatusCode) {
        this._StatusCode = StatusCode;
    }

    /**
     * @return the _IssuerCode
     */
    public int getIssuerCode() {
        return _IssuerCode;
    }

    /**
     * @param IssuerCode the _IssuerCode to set
     */
    public void setIssuerCode(int IssuerCode) {
        this._IssuerCode = IssuerCode;
    }

    /**
     * @return the _IssuerName
     */
    public String getIssuerName() {
        return _IssuerName;
    }

    /**
     * @param IssuerName the _IssuerName to set
     */
    public void setIssuerName(String IssuerName) {
        this._IssuerName = IssuerName;
    }
}
