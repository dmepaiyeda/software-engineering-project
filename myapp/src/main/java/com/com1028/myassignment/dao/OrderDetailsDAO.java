package com.com1028.myassignment.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.com1028.myassignment.OrderDetails;

/**
 * Creates Data Access Object(DAO) for orderDetails table.
 * @author deborahmepaiyeda
 *
 */
public class OrderDetailsDAO extends DAO{
	
	/**
	 * This constructor initializes the OrderDetailsDAO object.
	 */
	public OrderDetailsDAO() {
		super();
	}
	
	/**
	 * This method gets data from the orderDetails table.
	 * @return List A list of data from the orderDetails table.
	 */
	public List<OrderDetails> getOrderDetails(){
		ArrayList <OrderDetails> orderDetails = new ArrayList<OrderDetails>();
		try {
			String query = "SELECT * FROM orderDetails";
			
			ResultSet results = this.statement.executeQuery(query);
			
			while(results.next()) {
				int orderNumber = results.getInt("orderNumber");
				int quantityOrdered = results.getInt("quantityOrdered");
				double priceEach = results.getDouble("priceEach");
				orderDetails.add(new OrderDetails(orderNumber, quantityOrdered, priceEach));
			}
			
		}
		catch(Exception e) {
			System.out.println("SQLException happened while getting customer records");
			throw new RuntimeException(e);
		}
		return orderDetails;
	}
}
