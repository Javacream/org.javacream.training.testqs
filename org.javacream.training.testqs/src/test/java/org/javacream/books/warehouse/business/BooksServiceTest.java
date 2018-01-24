package org.javacream.books.warehouse.business;

import java.util.ArrayList;

import org.javacream.Context;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.implementation.MapBooksService;
import org.javacream.util.test.NetworkSimulatorDecorator;
import org.junit.Before;
import org.junit.Test;

public class BooksServiceTest {

	private BooksService booksService;
	@Before public void init() {
		MapBooksService mapBooksService = new MapBooksService();
		IsbnGenerator isbnGenerator = Context.isbnGenerator();
		isbnGenerator = NetworkSimulatorDecorator.decorate(isbnGenerator, 1);
		mapBooksService.setIsbnGenerator(isbnGenerator);
		mapBooksService.setStoreService(Context.storeService());

		booksService = mapBooksService;
	}
	@Test (timeout=500)public void testCreateBatchInsert() {
		ArrayList<String> titles = new ArrayList<>(1000);
		for (int i = 0; i < 1000; i++) {
			titles.add("Title" + i);
		}
		booksService.newBooks(titles);
	}
}
