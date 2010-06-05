package test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hcmus.Util.LMSConfig;

public class DataProviderTest {
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

			/** Create a variable for the connection string. **/
			String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=LMSDB;";

			/** Register SQL server driver and establish the connection. **/
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			/** Get connection **/
			con = DriverManager.getConnection(connectionUrl, "sa", "matkhau");
			
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
