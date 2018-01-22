package org.javacream.training.books.warehouse.business;

import org.javacream.books.warehouse.business.SimpleStoreService;
import org.junit.Assert;
import org.junit.Before;

public class SimpleStoreServiceTest {

	private SimpleStoreService simpleStoreService;

	@Before public void init() {
		simpleStoreService = new SimpleStoreService();
	}
	
	public void stockMustBeRetrieved() {
		String category = "";
		String id = "";
		int stock = simpleStoreService.getStock(category, id);
		Assert.assertTrue(stock >= 0);
				
	}
}
