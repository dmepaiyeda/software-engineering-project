package com.com1028.myassignment;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import com.com1028.myassignment.dao.CustomerDAO;
import com.com1028.myassignment.dao.DAOFactory;
import com.com1028.myassignment.dao.OrderDAO;
import com.com1028.myassignment.dao.OrderDetailsDAO;

/**
* This class performs calculations with the use of the data from the SQL database.
* @author deborahmepaiyeda
*
*/

public class CalculateOrders {
	
	/**
	 * This is the main method which makes the Customer, Order 
	 * and Order Details Data Access Objects 
	 * @param args 
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception {
		
		//Local variables
		final String db_user = "root";
		final String db_password = "";
		final String db = "jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
						
		CustomerDAO customerDAO = null;
		OrderDAO orderDAO = null;
		OrderDetailsDAO orderDetailsDAO = null;
		List<Customer> customers = null;
		List<Order> orders = null;
		List<OrderDetails> orderDetails = null;
		
		try {
			orderDAO = DAOFactory.getOrderDAO();
			customerDAO = DAOFactory.getCustomerDAO();
			orderDetailsDAO = DAOFactory.getOrderDetailsDAO();

			orderDAO.openConnection(db_user, db_password, db);
			customerDAO.openConnection(db_user, db_password, db);
			orderDetailsDAO.openConnection(db_user, db_password, db);
			
			orders = orderDAO.getOrders();
			customers = customerDAO.getCustomers();
			orderDetails = orderDetailsDAO.getOrderDetails();
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		} 
		finally {
			if(customerDAO != null) {
				customerDAO.closeConnection();
			}
			
			if(orderDAO != null) {
				orderDAO.closeConnection();
			}
			
			if(orderDetailsDAO != null) {
				orderDetailsDAO.closeConnection();
			}
		}
		
		//Creating objects to store the results from each function.
		Hashtable <Integer, Double> orderResult = calculateOrders(orders, customers, orderDetails);
		
		Enumeration<Integer> orderResultKeys = orderResult.keys();
		
		//Prints orders greater than $5,000
		System.out.println("\n\nORDERS GREATER THAN $5,000:\n");
		System.out.println("Order\nnumber  Amount		Customer name");
		System.out.println("_________________________________________");
		
		while(orderResultKeys.hasMoreElements()) {
			int key = orderResultKeys.nextElement();
			
			String customerName = "";
			for(int i = 0; i<orders.size(); i++) {
				if(orders.get(i).getOrderNumber() == key) {
					for(int j = 0; j<customers.size(); j++) {
						if(customers.get(j).getCustomerNumber() == orders.get(i).getCustomerNumber()) {
							customerName = customers.get(j).getCustomerName();
							break;
						}
					}
					break;
				}
			}
			System.out.printf("%d  $%f	%s\n", key, orderResult.get(key), customerName);
		}
	 }
	

	/**
	 * This method generates a list of orders greater than $5,000.
	 * @param orders The orders table.
	 * @param customers The customers table.
	 * @param orderDetails The orderDetails table.
	 * @return Hashtable A hashtable of order numbers (as key) and amount (as value).
	 */
	public static Hashtable<Integer, Double> calculateOrders(List<Order> orders, List<Customer> customers, List<OrderDetails> orderDetails){
		
		Hashtable <Integer, Double> highOrders = new Hashtable<Integer, Double>();

		for(int i = 0; i<orders.size(); i++) {
			double result = 0.0;
			for(int j = 0; j<orderDetails.size(); j++) {
				if(orders.get(i).getOrderNumber() == orderDetails.get(j).getOrderNumber()){
					result += (orderDetails.get(j).getQuantityOrdered()) * (orderDetails.get(j).getPriceEach());
				}
				
			}
			
			result = Math.round(result*100.0)/100.0;
			if(result > 5000) {
				highOrders.put(orders.get(i).getOrderNumber(), result);
			}
			
		}
		
		return highOrders;
		
	}
}
