/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO.iDAO;
import DTO.DTO_JPOS_Issuer;
import java.sql.*;
/**
 *
 * @author NKLapTop
 */
public interface IJPOS_Issuer {
    public DTO_JPOS_Issuer getIssuer(Connection conn);
    public boolean UpdateIssuer(DTO_JPOS_Issuer issuer, Connection conn);
}
