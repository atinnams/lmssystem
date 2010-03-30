package org.hcmus.dao.lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hcmus.Util.LMSConfig;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

public class DataProvider {
	public static Connection getConnection() {	
		
		// Declare the JDBC objects.
		Connection con = null;

		try {
			LMSConfig dbConfig = (LMSConfig) NameRegistrar.get("MyConfigDB");
			String host = dbConfig.getHost();
			String port = dbConfig.getPort();
			String dbName = dbConfig.getDBName();

			// Create a variable for the connection string.
			// String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
			// "databaseName=LMSDB;";
			
			// Create a variable for the connection string.
			 String connectionUrl = "jdbc:sqlserver://" + host + ":" + port +";" +
			 "databaseName=" + dbName + ";";
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl, "sa", "matkhau");
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
