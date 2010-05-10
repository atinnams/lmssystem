/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

/**
 *
 * @author NKLapTop
 */
public class DTO_Report {
    private int _LogID;
    private java.sql.Date _Date;
    private String _Task;
    private String _CardID;
    private int _CustomerID;
    private int _PointGain;
    private int _PointLoss;
    private String _PoSCCName;
    private String _TID;
    private String _MerchantName;
    private String _MerchantAddress;
    private String _GiftName;

    /**
     * @return the _LogID
     */
    public int getLogID() {
        return _LogID;
    }

    /**
     * @param LogID the _LogID to set
     */
    public void setLogID(int LogID) {
        this._LogID = LogID;
    }

    /**
     * @return the _Date
     */
    public java.sql.Date getDate() {
        return _Date;
    }

    /**
     * @param Date the _Date to set
     */
    public void setDate(java.sql.Date Date) {
        this._Date = Date;
    }

    /**
     * @return the _Task
     */
    public String getTask() {
        return _Task;
    }

    /**
     * @param Task the _Task to set
     */
    public void setTask(String Task) {
        this._Task = Task;
    }

    /**
     * @return the _CardID
     */
    public String getCardID() {
        return _CardID;
    }

    /**
     * @param CardID the _CardID to set
     */
    public void setCardID(String CardID) {
        this._CardID = CardID;
    }

    /**
     * @return the _CustomerID
     */
    public int getCustomerID() {
        return _CustomerID;
    }

    /**
     * @param CustomerID the _CustomerID to set
     */
    public void setCustomerID(int CustomerID) {
        this._CustomerID = CustomerID;
    }

    /**
     * @return the _PointGain
     */
    public int getPointGain() {
        return _PointGain;
    }

    /**
     * @param PointGain the _PointGain to set
     */
    public void setPointGain(int PointGain) {
        this._PointGain = PointGain;
    }

    /**
     * @return the _PointLoss
     */
    public int getPointLoss() {
        return _PointLoss;
    }

    /**
     * @param PointLoss the _PointLoss to set
     */
    public void setPointLoss(int PointLoss) {
        this._PointLoss = PointLoss;
    }

    /**
     * @return the _PoSCCName
     */
    public String getPoSCCName() {
        return _PoSCCName;
    }

    /**
     * @param PoSCCName the _PoSCCName to set
     */
    public void setPoSCCName(String PoSCCName) {
        this._PoSCCName = PoSCCName;
    }

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

    /**
     * @return the _GiftName
     */
    public String getGiftName() {
        return _GiftName;
    }

    /**
     * @param GiftName the _GiftName to set
     */
    public void setGiftName(String GiftName) {
        this._GiftName = GiftName;
    }
}
