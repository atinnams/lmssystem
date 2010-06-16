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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author NKLapTop
 */
public class DAO_JPOS_Admin implements IJPOS_Admin{

    @Override
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
    @Override
    public boolean AddAdmin(DTO_JPOS_Admin admin, Connection conn)
    {
        CallableStatement stmt = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_New_Admin(?, ?, ?, ?, ?)}");

            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            stmt.setString(3, admin.getFirstName());
            stmt.setString(4, admin.getLastName());
            stmt.setString(5, admin.getEmail());

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
    @Override
    public boolean DeleteAdmin(String Username,Connection conn)
    {
        CallableStatement stmt = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_Delete_Admin(?)}");
            
            stmt.setString(1, Username);

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
    @Override
    public boolean UpdateAdmin(DTO_JPOS_Admin admin,Connection conn)
    {
        CallableStatement stmt = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_Update_Admin(?, ?, ?, ?, ?)}");

            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            stmt.setString(3, admin.getFirstName());
            stmt.setString(4, admin.getLastName());
            stmt.setString(5, admin.getEmail());

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
    @Override
    public ArrayList<DTO_JPOS_Admin> SearchAdmin(String Key,Connection conn)
    {
        ArrayList ArrayResult = null ;
        CallableStatement stmt = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_Admin_Search(?)}");

            stmt.setString(1, Key);

            boolean HasRow = stmt.execute();
            if (HasRow) {
                ArrayResult = new ArrayList();
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    DTO_JPOS_Admin result = new DTO_JPOS_Admin();

                    result.setUsername(rs.getString("JPOS_Username"));
                    result.setPassword(rs.getString("JPOS_Password"));
                    result.setLoginCount(rs.getInt("JPOS_LoginCount"));
                    result.setLastName(rs.getString("JPOS_LastName"));
                    result.setFirstName(rs.getString("JPOS_FirstName"));
                    result.setEmail(rs.getString("JPOS_Email"));
                    result.setDateJoin(rs.getDate("JPOS_DateJoin"));
                    result.setLastLogin(rs.getDate("JPOS_LastLogin"));

                    ArrayResult.add(result);
                }
            }
        } catch (Exception e) {
            System.out.println("Error!!!!!!" + e);
            ArrayResult = null;
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

            return ArrayResult;
        }
    }
    @Override
    public ArrayList<DTO_JPOS_Admin> ListAdmin(Connection conn)
    {
        ArrayList ArrayResult = null ;
        CallableStatement stmt = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_Admin_List()}");            

            boolean HasRow = stmt.execute();
            if (HasRow) {
                ArrayResult = new ArrayList();
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    DTO_JPOS_Admin result = new DTO_JPOS_Admin();

                    result.setUsername(rs.getString("JPOS_Username"));
                    result.setPassword(rs.getString("JPOS_Password"));
                    result.setLoginCount(rs.getInt("JPOS_LoginCount"));
                    result.setLastName(rs.getString("JPOS_LastName"));
                    result.setFirstName(rs.getString("JPOS_FirstName"));
                    result.setEmail(rs.getString("JPOS_Email"));
                    result.setDateJoin(rs.getDate("JPOS_DateJoin"));
                    result.setLastLogin(rs.getDate("JPOS_LastLogin"));

                    ArrayResult.add(result);
                }
            }
        } catch (Exception e) {
            System.out.println("Error!!!!!!" + e);
            ArrayResult = null;
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

            return ArrayResult;
        }
    }
    @Override
    public DTO_JPOS_Admin GetAdmin(String Username, Connection conn)
    {
        DTO_JPOS_Admin result = null;
        CallableStatement stmt = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_Get_Admin(?)}");

            stmt.setString(1, Username);

            boolean HasRow = stmt.execute();
            if (HasRow) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
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

}
