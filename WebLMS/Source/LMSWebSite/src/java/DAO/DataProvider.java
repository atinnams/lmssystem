package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import UTIL.LMSConfig;


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
	public static Connection getConnection(ServletConfig Config) {

		/** Declare the JDBC objects. **/
		Connection con = null;

		try {
			
			/**
			 * Read all configuration value from configuration file through
			 * configuration object which is registered in NameRegistrar.
			 */
			LMSConfig dbConfig = new LMSConfig(Config);
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

		} 
		return con;
	}
}
