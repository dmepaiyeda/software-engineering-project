package com.com1028.myassignment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.com1028.myassignment.Order;

/**
 * Creates Data Access Object(DAO) for orders table.
 * @author deborahmepaiyeda
 *
 */
public class OrderDAO extends DAO{
	/**
	 * This constructor initializes the OrderDAO object.
	 */
	public OrderDAO(){
		super();
	}
	
	/**
	 * This method gets data from the orders table.
	 * @return List A list of data from the orders table.
	 */
	public List<Order> getOrders(){
		ArrayList <Order> orders = new ArrayList<Order>();
		try {
			String query = "SELECT * FROM orders";
			
			ResultSet results = this.statement.executeQuery(query);
			
			while(results.next()) {
				int orderNumber = results.getInt("orderNumber");
				int customerNumber = results.getInt("customerNumber");
				orders.add(new Order(orderNumber, customerNumber));
			}
			
		}
		catch(SQLException e){
			System.out.println("SQLException happened while getting payment records");
			throw new RuntimeException(e);
			
		}
		return orders;
	}
}
