package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.StoreService;
import org.javacream.books.warehouse.business.CounterIsbnGenerator;
import org.javacream.books.warehouse.business.MapBooksService;
import org.javacream.util.test.GenericDummy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceNewTest {

	private static final String TITLE = "Title1";

	private static BooksService booksService;

	@BeforeClass
	public static void testBooksService() {
		MapBooksService mapBooksService = new MapBooksService();
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		counterIsbnGenerator.setSuffix("-TEST");
		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		mapBooksService.setStoreService(GenericDummy.createDummy(StoreService.class));
		
		booksService = mapBooksService;
	}

	@Test
	public void testNewBook() {

		String isbn = booksService.newBook(TITLE);

		Assert.assertNotNull("Generated ISBN was null!", isbn);
	}

}
