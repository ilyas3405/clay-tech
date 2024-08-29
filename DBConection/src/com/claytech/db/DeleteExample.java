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
 * Accepting role/user and password from user input 
 * Using DataSource --> Connection --> Statement --> ResultSet
 * Using prepareStatement to pass the SQL String
 * Using the setInt to assign value for the parameter
 * Running a delete SQL statement and passing the customer email as parameter
 */

public class DeleteExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 
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
			
			PreparedStatement st3 = conn.prepareStatement("delete from customers where email = ?");
			st3.setString(1, "fofee@gmail.com");
			st3.execute();
			
			int recordsDeleted = st3.getUpdateCount();
			
			System.out.println("Records deleted: %s".formatted(recordsDeleted) );
	 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			try {
				 
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}
