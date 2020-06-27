package com.com1028.myassignment;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.com1028.myassignment.dao.CustomerDAO;
import com.com1028.myassignment.dao.DAOFactory;
import com.com1028.myassignment.dao.OrderDAO;
import com.com1028.myassignment.dao.OrderDetailsDAO;
import com.com1028.myassignment.dao.PaymentDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class AppTest {
	
	// Configuration information
	public static Connection con = null;
	private static Statement stmt;
	public static String db = "jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static String db_user = "root";
	public static String db_password = "";
	
	PaymentDAO paymentDAO = null;
	CustomerDAO customerDAO = null;
	OrderDAO orderDAO = null;
	OrderDetailsDAO orderDetailsDAO = null;
	List<Payment> payments = null;
	List<Customer> customers = null;
	List<Order> orders = null;
	List<OrderDetails> orderDetails = null;
	
	
	//SQL Queries
	String queryCalculateOrders = "SELECT orderNumber, ROUND(SUM(quantityOrdered*priceEach), 2) totalAmount "
								+ "FROM orderDetails "
								+ "GROUP BY orderNumber "
								+ "HAVING SUM(quantityOrdered*priceEach) > 5000 "
								+ "ORDER BY totalAmount";
	
	String queryCalculatePayments = "SELECT customerNumber, ROUND(SUM(amount), 2)"
								+ "FROM payments p GROUP BY customerNumber";
	
	String queryCalculateRevenue = "SELECT salesRepEmployeeNumber, ROUND(SUM(amount), 2) Revenue "
								+ "FROM payments p INNER JOIN customers c "
								+ "ON p.customerNumber = c.customerNumber "
								+ "GROUP BY salesRepEmployeeNumber";
	
	/**
	 * Creates connection to SQL database.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		try {
			
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
			con = DriverManager.getConnection(db, db_user, db_password);
			stmt = con.createStatement();
			
			paymentDAO = DAOFactory.getPaymentDAO();
			paymentDAO.openConnection(db_user, db_password, db);
			
			customerDAO = DAOFactory.getCustomerDAO();
			customerDAO.openConnection(db_user, db_password, db);
			
			orderDAO = DAOFactory.getOrderDAO();
			orderDAO.openConnection(db_user, db_password, db);
			
			orderDetailsDAO = DAOFactory.getOrderDetailsDAO();
			orderDetailsDAO.openConnection(db_user, db_password, db);
			
			payments = paymentDAO.getPayments();
			customers = customerDAO.getCustomers();
			orders = orderDAO.getOrders();
			orderDetails = orderDetailsDAO.getOrderDetails();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method tests the calculateOrders function.
	 */
	@Test
	public void testcalculateOrders() {
		try {
			
			ResultSet res = stmt.executeQuery(queryCalculateOrders);
			int rowCount = 0;
			Hashtable<Integer, Double> ordersList = CalculateOrders.calculateOrders(orders, customers, orderDetails);
			
			System.out.println("\n\nORDERS GREATER THAN $5,000:\n");
			System.out.println("Order\nnumber  Amount ");
			System.out.println("______________________");
			
			while(res.next()) {
				rowCount++;
				int orderNumber = res.getInt(1);
				double amount = res.getDouble(2);
				assertEquals("Expected amount is not the same as amount in query results", ordersList.get(orderNumber), amount, 0);
				
				//prints results from calculateOrders function
				System.out.printf("%d %f\n", orderNumber, ordersList.get(orderNumber));
			}
			
			
			if(rowCount != ordersList.size()) {
				fail("The database query result size is not the same size as the expected result size");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method tests the calculatePayment function.
	 */
	@Test
	public void testcalculatePayments() {
		try {
			
			ResultSet res = stmt.executeQuery(queryCalculatePayments);
			int rowCount = 0;
			Hashtable<Integer, Double> paymentList = CalculatePayments.calculatePayment(payments);
			
			System.out.println("\n\nAMOUNT PAID BY EACH CUSTOMER:\n");
			System.out.println("Customer\nnumber   Amount");
			System.out.println("_________________________");
			
			while(res.next()) {
				rowCount++;
				int customerNumber = res.getInt(1);
				double amount = res.getDouble(2);
				assertEquals("Expected amount is not the same as amount in query results", paymentList.get(customerNumber), amount, 0);
				
				//prints results from calculatePayments function
				System.out.printf("%d %f\n", customerNumber, paymentList.get(customerNumber));
			}
			
			if(rowCount != paymentList.size()) {
				fail("The database query result size is not the same size as the expected result size");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method tests the calculateRevenue function.
	 */
	@Test
	public void testcalculateRevenue() {
		try {
			
			ResultSet res = stmt.executeQuery(queryCalculateRevenue);
			int rowCount = 0;
			Hashtable<Integer, Double>revenueList = CalculateRevenues.calculateRevenue(payments, customers);
			
			System.out.println("\n\nREVENUE GENERATED BY SALES REPRESENTATIVES:\n");
			System.out.println("Sales \nRepresentative	Revenue");
			System.out.println("________________________________");
			
			while(res.next()) {
				rowCount++;
				int salesRepEmplNum = res.getInt(1);
				double revenue = res.getDouble(2);
				assertEquals("Expected revenue is not the same as revenue in query results", revenueList.get(salesRepEmplNum), revenue, 0);
				
				//prints results from calculateRevenue function
				System.out.printf("%d		%f\n", salesRepEmplNum, revenueList.get(salesRepEmplNum));
			}
			
			if(rowCount != revenueList.size()) {
				fail("The database query result size is not the same size as the expected result size");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method closes the connection to the SQL database.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		if (con != null) {
			con.close();
		}
	}
	
	
}
