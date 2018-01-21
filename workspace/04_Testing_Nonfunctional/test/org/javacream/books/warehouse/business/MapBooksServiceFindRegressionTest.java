package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.StoreService;
import org.javacream.util.test.XmlPlayer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceFindRegressionTest {

	private static String isbn1;
	private static String isbn2;

	private static final String TITLE1 = "Title1";
	private static final String TITLE2 = "Title2";

	private static BooksService booksService;
	private String UNKNOWN_ISBN = "UNKNOWN ISBN";


	@BeforeClass
	public static void testBooksService() {
		MapBooksService mapBooksService = new MapBooksService();
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		counterIsbnGenerator.setSuffix("-TEST");
		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		StoreService storeService = XmlPlayer.createPlayer(StoreService.class, "s:/storeWebService.xml");
		mapBooksService.setStoreService(storeService);
		booksService = mapBooksService;
		isbn1 = booksService.newBook(TITLE1);
		isbn2 = booksService.newBook(TITLE2);
	}

	@Test
	public void testFindBookByIsbn() {

		Book book = booksService.findBookByIsbn(isbn1);
		Assert.assertEquals("Title must be " + TITLE1, TITLE1, book.getTitle());
		Assert.assertTrue("Book must be available", book.isAvailable());
		book = booksService.findBookByIsbn(isbn2);
		Assert.assertEquals("Title must be " + TITLE2, TITLE2, book.getTitle());
		Assert.assertTrue("Book must be available", book.isAvailable());

	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testNullIsbn(){
		booksService.newBook(null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testNullTitle(){
		booksService.findBookByIsbn(null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testUnknownIsbn(){
		booksService.findBookByIsbn(UNKNOWN_ISBN);
	}

}
