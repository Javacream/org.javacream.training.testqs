package org.javacream.books.warehouse;

public interface CustomerService {

	String createCustomer(String name, String address);
	String findCustomerById(String customerId);
	String deleteCustomerById(String customerId);
	//void updateCustomer(String customerId);
	boolean checkCustomer(String customerId);
}
