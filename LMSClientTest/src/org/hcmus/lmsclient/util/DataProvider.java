package org.hcmus.lmsclient.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataProvider {
	public static Connection getConnection() {	
		
		// Declare the JDBC objects.
		Connection con = null;

		try {
			/* Read all configuration value from configuration file
			 * through configuration object which is registered in NameRegistrar. 
			 */
			
			 // Create a variable for the connection string.
			 String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
			 "databaseName=LMSDB;";
			 
			// Register SQL server driver and establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
