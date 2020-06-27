package com.com1028.myassignment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.com1028.myassignment.Payment;

/**
 * Creates Data Access Object(DAO) for payments table.
 * @author deborahmepaiyeda
 *
 */
public class PaymentDAO extends DAO{
	
	/**
	 * This constructor initializes the PaymentDAO object.
	 */
	public PaymentDAO() {
		super();
	}
	
	/**
	 * This method gets data from the payments table.
	 * @return List A list of data from the payments table.
	 */
	public List<Payment> getPayments(){
		ArrayList <Payment> payments = new ArrayList<Payment>();
		try {
			String query = "SELECT * FROM payments";
			
			ResultSet results = this.statement.executeQuery(query);
			
			while(results.next()) {
				int customerNumber = results.getInt("customerNumber");
				double amount = results.getDouble("amount");
				payments.add(new Payment(customerNumber, amount));
			}
			
		}catch(SQLException e){
			System.out.println("SQLException happened while getting payment records");
			throw new RuntimeException(e);
			
		}
		return payments;
	}
}
