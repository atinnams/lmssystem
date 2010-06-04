/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DAO.iDAO.IJPOS_Terminal;
import java.sql.Connection;
import java.util.ArrayList;
import DTO.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NKLapTop
 */
public class DAO_JPOS_Terminal implements IJPOS_Terminal {
    @Override
    public ArrayList<DTO_JPOS_Terminal> getListTerminal(Connection conn)
    {
         ArrayList ArrayResult = null ;
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Terminal_Report()}");


                boolean HasRow = stmt.execute();
                if (HasRow) {
                    ArrayResult = new ArrayList();
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        DTO_JPOS_Terminal terminal = new DTO_JPOS_Terminal();

                        terminal.setActiveCode(rs.getString("JPOS_ActivateCode"));
                        terminal.setMID(rs.getString("JPOS_MID"));
                        terminal.setMerchantAddress(rs.getString("JPOS_Address"));
                        terminal.setMerchantName(rs.getString("JPOS_MerchantName"));
                        terminal.setPIN(rs.getString("JPOS_PIN"));
                        terminal.setRetry(rs.getInt("JPOS_RetryLimit"));
                        terminal.setStatus(rs.getInt("JPOS_Status"));
                        terminal.setStatusName(rs.getString("JPOS_StatusName"));
                        terminal.setTID(rs.getString("JPOS_TID"));

                        ArrayResult.add(terminal);
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
    public DTO_JPOS_Terminal getTerminal(String strMID,Connection conn)
    {        
        CallableStatement stmt = null;
        DTO_JPOS_Terminal terminal = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_Terminal_Report()}");


            boolean HasRow = stmt.execute();
            if (HasRow) {                
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    terminal = new DTO_JPOS_Terminal();

                    terminal.setActiveCode(rs.getString("JPOS_ActivateCode"));
                    terminal.setMID(rs.getString("JPOS_MID"));
                    terminal.setMerchantAddress(rs.getString("JPOS_Address"));
                    terminal.setMerchantName(rs.getString("JPOS_MerchantName"));
                    terminal.setPIN(rs.getString("JPOS_PIN"));
                    terminal.setRetry(rs.getInt("JPOS_RetryLimit"));
                    terminal.setStatus(rs.getInt("JPOS_Status"));
                    terminal.setStatusName(rs.getString("JPOS_StatusName"));
                    terminal.setTID(rs.getString("JPOS_TID"));

                   
                }
            }
        } catch (Exception e) {
            System.out.println("Error!!!!!!" + e);            
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

            return terminal;
        }
    }
    @Override
    public boolean addTerminal(DTO_JPOS_Terminal terminal,Connection conn)
    {

        CallableStatement stmt = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_New_Terminal(?,?,?,?,?)}");
            stmt.setString(1, terminal.getMID());
            stmt.setInt(2, terminal.getStatus());
            stmt.setString(3, terminal.getPIN());
            stmt.setInt(4, terminal.getRetry());
            stmt.setString(5, terminal.getActiveCode());
            
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
    public boolean updateTerminal(DTO_JPOS_Terminal terminal,Connection conn)
    {
        CallableStatement stmt = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_Update_Terminal(?,?,?,?,?)}");
            stmt.setString(1, terminal.getMID());
            stmt.setInt(2, terminal.getStatus());
            stmt.setString(3, terminal.getPIN());
            stmt.setInt(4, terminal.getRetry());
            stmt.setString(5, terminal.getActiveCode());

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
    public boolean deleteTerminal(String strTID,Connection conn)
    {
        CallableStatement stmt = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_Delete_Terminal(?)}");
            stmt.setString(1, strTID);

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
    public boolean assignTerminal(String strMID,String strTID,Connection conn)
    {
         CallableStatement stmt = null;
        try {
            stmt = conn.prepareCall("{call dbo.sp_Assign_Terminal(?,?)}");
            stmt.setString(1, strTID);
            stmt.setString(2, strMID);

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
