/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BUS;
import DTO.DTO_JPOS_Admin;
import java.sql.Connection;

import DAO.FACTORY.LMSDAOFactory;
import DAO.iDAO.IJPOS_Admin;

/**
 *
 * @author NKLapTop
 */
public class BUS_JPOS_Admin {
    public static DTO_JPOS_Admin Login(String strUsername,String strPassword, Connection cn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
	IJPOS_Admin myAdmin = factory.getJPOS_Admin();
        return myAdmin.Login(strUsername, strPassword, cn);
    }

}
