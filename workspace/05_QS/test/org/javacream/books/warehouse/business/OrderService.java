package org.javacream.books.warehouse.business;


public interface OrderService {

	Order order(String customerId, String[] isbns);
}
