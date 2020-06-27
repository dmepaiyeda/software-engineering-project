package com.com1028.myassignment.dao;

import java.sql.Connection;
import java.util.List;

import com.com1028.myassignment.Customer;
import com.com1028.myassignment.Payment;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Creates Data Access Object(DAO) for customers table.
 * @author deborahmepaiyeda
 *
 */
public class CustomerDAO extends DAO{
	
	/**
	 * This constructor initializes the CustomerDAO object.
	 */
	public CustomerDAO(){
		super();
	}

	/**
	 * This method gets the data from the customers table.
	 * @return List A list of data from the customers table.
	 */
	public List<Customer> getCustomers(){
		ArrayList <Customer> customers = new ArrayList<Customer>();
		try {
			String query = "SELECT * FROM customers";
			
			ResultSet results = this.statement.executeQuery(query);
			
			while(results.next()) {
				String customerName = results.getString("customerName");
				int customerNumber = results.getInt("customerNumber");
				int salesRepEmployeeNum = results.getInt("salesRepEmployeeNumber");
				customers.add(new Customer(customerName, customerNumber, salesRepEmployeeNum));
			}
			
		}
		catch(Exception e) {
			System.out.println("SQLException happened while getting customer records");
			throw new RuntimeException(e);
		}
		return customers;
	}

}
