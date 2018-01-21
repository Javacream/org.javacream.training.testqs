package org.javacream.books.warehouse;

public interface OrderService {

	Order order(String customerId, String[] isbns);
}
