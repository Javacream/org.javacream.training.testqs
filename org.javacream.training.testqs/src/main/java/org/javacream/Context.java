package org.javacream;

import java.lang.reflect.Proxy;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.impl.CounterIsbnGenerator;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.store.impl.SimpleStoreService;
import org.javacream.util.PropertiesUtil;
import org.javacream.util.testqs.GenericDummy;

public abstract class Context {
	
	private static IsbnGenerator isbnGenerator;
	private static PropertiesUtil propertiesUtil;
	private static StoreService storeService;
	private static BooksService booksService;
	private static IsbnGenerator isbnGeneratorDummy;
	static {
		//Create Objects
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		propertiesUtil = new PropertiesUtil();
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		MapBooksService mapBooksService = new MapBooksService();
		
		//set Dependencies
		counterIsbnGenerator.setSuffix("-de");
		simpleStoreService.setPropertiesUtil(propertiesUtil);
		simpleStoreService.setRessourceName("/books-store.properties");
		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		mapBooksService.setStoreService(simpleStoreService);
		
		//initialize
		simpleStoreService.init();
		
		//Assign fields
		isbnGenerator = counterIsbnGenerator;
		storeService = simpleStoreService;
		booksService = mapBooksService;
		isbnGeneratorDummy = createDummy(IsbnGenerator.class);
	}
	
	public static IsbnGenerator isbnGeneratorDummy() {
		return isbnGeneratorDummy;
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
	
	public static <T> T createDummy(Class... interfaces) {
		ClassLoader classLoader = GenericDummy.class.getClassLoader();
		GenericDummy dummy = new GenericDummy();
		return (T) Proxy.newProxyInstance(classLoader, interfaces, dummy);
	}
}
