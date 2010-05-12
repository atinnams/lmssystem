/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

/**
 *
 * @author NKLapTop
 */
public class DTO_JPOS_Terminal {
    private String _TID;
    private String _MID;
    private int _Status;
    private String _StatusName;
    private String _PIN;
    private int _Retry;
    private String _ActiveCode;
    private String _MerchantName;
    private String _MerchantAddress;

    /**
     * @return the _TID
     */
    public String getTID() {
        return _TID;
    }

    /**
     * @param TID the _TID to set
     */
    public void setTID(String TID) {
        this._TID = TID;
    }

    /**
     * @return the _MID
     */
    public String getMID() {
        return _MID;
    }

    /**
     * @param MID the _MID to set
     */
    public void setMID(String MID) {
        this._MID = MID;
    }

    /**
     * @return the _Status
     */
    public int getStatus() {
        return _Status;
    }

    /**
     * @param Status the _Status to set
     */
    public void setStatus(int Status) {
        this._Status = Status;
    }

    /**
     * @return the _StatusName
     */
    public String getStatusName() {
        return _StatusName;
    }

    /**
     * @param StatusName the _StatusName to set
     */
    public void setStatusName(String StatusName) {
        this._StatusName = StatusName;
    }

    /**
     * @return the _PIN
     */
    public String getPIN() {
        return _PIN;
    }

    /**
     * @param PIN the _PIN to set
     */
    public void setPIN(String PIN) {
        this._PIN = PIN;
    }

    /**
     * @return the _Retry
     */
    public int getRetry() {
        return _Retry;
    }

    /**
     * @param Retry the _Retry to set
     */
    public void setRetry(int Retry) {
        this._Retry = Retry;
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
     * @return the _MerchantName
     */
    public String getMerchantName() {
        return _MerchantName;
    }

    /**
     * @param MerchantName the _MerchantName to set
     */
    public void setMerchantName(String MerchantName) {
        this._MerchantName = MerchantName;
    }

    /**
     * @return the _MerchantAddress
     */
    public String getMerchantAddress() {
        return _MerchantAddress;
    }

    /**
     * @param MerchantAddress the _MerchantAddress to set
     */
    public void setMerchantAddress(String MerchantAddress) {
        this._MerchantAddress = MerchantAddress;
    }
}
