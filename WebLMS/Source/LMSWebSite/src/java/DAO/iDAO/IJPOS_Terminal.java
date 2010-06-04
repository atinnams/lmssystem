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
    public ArrayList<DTO_JPOS_Terminal> getListTerminal(Connection conn);
    public DTO_JPOS_Terminal getTerminal(String strMID,Connection conn);
    public boolean addTerminal(DTO_JPOS_Terminal terminal,Connection conn);
    public boolean updateTerminal(DTO_JPOS_Terminal terminal,Connection conn);
    public boolean deleteTerminal(String strTID,Connection conn);
    public boolean assignTerminal(String strTID,String strMID,Connection conn);
    public boolean checkTerminalExist(String strTID,Connection conn);
    public ArrayList<DTO_JPOS_Terminal> searchTerminal(String strKey,Connection conn);
}
