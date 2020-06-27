package com.com1028.myassignment;

/**
 * This Order class implements the Orders
 * table from the ClassicModels database.
 * @author deborahmepaiyeda
 *
 */

public class Order{
	
	//Local variables
	private String orderDate = null;
	private String shippedDate = null;
	private String requiredDate = null;
	private String status = null;
	private String comments = null;
	private int customerNumber = 0;
	private int orderNumber = 0;

	
	/**
	 * This constructor initializes the order object.
	 * @param orderNumber The order number.
	 * @param customerNumber The customer number.
	 */
	
	public Order(int orderNumber, int customerNumber) {
		super();
		this.orderNumber = orderNumber;
		this.customerNumber = customerNumber;
	}
	
	/**
	 * This method is used to get the order number.
	 * @return int The order number.
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * This method is used to get the customer number.
	 * @return int The customer number.
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}
}
