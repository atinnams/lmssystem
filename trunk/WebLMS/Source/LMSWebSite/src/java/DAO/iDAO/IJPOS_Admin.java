/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO.iDAO;
import DTO.DTO_JPOS_Admin;
import java.sql.Connection;
import java.util.ArrayList;

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
        public boolean AddAdmin(DTO_JPOS_Admin admin, Connection cn);
        public boolean DeleteAdmin(String Username, Connection cn);
        public boolean UpdateAdmin(DTO_JPOS_Admin admin, Connection cn);
        public ArrayList<DTO_JPOS_Admin> SearchAdmin(String Key, Connection cn);
        public ArrayList<DTO_JPOS_Admin> ListAdmin(Connection cn);
        public DTO_JPOS_Admin GetAdmin(String Username, Connection cn);
}

