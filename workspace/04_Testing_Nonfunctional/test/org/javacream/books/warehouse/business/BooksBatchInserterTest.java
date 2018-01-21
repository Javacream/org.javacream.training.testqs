package org.javacream.books.warehouse.business;

import java.util.ArrayList;
import java.util.List;

import org.javacream.books.warehouse.BooksBatchInserter;
import org.javacream.util.test.NetworkSimulatorInvocationHandler;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BooksBatchInserterTest {

	private static BooksBatchInserter booksBatchInserter;

	@BeforeClass
	public static void testBooksService() {
		MapBooksService mapBooksService = new MapBooksService();
		CounterIsbnGenerator isbnGenerator = new CounterIsbnGenerator();
		SimpleStoreService storeService = new SimpleStoreService();
		storeService.setPropertiesUtil(new SimplePropertiesUtil());

		BooksBatchInserterImpl booksBatchInserterImpl = new BooksBatchInserterImpl();

		isbnGenerator.setSuffix("-TEST");
		mapBooksService.setIsbnGenerator(NetworkSimulatorInvocationHandler.decorate(isbnGenerator, 1));
		mapBooksService.setStoreService(storeService);
		booksBatchInserterImpl.setBooksService( mapBooksService);
		booksBatchInserter = booksBatchInserterImpl;
	}

	@Test(timeout = 100)
	public void testBatchInsert() {
		int size = 5000;
		ArrayList<String> titles = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			titles.add("Title" + i);
		}

		List<String> isbns = booksBatchInserter.insert(titles);

		Assert.assertTrue(isbns.size() == size);
	}
}
