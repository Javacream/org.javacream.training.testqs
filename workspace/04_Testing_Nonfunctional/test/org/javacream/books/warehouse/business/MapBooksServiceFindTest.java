package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.IsbnGenerator;
import org.javacream.books.warehouse.StoreService;
import org.javacream.util.test.BaseDecorator;
import org.javacream.util.test.TracingDecorator;
import org.javacream.util.test.XmlRecordingDecorator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceFindTest {

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
		IsbnGenerator tracingIsbnGenerator = BaseDecorator.decorate(counterIsbnGenerator, TracingDecorator.class);
		//SimpleStoreService simpleStoreService = new SimpleStoreService();
		StoreService tracingStoreService = BaseDecorator.decorate(new SimpleStoreService(), TracingDecorator.class);
		mapBooksService.setIsbnGenerator(tracingIsbnGenerator);
		mapBooksService.setStoreService(tracingStoreService);
		booksService = BaseDecorator.decorate(mapBooksService, TracingDecorator.class);
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
