package com.com1028.myassignment;

/**
 * This OrderDetails class implements the Orders
 * table from the ClassicModels database.
 * @author deborahmepaiyeda
 *
 */
public class OrderDetails {
	
	//Local variables
	private int orderNumber = 0;
	private int quantityOrdered = 0;
	private double priceEach = 0.0;
	private String productCode = null;
	private String shippedDate = null;
	private int orderLineNumber = 0;
	
	/**
	 * This constructor initializes the order details object.
	 * @param orderNumber The order number.
	 * @param quantityOrdered The quantity ordered.
	 * @param priceEach The price of each order.
	 */
	
	public OrderDetails(int orderNumber, int quantityOrdered, double priceEach) {
		super();
		this.orderNumber = orderNumber;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
	}
	
	/**
	 * This method is used to get the order number.
	 * @return int The order number.
	 */
	public int getOrderNumber() {
		return orderNumber;
	}
	
	/**
	 * This method is used to get the quantity ordered.
	 * @return int The quantity ordered.
	 */
	public int getQuantityOrdered() {
		return quantityOrdered;
	}
	
	/**
	 * This method is used to get the price of each order.
	 * @return int The price of each order.
	 */
	public double getPriceEach() {
		return priceEach;
	}


}
