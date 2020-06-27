package com.com1028.myassignment;

/**
 * This Customer class implements the Customers 
 * table from the ClassicModels database.
 * @author deborahmepaiyeda
 */
public class Customer {
	
	//Local variables
	private int customerNumber = 0;
	private int salesRepEmployeeNumber = 0;
	private String customerName = null ;
	private String contactLastName = null;
	private String phone = null;
	private String addressLine1 = null;
	private String addressLine2 = null;
	private String addressLine3 = null;
	private String city = null;
	private String state = null;
	private String postalCode = null;
	private String country = null;
	private double creditLimit = 0.0;
	
	/**
	 * This constructor initializes the customer object.
	 * @param customerName The customer name.
	 * @param customerNumber The customer number.
	 * @param salesRepEmployeeNumber The employee number of the sales representative.
	 */
	
	public Customer(String customerName, int customerNumber, int salesRepEmployeeNumber){
		super();
		this.customerName = customerName;
		this.customerNumber = customerNumber;
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
	}
	
	/**
	 * This method is used to get the customer name.
	 * @return String The customer name.
	 */
	
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * This method is used to get the customer number.
	 * @return int The customer number.
	 */
	
	public int getCustomerNumber() {
		return customerNumber;
	}
	
	/**
	 * This method is used to get the employee number of the sales representative.
	 * @return int The employee number of the sales representative.
	 */
	
	public int getSalesRepEmployeeNumber() {
		return salesRepEmployeeNumber;
	}
}
