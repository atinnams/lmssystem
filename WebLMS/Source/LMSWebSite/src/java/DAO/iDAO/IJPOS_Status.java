/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.iDAO;

import java.sql.Connection;
import java.util.ArrayList;
import DTO.*;


/**
 *
 * @author NKLapTop
 */
public interface IJPOS_Status {
    public ArrayList<DTO.DTO_JPOS_Status> GetListStatus(String strTable,Connection conn);

}
