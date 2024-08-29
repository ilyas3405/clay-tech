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
 * Accepting role/user and password from Properties file 
 * Using DataSource --> Connection --> Stateme
 * Using prepareStatement to pass the SQL Insert statement
 * Using the setString to pass the three table values
 */

public class InsertExample {

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
		
		
		//Establish connection using DataSource
		try {
			conn = datasource.getConnection();
			System.out.println("Connected to PostgreSQL server successfully using DataSource");

			//example 1 - Using formatted method of string to append multiple parameters
			
			PreparedStatement stmt = conn.prepareStatement("insert into customers (name, email, phone) values (?,?,?)");
			stmt.setString(1, "fo fee");
			stmt.setString(2, "fofee@gmail.com");
			stmt.setString(3, "123-456-7890");
			
			boolean status = stmt.execute();
			System.out.println(status);
 					
			
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
