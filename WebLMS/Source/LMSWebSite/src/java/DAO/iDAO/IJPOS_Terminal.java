/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO.iDAO;

import java.util.ArrayList;
import DTO.*;
import java.sql.Connection;
/**
 *
 * @author NKLapTop
 */
public interface IJPOS_Terminal {
    public ArrayList<DTO_JPOS_Terminal> getListTerminal(Connection con);
}
