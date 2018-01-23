package org.javacream.training.store;

import org.javacream.Context;
import org.javacream.store.api.StoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleStoreServiceTest {

	private StoreService simpleStoreService;

	@Before public void init() {
		simpleStoreService = Context.storeService();
	}
	
	@Test
	public void stockMustBeRetrieved() {
		String category = "";
		String id = "";
		int stock = simpleStoreService.getStock(category, id);
		Assert.assertTrue(stock >= 0);
				
	}
}
