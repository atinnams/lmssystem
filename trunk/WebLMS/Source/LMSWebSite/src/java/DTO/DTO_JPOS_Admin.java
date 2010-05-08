/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;
import java.sql.Date;
/**
 *
 * @author NKLapTop
 */
public class DTO_JPOS_Admin {
    private String _Username;
    private String _Password;
    private String _FirstName;
    private String _LastName;
    private java.sql.Date _DateJoin;
    private String _Email;
    private int _LoginCount;
    private java.sql.Date _LastLogin;

    /**
     * @return the _Username
     */
    public String getUsername() {
        return _Username;
    }

    /**
     * @param Username the _Username to set
     */
    public void setUsername(String Username) {
        this._Username = Username;
    }

    /**
     * @return the _Password
     */
    public String getPassword() {
        return _Password;
    }

    /**
     * @param Password the _Password to set
     */
    public void setPassword(String Password) {
        this._Password = Password;
    }

    /**
     * @return the _FirstName
     */
    public String getFirstName() {
        return _FirstName;
    }

    /**
     * @param FirstName the _FirstName to set
     */
    public void setFirstName(String FirstName) {
        this._FirstName = FirstName;
    }

    /**
     * @return the _LastName
     */
    public String getLastName() {
        return _LastName;
    }

    /**
     * @param LastName the _LastName to set
     */
    public void setLastName(String LastName) {
        this._LastName = LastName;
    }

    /**
     * @return the _DateJoin
     */
    public java.sql.Date getDateJoin() {
        return _DateJoin;
    }

    /**
     * @param DateJoin the _DateJoin to set
     */
    public void setDateJoin(java.sql.Date DateJoin) {
        this._DateJoin = DateJoin;
    }

    /**
     * @return the _Email
     */
    public String getEmail() {
        return _Email;
    }

    /**
     * @param Email the _Email to set
     */
    public void setEmail(String Email) {
        this._Email = Email;
    }
    
    /**
     * @return the _LastLogin
     */
    public java.sql.Date getLastLogin() {
        return _LastLogin;
    }

    /**
     * @param LastLogin the _LastLogin to set
     */
    public void setLastLogin(java.sql.Date LastLogin) {
        this._LastLogin = LastLogin;
    }

    /**
     * @param LoginCount the _LoginCount to set
     */
    public void setLoginCount(int LoginCount) {
        this._LoginCount = LoginCount;
    }
}
