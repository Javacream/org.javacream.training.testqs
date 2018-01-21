package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.business.MapBooksService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceFindTest {

	private static String isbn1;
	private static String isbn2;

	private static final String TITLE1 = "Title1";
	private static final String TITLE2 = "Title2";

	private static MapBooksService booksService;
	private String UNKNOWN_ISBN = "UNKNOWN ISBN";


	@BeforeClass
	public static void testBooksService() {
		booksService = new MapBooksService();
		isbn1 = booksService.newBook(TITLE1);
		isbn2 = booksService.newBook(TITLE2);
	}

	@Test
	public void testFindBookByIsbn() {

		Book book = booksService.findBookByIsbn(isbn1);
		Assert.assertEquals("Title must be " + TITLE1, TITLE1, book.getTitle());
		book = booksService.findBookByIsbn(isbn2);
		Assert.assertEquals("Title must be " + TITLE2, TITLE2, book.getTitle());

	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testNullIsbn(){
		booksService.newBook(null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testUnknownIsbn(){
		booksService.findBookByIsbn(UNKNOWN_ISBN);
	}

}
