/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hcmus.dao.lms;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hcmus.bus.JPOS_AdminDTO;
import org.hcmus.dao.idao.IJPOS_Admin;
/**
 *
 * @author NKLapTop
 */
public class JPOS_AdminDAO implements IJPOS_Admin{

    public JPOS_AdminDTO Login(String strUsername, String strPassword,Connection con) {
        JPOS_AdminDTO result = null;
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
                        result = new JPOS_AdminDTO();
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
