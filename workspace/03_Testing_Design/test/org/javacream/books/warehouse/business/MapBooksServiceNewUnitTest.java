package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.StoreService;
import org.javacream.books.warehouse.business.MapBooksService;
import org.javacream.books.warehouse.test.IsbnGeneratorMock;
import org.javacream.util.test.GenericDummy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceNewUnitTest {

	private final static String ISBN1 = "ISBN1-TEST";
	private final static String ISBN2 = "ISBN2-TEST";

	private static final String TITLE1 = "Title1";
	private static final String TITLE2 = "Title2";

	private static BooksService booksService;

	@BeforeClass
	public static void testBooksService() {
		MapBooksService mapBooksService = new MapBooksService();
		//CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		//counterIsbnGenerator.setSuffix("-TEST");
		IsbnGeneratorMock testDriver = new IsbnGeneratorMock();
		testDriver.addReturnValueForNextIsbn(ISBN1);
		testDriver.addReturnValueForNextIsbn(ISBN2);
		testDriver.play();
		mapBooksService.setIsbnGenerator(testDriver);
		mapBooksService.setStoreService(GenericDummy.createDummy(StoreService.class));
		//testDriver.addReturnValueForNextIsbn(ISBN2);
		
		booksService = mapBooksService;
	}

	@Test
	public void testNewBook() {

		String isbn1 = booksService.newBook(TITLE1);
		String isbn2 = booksService.newBook(TITLE2);

		Assert.assertEquals(ISBN1, isbn1);
		Assert.assertEquals(ISBN2, isbn2);

	}

}
