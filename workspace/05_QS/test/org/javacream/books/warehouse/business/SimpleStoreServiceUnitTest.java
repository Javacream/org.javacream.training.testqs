package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.StoreService;
import org.javacream.books.warehouse.business.SimpleStoreService;
import org.javacream.books.warehouse.test.PropertiesUtilTestDriver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleStoreServiceUnitTest {

	private StoreService storeService;
	private static final String CATEGORY = "books";
	private final String UNKNWON_CATEGORY = "_§$%_";
	private final String UNKNWON_ITEM = "_§4$%6_";
	final String TEST_ISBN1 = "1-123-12345-1-TEST";
	final Integer TEST_ISBN1_STOCK = 10;

	@Before
	public void setUp(){
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		simpleStoreService.setPropertiesUtil(new PropertiesUtilTestDriver());
		storeService = simpleStoreService;
	}


	@Test
	public void testSimpleStoreService() {
		Integer result = storeService.getStock(CATEGORY, TEST_ISBN1);
		Assert.assertEquals(TEST_ISBN1_STOCK, result);
	}

	@Test
	public void testSimpleStoreServiceInitialization() {
		Assert.assertNotNull("simpleStoreService was null", storeService);
	}

	@Test
	public void testSimpleStoreServiceUnknownCategory() {
		Assert.assertEquals(0,
				storeService.getStock(UNKNWON_CATEGORY, TEST_ISBN1));
	}

	@Test
	public void testSimpleStoreServiceUnknownItem() {
		Assert.assertEquals(0,
				storeService.getStock(CATEGORY, UNKNWON_ITEM));
	}

	@Test
	public void testSimpleStoreServiceNullCategory() {
		Assert.assertEquals(0, storeService.getStock(null, TEST_ISBN1));
	}

	@Test
	public void testSimpleStoreServiceNullItem() {
		Assert.assertEquals(0, storeService.getStock(CATEGORY, null));
	}
}
