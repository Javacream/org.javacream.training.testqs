package org.javacream.books.warehouse.impl.dummy;

import org.javacream.Context;
import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BooksServiceUnitTest {

	private BooksService booksService;

	@Before
	public void init() {
		MapBooksService mapBooksService = new MapBooksService();
		mapBooksService.setIsbnGenerator(Context.createDummy(IsbnGenerator.class));
		mapBooksService.setStoreService(Context.createDummy(StoreService.class));
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
