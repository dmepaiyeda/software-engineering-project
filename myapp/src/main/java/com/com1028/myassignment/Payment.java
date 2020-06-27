package com.com1028.myassignment;

/**
 * This Payment class implements the Payment 
 * table from the ClassicModels database.
 * @author deborahmepaiyeda
 *
 */

public class Payment {
	
	//Local variables
	private int customerNumber = 0;
	private double amount = 0.0;
	private double checkNumber = 0.0;
	private String paymentDate = null;
	
	/**
	 * This constructor initializes the payment object.
	 * @param customerNumber The customer name.
	 * @param amount The amount of a payment.
	 */
	public Payment(int customerNumber, double amount) {
		super();
		this.customerNumber = customerNumber;
		this.amount = amount;
	}
	
	/**
	 * This method is used to get the customer number.
	 * @return int The customer number.
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}
	/**
	 * This method is used to get the amount of a payment.
	 * @return int The amount of a payment.
	 */
	public double getAmount() {
		return amount;
	}
	

	
		
	
}
