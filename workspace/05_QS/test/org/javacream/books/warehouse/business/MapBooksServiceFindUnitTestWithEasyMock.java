package org.javacream.books.warehouse.business;

import org.easymock.EasyMock;
import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.IsbnGenerator;
import org.javacream.books.warehouse.StoreService;
import org.javacream.books.warehouse.business.MapBooksService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceFindUnitTestWithEasyMock {

	private final static String ISBN1 = "ISBN1-TEST";
	private final static String ISBN2 = "ISBN2-TEST";

	private static final String TITLE1 = "Title1";
	private static final String TITLE2 = "Title2";

	private static BooksService booksService;
	private String UNKNOWN_ISBN = "UNKNOWN ISBN";


	@BeforeClass
	public static void testBooksService() {
		MapBooksService mapBooksService = new MapBooksService();
		IsbnGenerator isbnGeneratorMock = EasyMock.createStrictMock(IsbnGenerator.class);
		EasyMock.expect(isbnGeneratorMock.nextIsbn()).andReturn(ISBN1);
		EasyMock.expect(isbnGeneratorMock.nextIsbn()).andReturn(ISBN2);
		EasyMock.replay(isbnGeneratorMock);
		mapBooksService.setIsbnGenerator(isbnGeneratorMock);

		
		booksService = mapBooksService;
		booksService.newBook(TITLE1);
		booksService.newBook(TITLE2);

		StoreService storeServiceMock = EasyMock.createStrictMock(StoreService.class);
		EasyMock.expect(storeServiceMock.getStock("books", ISBN1)).andReturn(42);
		EasyMock.expect(storeServiceMock.getStock("books", ISBN2)).andReturn(0);
		EasyMock.replay(storeServiceMock);
		mapBooksService.setStoreService(storeServiceMock);

	}

	@Test
	public void testFindBookByIsbn() {

		Book book = booksService.findBookByIsbn(ISBN1);
		Assert.assertEquals("Title must be " + TITLE1, TITLE1, book.getTitle());
		Assert.assertTrue("Book must be available", book.isAvailable());
		book = booksService.findBookByIsbn(ISBN2);
		Assert.assertEquals("Title must be " + TITLE2, TITLE2, book.getTitle());
		Assert.assertFalse("Book must be unavailable", book.isAvailable());

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
