package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.IsbnGenerator;
import org.javacream.books.warehouse.StoreService;
import org.javacream.util.test.GenericDummy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceFindTestWithDummies {

	private static String isbn;

	private static final String TITLE = "Title";

	private static BooksService booksService;
	private String UNKNOWN_ISBN = "UNKNOWN ISBN";


	@BeforeClass
	public static void testBooksService() {
		MapBooksService mapBooksService = new MapBooksService();
		mapBooksService.setIsbnGenerator(GenericDummy.createDummy(IsbnGenerator.class));
		mapBooksService.setStoreService(GenericDummy.createDummy(StoreService.class));
		booksService = mapBooksService;
		isbn = booksService.newBook(TITLE);
	}

	@Test
	public void testFindBookByIsbn() {

		Book book = booksService.findBookByIsbn(isbn);
		Assert.assertEquals("Title must be " + TITLE, TITLE, book.getTitle());

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
