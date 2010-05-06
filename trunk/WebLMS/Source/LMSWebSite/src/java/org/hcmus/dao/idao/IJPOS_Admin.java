/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hcmus.dao.idao;
import org.hcmus.bus.*;
import java.sql.Connection;

/**
 *
 * @author NKLapTop
 */
public interface IJPOS_Admin {
        /**
	 * Login system with username and password
	 * @param customerId Identify of customer.
	 * @param con connection.
	 * @return Customer result.
	 */
	public JPOS_AdminDTO Login(String Username, String Password, Connection cn);
}
