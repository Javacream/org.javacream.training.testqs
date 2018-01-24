package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.implementation.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.util.test.GenericDummy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BooksServiceUnitTest {

	private BooksService booksService;

	@Before
	public void init() {
		MapBooksService mapBooksService = new MapBooksService();
		mapBooksService.setIsbnGenerator(GenericDummy.createDummyFor(IsbnGenerator.class));
		mapBooksService.setStoreService(GenericDummy.createDummyFor(StoreService.class));
		booksService = mapBooksService;
	}

	@Test
	public void testNewBook() {
		String isbn = booksService.newBook("Hugo");
		Assert.assertNotNull(isbn);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindBookByIsbn() {
		booksService.findBookByIsbn("Egal");
	}

	@Test
	public void testNewAndFindBook() {
		final String title = "Hugo";
		String isbn = booksService.newBook(title);
		Assert.assertNotNull(isbn);
		Book book = booksService.findBookByIsbn(isbn);
		Assert.assertNotNull(book);
		Assert.assertEquals(title, book.getTitle());

	}

}
