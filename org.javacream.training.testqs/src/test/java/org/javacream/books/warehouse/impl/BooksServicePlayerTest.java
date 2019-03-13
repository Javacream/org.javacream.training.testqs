package org.javacream.books.warehouse.impl;

import org.javacream.Context;
import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.util.test.XmlPlayer;
import org.junit.Before;
import org.junit.Test;

public class BooksServicePlayerTest {

	private BooksService booksService;
	@Before public void init() {
		MapBooksService mapBooksService = new MapBooksService();
		IsbnGenerator isbnGenerator = XmlPlayer.createPlayer(IsbnGenerator.class, "/tmp/recording.xml");
		mapBooksService.setIsbnGenerator(isbnGenerator);
		mapBooksService.setStoreService(Context.storeService());

		booksService = mapBooksService;
	}
	@Test public void testCreateBatchInsert() {
		for (int i = 0; i < 10; i++) {
			String isbn = booksService.newBook("Title" + i);
			System.out.println("Created ISBN: " + isbn);
		}
	}
}
