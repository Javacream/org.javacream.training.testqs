package org.javacream.books.warehouse.impl.mock;

import java.util.HashMap;

import org.easymock.EasyMock;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FindBookUnitTest {

	private BooksService booksService;

	private static final HashMap<String, Book> BOOKS = new HashMap<>();
	private static final String AVAILABLE_ISBN = "ISBN-1-2-3-4";
	private static final String UNKNOWN_ISBN = "ISBN-1-2-3-2";
	private static final String UNAVAILABLE_ISBN = "ISBN-1-2-3-5";
	private static final String TITLE = "Title";
	private static final double PRICE = 9.99;
	
	private StoreService storeService;
	@Before
	public void init() {
		MapBooksService mapBooksService = new MapBooksService();
		storeService = createMockForStoreService();
		BOOKS.put(AVAILABLE_ISBN, new Book(AVAILABLE_ISBN, TITLE, true, PRICE));
		BOOKS.put(UNAVAILABLE_ISBN, new Book(UNAVAILABLE_ISBN, TITLE, true, PRICE));
		mapBooksService.setBooks(BOOKS);
		mapBooksService.setStoreService(storeService);
		booksService = mapBooksService;
	}


	private StoreService createMockForStoreService() {
		StoreService storeServiceMock = EasyMock.createMock(StoreService.class);
		EasyMock.expect(storeServiceMock.getStock("Books", AVAILABLE_ISBN)).andReturn(42);
		EasyMock.expect(storeServiceMock.getStock("Books", UNAVAILABLE_ISBN)).andReturn(0);
		EasyMock.replay(storeServiceMock);
		return storeServiceMock;
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findBookByUnknownIsbnMustThrow() {
		booksService.findBookByIsbn(UNKNOWN_ISBN);
	}
	@Test
	public void bookIsFoundByAvailableIsbn() {
		booksService.findBookByIsbn(AVAILABLE_ISBN);
	}
	@Test
	public void bookIsAvailableForAvailableIsbn() {
		Book book = booksService.findBookByIsbn(AVAILABLE_ISBN);
		Assert.assertTrue(book.isAvailable());
	}
	@Test
	public void bookIsUnavailableForUnavailableIsbn() {
		Book book = booksService.findBookByIsbn(UNAVAILABLE_ISBN);
		Assert.assertFalse(book.isAvailable());
	}
	@Test
	public void booksServiceUsesStoreService() {
		booksService.findBookByIsbn(AVAILABLE_ISBN);
		booksService.findBookByIsbn(UNAVAILABLE_ISBN);
		EasyMock.verify(storeService);
	}


}
