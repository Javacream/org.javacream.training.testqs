package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.CustomerService;
import org.javacream.books.warehouse.InvoiceService;
import org.javacream.books.warehouse.Order;
import org.javacream.books.warehouse.OrderService;
import org.javacream.books.warehouse.business.OrderServiceImpl;
import org.javacream.util.test.GenericDummy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderServiceImplDummyTest {
	
	private OrderService orderService;
	private static final String CUSTOMER_ID = "TEST_CUST_ID";
	private static final String[] ISBNS;
	
	static{
		ISBNS = new String[]{"1", "2", "3"};
	}
	@Before
	public void init(){
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		orderServiceImpl.setBooksService(GenericDummy.createDummy(BooksService.class));
		orderServiceImpl.setCustomerService(GenericDummy.createDummy(CustomerService.class));
		orderServiceImpl.setInvoiceService(GenericDummy.createDummy(InvoiceService.class));
		orderService = orderServiceImpl;
	}
	

	@Test
	public void testOrder() {
		Order order = orderService.order(CUSTOMER_ID, ISBNS);
		Assert.assertEquals(CUSTOMER_ID, order.getCustomerId());
		Assert.assertEquals(false, order.isApproved());
	}

}
