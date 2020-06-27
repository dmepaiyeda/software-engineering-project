package com.com1028.myassignment;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import com.com1028.myassignment.dao.CustomerDAO;
import com.com1028.myassignment.dao.DAOFactory;
import com.com1028.myassignment.dao.PaymentDAO;

/**
* This class performs calculations with the use of the data from the SQL database.
* @author deborahmepaiyeda
*
*/

public class CalculatePayments {
	
	/**
	 * This is the main method which makes the Payment, Customer 
	 * Data Access Objects 
	 * @param args 
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception {
		
		//Local variables
		final String db_user = "root";
		final String db_password = "";
		final String db = "jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
						
		PaymentDAO paymentDAO = null;
		CustomerDAO customerDAO = null;
		List<Payment> payments = null;
		List<Customer> customers = null;
		
		try {
			
			paymentDAO = DAOFactory.getPaymentDAO();
			customerDAO = DAOFactory.getCustomerDAO();

			paymentDAO.openConnection(db_user, db_password, db);
			customerDAO.openConnection(db_user, db_password, db);
			
			payments = paymentDAO.getPayments();
			customers = customerDAO.getCustomers();
		} 
		catch(Exception e) {
			e.printStackTrace();
		} 
		finally {
			if(paymentDAO != null) {
				paymentDAO.closeConnection();
			}
			if(customerDAO != null) {
				customerDAO.closeConnection();
			}
		}
		
		//Creating objects to store the results from each function.
		Hashtable <Integer, Double> paymentResult = calculatePayment(payments);
		
		Enumeration<Integer> paymentResultKeys = paymentResult.keys();
		
				
		//Prints amount paid by each customer
		System.out.println("\n\nAMOUNT PAID BY EACH CUSTOMER:\n");
		System.out.println("Customer\nnumber   Amount		Customer name");
		System.out.println("______________________________________________");
		
		while(paymentResultKeys.hasMoreElements()) {
			int key = paymentResultKeys.nextElement();
			
			String name = "";
			//loop through payments
			for(int i = 0; i<payments.size(); i++) {
				if(payments.get(i).getCustomerNumber() == key) {
					for(int j = 0; j<customers.size(); j++) {
						if(payments.get(i).getCustomerNumber() == customers.get(j).getCustomerNumber()) {
							name = customers.get(j).getCustomerName();
							break;
						}
					}
					break;
				}
			}
				
			System.out.printf("%d 	$%f	%s\n", key, paymentResult.get(key), name);
		}
		
	 }
	

	
	/**
	 * This method calculates the amount paid by each customer.
	 * @param payments The payments table.
	 * @return Hashtable A hashtable of customer numbers (as keys) and amount (as value).
	 */
		public static Hashtable<Integer, Double> calculatePayment(List<Payment> payments){
			Hashtable <Integer, Double> totalPayments = new Hashtable <Integer, Double> ();
			
			for(int i = 0; i<payments.size(); i++) {
				int customerNumber = payments.get(i).getCustomerNumber();
				double amount = payments.get(i).getAmount();

				if(totalPayments.containsKey(customerNumber)) {
					double total = totalPayments.get(customerNumber) + amount;
					totalPayments.put(customerNumber, Math.round(total*100.0)/100.0);
				}
				else {
					totalPayments.put(customerNumber, amount);
				}
				

			}

			return totalPayments;
		}
}
