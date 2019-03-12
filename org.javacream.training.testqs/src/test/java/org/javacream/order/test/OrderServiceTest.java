package org.javacream.order.test;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.easymock.EasyMock;
import org.javacream.accounting.api.AccountService;
import org.javacream.accounting.api.AddressService;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.customer.api.Customer;
import org.javacream.customer.api.CustomerService;
import org.javacream.order.api.Order;
import org.javacream.order.api.OrderService;
import org.javacream.order.api.OrderStatus;
import org.javacream.order.impl.OrderServiceImpl;
import org.javacream.store.api.StoreService;
import org.javacream.util.idgenerator.api.IdGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderServiceTest {

	private BooksService booksService;
	private StoreService storeService;
	private CustomerService customerService;
	private AddressService addressService;
	private AccountService accountService;
	private IdGenerator idGenerator;

	
	private String lastname;
	private String firstname;
	private String isbn;
	private int number;
	private double bookPrice;
	private double limit;
	private String address;
	private Customer c;
	private int stock;
	private boolean isAddressValid;
	private long orderId = 55l;
	private OrderService orderService;
	private Book book;

	@Before
	public void init() {
		idGenerator = EasyMock.createStrictMock(IdGenerator.class);
		EasyMock.expect(idGenerator.next()).andReturn(orderId);
	}

	@Test
	public void testValidOrder() {
		testSequence(() -> {
			return null;
		}, (order) -> {
			Assert.assertEquals(OrderStatus.OK, order.getStatus());
			Assert.assertEquals(99.9, order.getTotalPrice(), 1e-9);
		});

	}

	@Test
	public void testLimitedStockCreatesPendingOrder() {
		testSequence(() -> {
			stock = 1;
			return null;
		}, (order) -> {
			Assert.assertEquals(OrderStatus.PENDING, order.getStatus());
			Assert.assertEquals(99.9, order.getTotalPrice(), 1e-9);
		});
	}

	@Test
	public void testLimitCreatesUnavailableOrder() {
		testSequence(() -> {
			limit = 1;
			return null;
		}, (order) -> {
			Assert.assertEquals(OrderStatus.UNAVAILABLE, order.getStatus());
			Assert.assertEquals(99.9, order.getTotalPrice(), 1e-9);
		});
	}

	@Test
	public void testUnknownCustomerCreatesUnavailableOrder() {
		testSequence(() -> {
			c = null;
			return null;
		}, (order) -> {
			Assert.assertEquals(OrderStatus.UNAVAILABLE, order.getStatus());
			Assert.assertEquals(99.9, order.getTotalPrice(), 1e-9);
		});
	}

	@Test
	public void testUnknownAddressCreatesUnavailableOrder() {
		testSequence(() -> {
			isAddressValid = false;
			return null;
		}, (order) -> {
			Assert.assertEquals(OrderStatus.UNAVAILABLE, order.getStatus());
			Assert.assertEquals(99.9, order.getTotalPrice(), 1e-9);
		});
	}

	@Test
	public void testUnknownBookCreatesUnavailableOrder() {
		testSequence(() -> {
			book = null;
			return null;
		}, (order) -> {
			Assert.assertEquals(OrderStatus.UNAVAILABLE, order.getStatus());
			Assert.assertEquals(0, order.getTotalPrice(), 1e-9);
		});
	}
	
	@Test public void testStockToSmallAndLinitReachedCreatesUnavailableOrder() {
		testSequence(() -> {
			stock = 0;
			limit = 1;
			return null;
		}, (order) -> {
			Assert.assertEquals(OrderStatus.UNAVAILABLE, order.getStatus());
			Assert.assertEquals(99.9, order.getTotalPrice(), 1e-9);
		});
		
	}

	private void testSequence(Supplier<Void> parametersCallback, Consumer<Order> assertionsCallback) {
		prepareValidParameters();
		parametersCallback.get();
		prepare();
		Order order = orderService.order(isbn, number, lastname, firstname, address);
		assertionsCallback.accept(order);
		assertBasics(order);

	}

	private void assertBasics(Order order) {
		Assert.assertEquals(orderId, order.getOrderId());
		Assert.assertEquals(isbn, order.getIsbn());
		Assert.assertEquals(number, order.getNumber());
		Assert.assertEquals(address, order.getAddress());
		Assert.assertEquals("B A", order.getCustomer());

	}

	private void prepareValidParameters() {
		lastname = "A";
		firstname = "B";
		isbn = "ISBN-1";
		number = 10;
		bookPrice = 9.99;
		limit = 200.99;
		address = "egal";
		stock = 200;
		isAddressValid = true;
		c = new Customer(lastname, firstname);
		book = new Book();
		book.setIsbn(isbn);
		book.setPrice(bookPrice);

	}

	private void prepare() {

		booksService = EasyMock.createStrictMock(BooksService.class);
		EasyMock.expect(booksService.findBookByIsbn(isbn)).andReturn(book);
		storeService = EasyMock.createStrictMock(StoreService.class);
		EasyMock.expect(storeService.getStock("books", isbn)).andReturn(stock);
		accountService = EasyMock.createStrictMock(AccountService.class);
		EasyMock.expect(accountService.getAccountByCustomer(c)).andReturn(1l);
		EasyMock.expect(accountService.getLimit(1l)).andReturn(limit);
		addressService = EasyMock.createStrictMock(AddressService.class);
		EasyMock.expect(addressService.validate(address)).andReturn(isAddressValid);
		customerService = EasyMock.createMock(CustomerService.class);
		EasyMock.expect(customerService.findCustomerByName(lastname, firstname)).andReturn(c);
		EasyMock.replay(idGenerator, storeService, customerService, booksService, addressService, accountService);

		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		orderServiceImpl.setAccountService(accountService);
		orderServiceImpl.setAddressService(addressService);
		orderServiceImpl.setBooksService(booksService);
		orderServiceImpl.setCustomerService(customerService);
		orderServiceImpl.setIdGenerator(idGenerator);
		orderServiceImpl.setStoreService(storeService);
		orderService = orderServiceImpl;
	}

}
