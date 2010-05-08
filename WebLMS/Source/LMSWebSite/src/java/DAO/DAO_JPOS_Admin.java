/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DTO.DTO_JPOS_Admin;
import DAO.iDAO.IJPOS_Admin;
/**
 *
 * @author NKLapTop
 */
public class DAO_JPOS_Admin implements IJPOS_Admin{

    public DTO_JPOS_Admin Login(String strUsername, String strPassword,Connection con) {
        DTO_JPOS_Admin result = null;
        String SQL = "select * from JPOS_Admin where JPOS_Username = '" +  strUsername + "' and JPOS_Password = '" + strPassword + "'";
        Statement st;
       
        try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if(rs.wasNull())
                {
                    return result;
                }
                else
                {
                    if (rs.next())
                    {
                        result = new DTO_JPOS_Admin();
                        result.setUsername(rs.getString("JPOS_Username"));
                        result.setPassword(rs.getString("JPOS_Password"));
                        result.setLoginCount(rs.getInt("JPOS_LoginCount"));
                        result.setLastName(rs.getString("JPOS_LastName"));
                        result.setFirstName(rs.getString("JPOS_FirstName"));
                        result.setEmail(rs.getString("JPOS_Email"));
                        result.setDateJoin(rs.getDate("JPOS_DateJoin"));
                        result.setLastLogin(rs.getDate("JPOS_LastLogin"));

                    }

                }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        try {
                if(con != null) {
                        int iLog=-1;
                        CallableStatement cstmt = null;
                        cstmt = (CallableStatement) con
                                        .prepareCall("{call dbo.sp_Login(?,?,?)}");
                        cstmt.setString(1, strUsername);                        
                        cstmt.setString(2, strPassword);                        
                        cstmt.registerOutParameter(3,java.sql.Types.INTEGER );
                        cstmt.execute();
                        iLog = cstmt.getInt("Result");
                }
        } catch (SQLException e) {
                e.printStackTrace();                
        }	
        return result;
    }

}
