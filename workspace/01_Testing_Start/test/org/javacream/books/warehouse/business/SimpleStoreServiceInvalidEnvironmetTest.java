package org.javacream.books.warehouse.business;

import org.junit.Test;

public class SimpleStoreServiceInvalidEnvironmetTest {

	@Test(expected=IllegalStateException.class) 
	public void testSimpleStoreServiceInvalidInitialization(){
		new SimpleStoreService();
	}
}
