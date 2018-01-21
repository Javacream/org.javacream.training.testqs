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

	private static String isbn1;
	private static String isbn2;

	private static final String TITLE1 = "Title1";
	private static final String TITLE2 = "Title2";

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

		isbn1 = booksService.newBook(TITLE1);
		isbn2 = booksService.newBook(TITLE2);

		Assert.assertNotNull("Generated ISBN was null!", isbn1);
		Assert.assertNotNull("Generated ISBN was null!", isbn2);

		Assert.assertNotSame("Isbn must be unique", isbn1, isbn2);

		Assert.assertTrue("ISBN must have special format", isbn1.indexOf("-") > 0);
	}

}
