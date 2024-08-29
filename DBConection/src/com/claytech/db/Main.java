package com.claytech.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/* Connecting to the database with JDBC 
 * Running a simple select statement
 * 08/28/2024
 * Accepting role/user and password from user input 
 * Using DriverManager --> Connection --> Statement --> ResultSet
 * Using ResultSetMetaData to get column names
 * Using createStatement to pass the SQL String
 */
public class Main {

	private final static String CONN_STRING = "jdbc:postgresql://localhost:5432/postgres";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		String username = JOptionPane.showInputDialog(null, "Enter DB Username");

		JPasswordField pf = new JPasswordField();

		int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter DB password", JOptionPane.OK_CANCEL_OPTION);

		final char[] password = (okCxl == JOptionPane.OK_OPTION ? pf.getPassword() : null);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
//Not required any more. But might see old legacy code at your work place!!		
		try {
			Class.forName("org.postgresql.Drivers");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
				
			// Establish connection
		    conn = DriverManager.getConnection(CONN_STRING, username, String.valueOf(password));
			Arrays.fill(password, ' ');
			System.out.println("Connected to PostgreSQL server successfully");
			
			// Create a statement object to perform a query
			stmt = conn.createStatement();
			
			//pass the query string as parameter
			String query = "SELECT * FROM Customers";
			rs = stmt.executeQuery(query);
		
	//		var meta = rs.getMetaData();			
			ResultSetMetaData meta = rs.getMetaData();
			
			
			while(rs.next()) {
//				int customerid = rs.getInt("customer_id");
//				String name = rs.getString("name");
//				String email = rs.getString("email");
//				String phone = rs.getString("phone");
//				
//				System.out.println("Customer id: " +  customerid + 
//											", Name: " + name +
//											", Email: " + email + 
//											", Phone: " +phone);
//			
				System.out.println(meta.getColumnName(1) + " : " + rs.getString(1) + " " +
						meta.getColumnName(2) + " : " + rs.getString(2) + " " +
						meta.getColumnName(3) + " : " + rs.getString(3) + " " +
						meta.getColumnName(4) + " : " + rs.getString(4)
						);	
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			try {
				if (conn != null)
					rs.close();
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
