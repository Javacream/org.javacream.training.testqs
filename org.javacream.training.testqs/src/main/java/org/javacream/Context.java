package org.javacream;

import java.util.HashMap;
import java.util.Properties;

import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.implementation.CounterIsbnGenerator;
import org.javacream.books.warehouse.implementation.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.store.implementation.SimpleStoreService;
import org.javacream.util.PropertiesUtil;

public abstract class Context {

	private static IsbnGenerator isbnGenerator;
	private static StoreService storeService;
	private static BooksService booksService;

	static {
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		counterIsbnGenerator.setSuffix("-de");
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		HashMap<String, Properties> store = new HashMap<>();
		store.put("books", PropertiesUtil.getProperties("/books-store.properties"));
		simpleStoreService.setStore(store);
		MapBooksService mapBooksService = new MapBooksService();
		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		mapBooksService.setStoreService(simpleStoreService);
		booksService = mapBooksService;
		isbnGenerator = counterIsbnGenerator;
		storeService = simpleStoreService;
	}
	public static IsbnGenerator isbnGenerator() {
		return isbnGenerator;
	}

	public static StoreService storeService() {
		return storeService;
	}
	
	public static BooksService booksService() {
		return booksService;
	}

}
