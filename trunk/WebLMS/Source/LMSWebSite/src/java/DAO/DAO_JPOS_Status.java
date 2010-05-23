/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import DAO.iDAO.IJPOS_Status;
import java.util.*;
import DTO.*;
import java.sql.ResultSet;
/**
 *
 * @author NKLapTop
 */
public class DAO_JPOS_Status implements IJPOS_Status {
    @Override
    public ArrayList<DTO.DTO_JPOS_Status> GetListStatus(String strTable,Connection conn)
    {
        ArrayList ArrayResult = null ;
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Get_Status(?)}");

                stmt.setString(1, strTable);
                
                boolean HasRow = stmt.execute();
                
                if (HasRow) {
                    ArrayResult = new ArrayList();
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        DTO_JPOS_Status Status = new DTO_JPOS_Status();
                        Status.setStatusCode(rs.getInt("JPOS_StatusID"));
                        Status.setStatusName(rs.getString("JPOS_StatusName"));                       
                        ArrayResult.add(Status);
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
