/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BUS;
import DAO.FACTORY.LMSDAOFactory;
import DAO.iDAO.IJPOS_Terminal;

import DTO.*;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author NKLapTop
 */
public class BUS_JPOS_Terminal {
    public static ArrayList<DTO_JPOS_Terminal> getListTerminal(Connection conn){
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
        IJPOS_Terminal terminal = factory.getJPOS_Terminal();
        return terminal.getListTerminal(conn);
    }
    public static DTO_JPOS_Terminal getTerminal(String strMID,Connection conn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
        IJPOS_Terminal terminal = factory.getJPOS_Terminal();
        return terminal.getTerminal(strMID, conn);
    }
    public static boolean addTerminal(DTO_JPOS_Terminal NewTerminal,Connection conn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
        IJPOS_Terminal terminal = factory.getJPOS_Terminal();
        return terminal.addTerminal(NewTerminal, conn);
    }
    public static boolean updateTerminal(DTO_JPOS_Terminal NewTerminal,Connection conn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
        IJPOS_Terminal terminal = factory.getJPOS_Terminal();
        return terminal.updateTerminal(NewTerminal, conn);

    }
    public static boolean deleteTerminal(String strTID,Connection conn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
        IJPOS_Terminal terminal = factory.getJPOS_Terminal();
        return terminal.deleteTerminal(strTID, conn);
    }
    public static boolean assignTerminal(String strTID,String strMID,Connection conn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
        IJPOS_Terminal terminal = factory.getJPOS_Terminal();
        return terminal.assignTerminal(strTID, strMID, conn);
    }
    public static boolean checkTerminalExist(String strTID,Connection conn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
        IJPOS_Terminal terminal = factory.getJPOS_Terminal();
        return terminal.checkTerminalExist(strTID, conn);
    }
    public static ArrayList<DTO_JPOS_Terminal> searchTerminal(String strKey,Connection conn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
        IJPOS_Terminal terminal = factory.getJPOS_Terminal();
        return terminal.searchTerminal(strKey, conn);
    }
    public static boolean stopAssignTerminal(String strTID,Connection conn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
        IJPOS_Terminal terminal = factory.getJPOS_Terminal();
        return terminal.stopAssignTerminal(strTID, conn);
    }
}
