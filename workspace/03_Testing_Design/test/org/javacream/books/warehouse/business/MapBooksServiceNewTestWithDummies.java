package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.IsbnGenerator;
import org.javacream.books.warehouse.StoreService;
import org.javacream.util.test.GenericDummy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceNewTestWithDummies {

	private static BooksService booksService;

	@BeforeClass
	public static void testBooksService() {
		MapBooksService mapBooksService = new MapBooksService();
		mapBooksService.setIsbnGenerator(GenericDummy.createDummy(IsbnGenerator.class));
		mapBooksService.setStoreService(GenericDummy.createDummy(StoreService.class));
		booksService = mapBooksService;
	}

	@Test
	public void testNewBook() {

		String isbn= booksService.newBook("TITLE");
		Assert.assertNotNull("Generated ISBN was null!", isbn);

	}

}
