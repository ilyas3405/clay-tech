package com.claytech.db;

import java.sql.Statement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


import org.postgresql.ds.PGSimpleDataSource;

/* Connecting to the database with JDBC 
 * Running a simple select statement
 * 08/28/2024
 * Accepting role/user and password from Properties file and using the Properties object
 * Using DataSource --> Connection --> Statement --> ResultSet
 * Using ResultSetMetaData to get column names
 * Using prepareStatement to pass the SQL String
 * Using the setInt to assign value for the parameter
 */

public class PreparedStatementExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 String currentPath = System.getProperty("user.dir");	 
		 System.out.println(currentPath);
		 
		Properties props = new Properties();
		try {
			Path path = Path.of("SalesManagement.properties");
			
	 
			props.load(Files.newInputStream(path, StandardOpenOption.READ));
						 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		//Create a datasource 
		PGSimpleDataSource datasource = new PGSimpleDataSource();
		
		//Set the database connection properties
		datasource.setServerNames(new String[] {props.getProperty("serverName")});
		datasource.setPortNumbers(new int[] { Integer.parseInt(props.getProperty("port"))} );
		datasource.setDatabaseName( props.getProperty("databaseName") );
		datasource.setUser(props.getProperty("user") ); 
		datasource.setPassword(props.getProperty("password") ); 
			
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		//Establish connection using DataSource
		try {
			conn = datasource.getConnection();
			System.out.println("Connected to PostgreSQL server successfully using DataSource");

			//example 1 - Using formatted method of string to append a parameter
			int customer_id = 2;
			String query = "select * from customers where customer_id=%s".formatted(customer_id);
			System.out.println(query);
			
			//example 2 - using PreparedStatement
			PreparedStatement st1 = conn.prepareStatement("select * from customers where customer_id=?");
			st1.setInt(1, customer_id);
			
			String customername = "Alice Johnson";
			
			PreparedStatement st2 = conn.prepareStatement("select * from customers where name=?");
			st2.setString(1, customername);
			
			PreparedStatement st3 = conn.prepareStatement("select * from customers where name=? and customer_id=?");
			st3.setString(1, customername);
			st3.setInt(2, customer_id);
			
			
			rs = st1.executeQuery();
			while (rs.next()) {
				System.out.println("Row 1 returned");
				System.out.println(rs.getString(3));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}
