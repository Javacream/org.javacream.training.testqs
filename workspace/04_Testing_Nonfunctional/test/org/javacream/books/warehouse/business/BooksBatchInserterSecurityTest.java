package org.javacream.books.warehouse.business;

import java.util.ArrayList;
import java.util.List;

import org.javacream.books.warehouse.BooksBatchInserter;
import org.javacream.util.test.BaseDecorator;
import org.javacream.util.test.InjectionDetectorDecorator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BooksBatchInserterSecurityTest {

	private static BooksBatchInserter booksBatchInserter;

	@BeforeClass
	public static void testBooksService() {
		MapBooksService mapBooksService = new MapBooksService();
		CounterIsbnGenerator isbnGenerator = new CounterIsbnGenerator();
		isbnGenerator.setSuffix("TEST OR 'A' = 'A'");
		SimpleStoreService storeService = new SimpleStoreService();
		storeService.setPropertiesUtil(new SimplePropertiesUtil());

		BooksBatchInserterImpl booksBatchInserterImpl = new BooksBatchInserterImpl();

		mapBooksService.setIsbnGenerator(BaseDecorator.decorate(isbnGenerator, InjectionDetectorDecorator.class));
		mapBooksService.setStoreService(storeService);
		booksBatchInserterImpl.setBooksService(BaseDecorator.decorate(mapBooksService, InjectionDetectorDecorator.class));
		booksBatchInserter = booksBatchInserterImpl;
	}

	@Test(timeout = 100, expected=IllegalArgumentException.class)
	public void testBatchInsert() {
		int size = 5000;
		ArrayList<String> titles = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			//titles.add("Title " + i + "' AND 'A' = 'A'");
			titles.add("Title " + i);
		}

		List<String> isbns = booksBatchInserter.insert(titles);

		Assert.assertTrue(isbns.size() == size);
	}
}
