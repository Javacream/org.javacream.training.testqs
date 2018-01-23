package org.javacream;

import java.util.HashMap;
import java.util.Properties;

import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.implementation.IsbnGeneratorImplementation;
import org.javacream.books.warehouse.implementation.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.store.implementation.SimpleStoreService;
import org.javacream.util.PropertiesUtil;
import org.javacream.util.idgenerator.api.IdGenerator;
import org.javacream.util.idgenerator.implementation.SequenceIdGenerator;

public abstract class Context {

	private static IsbnGenerator isbnGenerator;
	private static StoreService storeService;
	private static BooksService booksService;
	private static IdGenerator idGenerator;

	static {
		//CREATE
		IsbnGeneratorImplementation isbnGeneratorImpl = new IsbnGeneratorImplementation();
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		HashMap<String, Properties> store = new HashMap<>();
		MapBooksService mapBooksService = new MapBooksService();
		SequenceIdGenerator sequenceIdGenerator = new SequenceIdGenerator();

		//SET DEPENDENCIES
		isbnGeneratorImpl.setSuffix("-de");
		isbnGeneratorImpl.setIdGenerator(sequenceIdGenerator);
		store.put("books", PropertiesUtil.getProperties("/books-store.properties"));
		simpleStoreService.setStore(store);
		mapBooksService.setIsbnGenerator(isbnGeneratorImpl);
		mapBooksService.setStoreService(simpleStoreService);
		
		//SET ATTRIBUTES
		idGenerator = sequenceIdGenerator;
		booksService = mapBooksService;
		isbnGenerator = isbnGeneratorImpl;
		storeService = simpleStoreService;
	}
	public static IsbnGenerator isbnGenerator() {
		return isbnGenerator;
	}
	public static IdGenerator idGenerator() {
		return idGenerator;
	}

	public static StoreService storeService() {
		return storeService;
	}
	
	public static BooksService booksService() {
		return booksService;
	}

}
