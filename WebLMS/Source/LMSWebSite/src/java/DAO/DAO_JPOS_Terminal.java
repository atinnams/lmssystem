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
}
