package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.CustomerService;
import org.javacream.books.warehouse.InvoiceService;
import org.javacream.books.warehouse.Order;
import org.javacream.books.warehouse.OrderService;

public class OrderServiceImpl implements OrderService {

	private BooksService booksService;
	private CustomerService customerService;
	private InvoiceService invoiceService;

	public void setBooksService(BooksService booksService) {
		this.booksService = booksService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@Override
	public Order order(String customerId, String[] isbns) {
		Order order = new Order();
		order.setOrderId(Long.toString(System.currentTimeMillis()));
		order.setCustomerId(customerId);
		double totalPrice = 0;
		boolean approved = true;
		for (String isbn : isbns) {
			try {
				Book book = booksService.findBookByIsbn(isbn);
				totalPrice += book.getPrice();
				if (!book.isAvailable()) {
					approved = false;
				}
			} catch (IllegalArgumentException e) {
				// price is null
			}
		}
		order.setTotalPrice(totalPrice);

		if (!customerService.checkCustomer(customerId)) {
			approved = false;
		} else if (!invoiceService.check(customerId, totalPrice)) {
			approved = false;
		}
		order.setApproved(approved);
		return order;
	}

}
