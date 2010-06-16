/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BUS;
import DTO.DTO_JPOS_Admin;
import java.sql.Connection;

import DAO.FACTORY.LMSDAOFactory;
import DAO.iDAO.IJPOS_Admin;
import java.util.ArrayList;

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
    public static boolean AddAdmin(DTO_JPOS_Admin admin, Connection cn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
	IJPOS_Admin myAdmin = factory.getJPOS_Admin();
        return myAdmin.AddAdmin(admin, cn);
    }
    public static boolean DeleteAdmin(String Username, Connection cn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
	IJPOS_Admin myAdmin = factory.getJPOS_Admin();
        return myAdmin.DeleteAdmin(Username, cn);
    }
    public static boolean UpdateAdmin(DTO_JPOS_Admin admin, Connection cn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
	IJPOS_Admin myAdmin = factory.getJPOS_Admin();
        return myAdmin.UpdateAdmin(admin, cn);
    }
    public static ArrayList<DTO_JPOS_Admin> SearchAdmin(String Key, Connection cn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
	IJPOS_Admin myAdmin = factory.getJPOS_Admin();
        return myAdmin.SearchAdmin(Key, cn);
    }
    public static ArrayList<DTO_JPOS_Admin> ListAdmin(Connection cn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
	IJPOS_Admin myAdmin = factory.getJPOS_Admin();
        return myAdmin.ListAdmin(cn);
    }
    public static DTO_JPOS_Admin GetAdmin(String Username, Connection conn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
	IJPOS_Admin myAdmin = factory.getJPOS_Admin();
        return myAdmin.GetAdmin(Username, conn);
    }

}
