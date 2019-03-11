package org.javacream;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.impl.CounterIsbnGenerator;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.store.impl.SimpleStoreService;
import org.javacream.util.PropertiesUtil;

public abstract class Context {
	
	private static IsbnGenerator isbnGenerator;
	private static PropertiesUtil propertiesUtil;
	private static StoreService storeService;
	private static BooksService booksService;
	static {
		//Create Objects
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		propertiesUtil = new PropertiesUtil();
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		MapBooksService mapBooksService = new MapBooksService();
		
		//set Dependencies
		counterIsbnGenerator.setSuffix("-de");
		simpleStoreService.setPropertiesUtil(propertiesUtil);
		simpleStoreService.setRessourceName("books-store.properties");
		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		mapBooksService.setStoreService(simpleStoreService);
		
		//Assign fields
		isbnGenerator = counterIsbnGenerator;
		storeService = simpleStoreService;
		booksService = mapBooksService;
	}
	
	public static IsbnGenerator isbnGenerator() {
		return isbnGenerator;
	}
	public static PropertiesUtil propertiesUtil() {
		return propertiesUtil;
	}
	
	public static StoreService storeService() {
		return storeService;
	}

	public static BooksService booksService() {
		return booksService;
	}
}
