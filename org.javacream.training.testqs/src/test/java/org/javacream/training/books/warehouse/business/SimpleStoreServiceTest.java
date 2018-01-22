package org.javacream.training.books.warehouse.business;

import org.javacream.Context;
import org.javacream.store.api.StoreService;
import org.javacream.store.implementation.SimpleStoreService;
import org.junit.Assert;
import org.junit.Before;

public class SimpleStoreServiceTest {

	private StoreService simpleStoreService;

	@Before public void init() {
		simpleStoreService = Context.storeService();
	}
	
	public void stockMustBeRetrieved() {
		String category = "";
		String id = "";
		int stock = simpleStoreService.getStock(category, id);
		Assert.assertTrue(stock >= 0);
				
	}
}
