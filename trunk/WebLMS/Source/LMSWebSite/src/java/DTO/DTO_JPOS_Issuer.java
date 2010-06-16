/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;
import java.util.*;
/**
 *
 * @author NKLapTop
 */
public class DTO_JPOS_Issuer {
    private String _IssuerName;
    private String _IssuerAddress;
    private Date _IssuerDateFount;
    private int _IssuerID;

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

    /**
     * @return the _IssuerAddress
     */
    public String getIssuerAddress() {
        return _IssuerAddress;
    }

    /**
     * @param IssuerAddress the _IssuerAddress to set
     */
    public void setIssuerAddress(String IssuerAddress) {
        this._IssuerAddress = IssuerAddress;
    }

    /**
     * @return the _IssuerDateFount
     */
    public Date getIssuerDateFound() {
        return _IssuerDateFount;
    }

    /**
     * @param IssuerDateFount the _IssuerDateFount to set
     */
    public void setIssuerDateFound(Date IssuerDateFount) {
        this._IssuerDateFount = IssuerDateFount;
    }

    /**
     * @return the _IssuerID
     */
    public int getIssuerID() {
        return _IssuerID;
    }

    /**
     * @param IssuerID the _IssuerID to set
     */
    public void setIssuerID(int IssuerID) {
        this._IssuerID = IssuerID;
    }


}
