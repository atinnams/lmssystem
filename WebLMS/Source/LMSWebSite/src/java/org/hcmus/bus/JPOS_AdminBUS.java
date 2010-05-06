/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hcmus.bus;
import java.sql.Connection;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_Admin;

/**
 *
 * @author NKLapTop
 */
public class JPOS_AdminBUS {
    public static JPOS_AdminDTO Login(String strUsername,String strPassword, Connection cn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
	IJPOS_Admin myAdmin = factory.getJPOS_Admin();
        return myAdmin.Login(strUsername, strPassword, cn);
    }
}
