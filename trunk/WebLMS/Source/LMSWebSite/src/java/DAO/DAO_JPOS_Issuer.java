/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import DAO.iDAO.*;
import DTO.DTO_JPOS_Issuer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
/**
 *
 * @author NKLapTop
 */
public class DAO_JPOS_Issuer implements IJPOS_Issuer {

    @Override
    public DTO_JPOS_Issuer getIssuer(Connection conn)    
    {
        DTO_JPOS_Issuer result = null;
        CallableStatement stmt = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_Get_Issuer()}");
            

            boolean HasRow = stmt.execute();
            if (HasRow) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    result = new DTO_JPOS_Issuer();
                    result.setIssuerName(rs.getString("JPOS_IssuerName"));
                    result.setIssuerAddress(rs.getString("JPOS_IssuerAddress"));
                    result.setIssuerDateFound(rs.getDate("JPOS_DateFound"));
                   
                }
            }
        } catch (Exception e) {
            System.out.println("Error!!!!!!" + e);
            result = null;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }

            return result;
        }
    }

    @Override
    public boolean UpdateIssuer(DTO_JPOS_Issuer issuer, Connection conn)
    {
        CallableStatement stmt = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_Update_Issuer(?, ?, ?)}");

            SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
            String DateFound = myformat.format(issuer.getIssuerDateFound());
            
            stmt.setString(1, issuer.getIssuerName());
            stmt.setString(2, issuer.getIssuerAddress());
            stmt.setString(3, DateFound);
            
            

            stmt.execute();
        } catch (Exception e) {
            System.out.println("Error!!!!!!" + e);
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }

        }
        return true;
    }


}
