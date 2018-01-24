package org.javacream.books.warehouse.business;

import java.util.ArrayList;
import java.util.List;

import org.javacream.Context;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.implementation.MapBooksService;
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
	@Test (timeout=500)public void testCreateBatchInsert() {
		ArrayList<String> titles = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			titles.add("Title" + i);
		}
		List<String> isbns = booksService.newBooks(titles);
		System.out.println(isbns);
	}
}
