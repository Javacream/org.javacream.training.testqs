package org.javacream.order.impl;

import org.javacream.accounting.api.AccountService;
import org.javacream.accounting.api.AddressService;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.customer.api.Customer;
import org.javacream.customer.api.CustomerService;
import org.javacream.order.api.Order;
import org.javacream.order.api.OrderService;
import org.javacream.order.api.OrderStatus;
import org.javacream.store.api.StoreService;
import org.javacream.util.idgenerator.api.IdGenerator;

public class OrderServiceImpl implements OrderService {

	private BooksService booksService;
	private StoreService storeService;
	private CustomerService customerService;
	private AddressService addressService;
	private AccountService accountService;
	private IdGenerator idGenerator;

	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	public void setBooksService(BooksService booksService) {
		this.booksService = booksService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public Order order(String isbn, int number, String lastname, String firstname, String address) {
		long orderId = idGenerator.next();
		double totalPrice;
		OrderStatus orderStatus;
		Book book = booksService.findBookByIsbn(isbn);
		if (book == null) {
			orderStatus = OrderStatus.UNAVAILABLE;
			totalPrice = 0;
		} else {
			totalPrice = book.getPrice() * number;
			if (storeService.getStock("books", isbn) >= number) {
				orderStatus = OrderStatus.OK;
			} else {
				orderStatus = OrderStatus.PENDING;
			}
		}
		Customer customer = customerService.findCustomerByName(lastname, firstname);
		if (customer == null) {
			orderStatus = OrderStatus.UNAVAILABLE;

		} else {
			long accountId = accountService.getAccountByCustomer(customer);
			double limit = accountService.getLimit(accountId);
			if (limit < totalPrice) {
				orderStatus = OrderStatus.UNAVAILABLE;
			}
			if (!addressService.validate(address)) {
				orderStatus = OrderStatus.UNAVAILABLE;
			}
			;

		}

		return new Order(orderId, isbn, firstname + " " + lastname, totalPrice, address, number, orderStatus);
	}

}
