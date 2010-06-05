package org.hcmus.dao.lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hcmus.Util.LMSConfig;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

/**
 * 
 * @author HUNGPT
 * 
 */
public class DataProvider {

	/**
	 * Read configuration parameters from configuration XML file through
	 * LMSConfig Object. After a while, Get connection with parameters which it
	 * got.
	 * 
	 * @return Connection to specify database.
	 * @see LMSConfig
	 */
	public static Connection getConnection() {

		/** Declare the JDBC objects. **/
		Connection con = null;

		try {
			
			/**
			 * Read all configuration value from configuration file through
			 * configuration object which is registered in NameRegistrar.
			 */
			LMSConfig dbConfig = (LMSConfig) NameRegistrar.get("MyConfigDB");
			String host = dbConfig.getHost();
			String port = dbConfig.getPort();
			String dbName = dbConfig.getDBName();

			/** Create a variable for the connection string. **/
			String connectionUrl = "jdbc:sqlserver://" + host + ":" + port
					+ ";" + "databaseName=" + dbName + ";";

			/** Register SQL server driver and establish the connection. **/
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			/** Get connection **/
			con = DriverManager.getConnection(connectionUrl, "sa", "matkhau");
			
			dbConfig = null; //make a change to GC deallocate dbConfig reference
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;

		} catch (NotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return con;
	}
}
