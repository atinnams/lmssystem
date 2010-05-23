/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author NKLapTop
 */
public class DTO_JPOS_Status {
    private int _StatusCode;
    private String _StatusName;

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
}
