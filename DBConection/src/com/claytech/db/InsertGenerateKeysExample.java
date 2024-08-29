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
 * Using DataSource --> Connection --> Statement
 * Using prepareStatement to pass the SQL Insert statement
 * Getting the auto generated value back after inserting the customer information
 */

public class InsertGenerateKeysExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Get yor java application current running path.
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

		//Create a PGSimpleDataSource 
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

			PreparedStatement stmt = conn.prepareStatement("");
		 
			stmt.execute("insert into customers (name, email, phone) values ('Foo Fee','fofee@gmail.com','123-456-7890')", Statement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = stmt.getGeneratedKeys();
	 
			int customer_id = (rs != null && rs.next()) ? rs.getInt(1) : -1;
			System.out.println("New Customer Inserted : %s".formatted(customer_id));
			
			
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
