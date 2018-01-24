package org.javacream.order.api;

public interface OrderService {

	Order order(String isbn, int amount, String customer, String address);
}
