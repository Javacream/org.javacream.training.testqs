package org.javacream.training.books.warehouse.business;

import org.easymock.EasyMock;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.implementation.MapBooksService;
import org.javacream.store.api.StoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EasyMockBooksServiceUnitTest {

	private BooksService booksService;
	private String ISBN = "ISBN-42";
	private IsbnGenerator isbnGeneratorMock;
	private StoreService storeServiceMock;

	@Before
	public void init() {
		isbnGeneratorMock = EasyMock.createMock(IsbnGenerator.class);
		storeServiceMock = EasyMock.createMock(StoreService.class);
		// "Aufzeichnungsphase"
		EasyMock.expect(isbnGeneratorMock.nextIsbn()).andReturn(ISBN);
		EasyMock.expect(storeServiceMock.getStock("books", ISBN)).andReturn(0);
		// Scharf schalten der Mocks
		EasyMock.replay(isbnGeneratorMock, storeServiceMock);
		MapBooksService mapBooksService = new MapBooksService();
		mapBooksService.setIsbnGenerator(isbnGeneratorMock);
		mapBooksService.setStoreService(storeServiceMock);
		booksService = mapBooksService;
	}

	@Test
	public void testNewBook() {
		String isbn = booksService.newBook("Hugo");
		Assert.assertEquals(ISBN, isbn);
		EasyMock.verify(isbnGeneratorMock);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFindBookByIsbn() {
		booksService.findBookByIsbn("Egal");
	}

	@Test
	public void testNewAndFindBook() {
		final String title = "Hugo";
		String isbn = booksService.newBook(title);
		Book book = booksService.findBookByIsbn(isbn);
		Assert.assertNotNull(book);
		Assert.assertEquals(title, book.getTitle());
		Assert.assertFalse(book.isAvailable());
	}

}
