package org.javacream.books.warehouse.business;

import org.easymock.EasyMock;
import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.CustomerService;
import org.javacream.books.warehouse.InvoiceService;
import org.javacream.books.warehouse.Order;
import org.javacream.books.warehouse.OrderService;
import org.javacream.books.warehouse.business.OrderServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderServiceImplUnitTest {
	
	private OrderService orderService;
	private static final String CUSTOMER_ID = "TEST_CUST_ID";
	private static double totalPrice;
	private static final int size;
	private static final String[] ISBNS;
	
	static{
		size = 5;
		ISBNS = new String[size];
	}
	@Before
	public void init(){
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		orderServiceImpl.setBooksService(createBooksServiceMock());
		orderServiceImpl.setCustomerService(createCustomerServiceMock());
		orderServiceImpl.setInvoiceService(createInvoiceServiceMock());
		orderService = orderServiceImpl;
	}
	
	private InvoiceService createInvoiceServiceMock() {
		InvoiceService invoiceService = EasyMock.createStrictMock(InvoiceService.class);
		EasyMock.expect(invoiceService.check(CUSTOMER_ID, totalPrice)).andReturn(false);
		EasyMock.replay(invoiceService);
		return invoiceService;
	}

	private CustomerService createCustomerServiceMock() {
		CustomerService customerService = EasyMock.createStrictMock(CustomerService.class);
		EasyMock.expect(customerService.checkCustomer(CUSTOMER_ID)).andReturn(true);
		EasyMock.replay(customerService);
		return customerService;
	}

	private BooksService createBooksServiceMock() {
		BooksService booksService = EasyMock.createStrictMock(BooksService.class);
		Book book = null;
		for (int i = 0; i < size; i++){
			book = new Book();
			book.setIsbn("ISBN" + i);
			book.setTitle("TITLE" + i);
			book.setPrice(9.99 * i);
			book.setAvailable(true);
			totalPrice += book.getPrice();
			ISBNS[i] = book.getIsbn();
			EasyMock.expect(booksService.findBookByIsbn(book.getIsbn())).andReturn(book);
		}
		//book.setAvailable(false);
		EasyMock.replay(booksService);
		return booksService;
	}

	@Test
	public void testOrder() {
		Order order = orderService.order(CUSTOMER_ID, ISBNS);
		Assert.assertEquals(CUSTOMER_ID, order.getCustomerId());
		Assert.assertEquals(totalPrice, order.getTotalPrice(), 1e-9);
		Assert.assertEquals(false, order.isApproved());
	}

}
