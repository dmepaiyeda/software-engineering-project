package com.com1028.myassignment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Creates a class for connecting to the SQL database.
 * @author deborahmepaiyeda
 *
 */

public class DAO {
	
	//Local variables
	protected Statement statement;
	protected Connection connect;
	
	/**
	 * This constructor initializes the DAO object.
	 */
	public DAO() {
		super();
		this.connect = null;
		this.statement = null;
	}

	/**
	 * This method opens a connection to the SQL database.
	 * @param db_user The database user name. 
	 * @param db_password The database password.
	 * @param db The URL of the database.
	 */
	public void openConnection(String db_user, String db_password, String db) {
		try {
			if(this.connect == null || this.connect.isClosed()) {
				
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
				this.connect = DriverManager.getConnection(db, db_user, db_password);
			}
			if(this.statement == null || this.statement.isClosed()) {
				this.statement = this.connect.createStatement();
			}
						
			if(connect != null) {
				System.out.println("Connected to the database ClassicModels");
			}
			
		}
		catch(Exception e) {
			System.out.println("Error: Failed to create a connection to the database");
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * This method closes the connection to the SQL database.
	 */
	public void closeConnection() {
		
		try {
			  if (this.statement != null) {
				  statement.close();
			  }
			
			  if (this.connect != null) {
			    this.connect.close();
			  }
		  
			  System.out.println("Database connection closed");
			} 
		catch (Exception e) {
				System.out.print("Error: Failed to close the connection to the database");
			    	throw new RuntimeException(e);
		}
		
	}
	

}
