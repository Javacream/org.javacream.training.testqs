package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.IsbnGenerator;
import org.javacream.books.warehouse.StoreService;
import org.javacream.util.test.GenericDummy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class MapBooksServiceNewTestWithMocks {

	private static BooksService booksService;
	private static IsbnGenerator isbnGenerator;
	private static final String TEST_ISBN="1-234-567-890";
	@BeforeClass
	public static void testBooksService() {
		MapBooksService mapBooksService = new MapBooksService();
		isbnGenerator = Mockito.mock(IsbnGenerator.class);
		Mockito.when(isbnGenerator.nextIsbn()).thenReturn(TEST_ISBN);
		mapBooksService.setIsbnGenerator(isbnGenerator);
		mapBooksService.setStoreService(GenericDummy.createDummy(StoreService.class));
		booksService = mapBooksService;
	}

	@Test
	public void testNewBook() {

		String isbn= booksService.newBook("TITLE");
		Assert.assertEquals(TEST_ISBN, isbn);
		Mockito.verify(isbnGenerator, Mockito.atLeastOnce()).nextIsbn();
	}

}
