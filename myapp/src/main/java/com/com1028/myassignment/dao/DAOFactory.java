package com.com1028.myassignment.dao;

/**
 * This class creates instance of the PaymentDAO, CustomerDAO, OrderDAO and OrderDetailsDAO
 * @author deborahmepaiyeda
 *
 */
public class DAOFactory {

	//Local variables
	private static final PaymentDAO paymentDAO = new PaymentDAO();
	private static final CustomerDAO customerDAO = new CustomerDAO();
	private static final OrderDAO orderDAO = new OrderDAO();
	private static final OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
	
	/**
	 * This method gets the PaymentDAO object.
	 * @return PaymentDAO The PaymentDAO object.
	 */
	public static PaymentDAO getPaymentDAO() {
		return DAOFactory.paymentDAO;
	}
	
	/**
	 * This method gets the CustomerDAO object.
	 * @return CustomerDAO The CustomerDAO object. 
	 */
	public static CustomerDAO getCustomerDAO() {
		return DAOFactory.customerDAO;
	}
	
	/**
	 * This method gets the OrderDAO object.
	 * @return OrderDAO The OrderDAO object.
	 */
	public static OrderDAO getOrderDAO() {
		return DAOFactory.orderDAO;
	}
	
	/**
	 * This method gets the OrderDetailsDAO object.
	 * @return OrderDetailsDAO The OrderDetailsDAO object.
	 */
	public static OrderDetailsDAO getOrderDetailsDAO() {
		return DAOFactory.orderDetailsDAO;
	}
}
