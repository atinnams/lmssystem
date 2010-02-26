package org.hcmus.dao.lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataProvider {
	public static Connection getConnection() {
		// Create a variable for the connection string.
	      String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
	            "databaseName=LMSDB;";
	      
	      // Declare the JDBC objects.
	      Connection con = null;
	      
	      try {
	         
	         // Establish the connection.
	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	         con = DriverManager.getConnection(connectionUrl,"sa","matkhau");
	      }catch(SQLException ex){
	    	  ex.printStackTrace();
	    	  return null;
	    	  
	      }catch(ClassNotFoundException ex) {
	    	  ex.printStackTrace();
	    	  return null;
	    	  
	      }
	      return con;
	}
}
