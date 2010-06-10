package DTO;

public class DTO_JPOS_Log {

    private int _LogID;
    private java.sql.Date _Date;
    private int _Task;
    private String _CardID;
    private int _PointGain;
    private int _PointLoss;
    private String _TID;
    private String _MID;
    private String _PoSCC_ID;
   

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
    public int getTask() {
        return _Task;
    }

    /**
     * @param Task the _Task to set
     */
    public void setTask(int Task) {
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
     * @return the _PoSCC_ID
     */
    public String getPoSCC_ID() {
        return _PoSCC_ID;
    }

    /**
     * @param PoSCC_ID the _PoSCC_ID to set
     */
    public void setPoSCC_ID(String PoSCC_ID) {
        this._PoSCC_ID = PoSCC_ID;
    }

    
}
