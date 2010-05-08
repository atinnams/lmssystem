/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO.iDAO;
import DTO.DTO_JPOS_Admin;
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
	public DTO_JPOS_Admin Login(String Username, String Password, Connection cn);
}
