package org.javacream;

import java.util.HashMap;
import java.util.Properties;

import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.decorators.ProfilingIsbnGeneratorDecorator;
import org.javacream.books.warehouse.decorators.TracingIsbnGeneratorDecorator;
import org.javacream.books.warehouse.implementation.IsbnGeneratorImplementation;
import org.javacream.books.warehouse.implementation.MapBooksService;
import org.javacream.demo.withcdi.Demo;
import org.javacream.demo.withcdi.DemoImpl;
import org.javacream.demo.withcdi.ProfilingDemoDecorator;
import org.javacream.demo.withcdi.TracingDemoDecorator;
import org.javacream.store.api.StoreService;
import org.javacream.store.implementation.SimpleStoreService;
import org.javacream.util.PropertiesUtil;
import org.javacream.util.decorator.TracingDecorator;
import org.javacream.util.idgenerator.api.IdGenerator;
import org.javacream.util.idgenerator.implementation.SequenceIdGenerator;

public abstract class Context {

	private static IsbnGenerator isbnGenerator;
	private static StoreService storeService;
	private static BooksService booksService;
	private static IdGenerator idGenerator;
	private static Demo demo;
	
	static {
		//CREATE
		IsbnGeneratorImplementation isbnGeneratorImpl = new IsbnGeneratorImplementation();
		TracingIsbnGeneratorDecorator tracingIsbnGeneratorDecorator = new TracingIsbnGeneratorDecorator();
		ProfilingIsbnGeneratorDecorator profilingIsbnGeneratorDecorator = new ProfilingIsbnGeneratorDecorator();

		
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		HashMap<String, Properties> store = new HashMap<>();
		MapBooksService mapBooksService = new MapBooksService();
		SequenceIdGenerator sequenceIdGenerator = new SequenceIdGenerator();
		DemoImpl demoImpl = new DemoImpl();
		TracingDemoDecorator tracingDemoDecorator = new TracingDemoDecorator();
		ProfilingDemoDecorator profilingDemoDecorator = new ProfilingDemoDecorator();
		//SET DEPENDENCIES
		isbnGeneratorImpl.setSuffix("-de");
		isbnGeneratorImpl.setIdGenerator(sequenceIdGenerator);
		tracingIsbnGeneratorDecorator.setDelegate(isbnGeneratorImpl);
		profilingIsbnGeneratorDecorator.setDelegate(tracingIsbnGeneratorDecorator);

		
		store.put("books", PropertiesUtil.getProperties("/books-store.properties"));
		simpleStoreService.setStore(store);
		mapBooksService.setIsbnGenerator(isbnGeneratorImpl);
		mapBooksService.setStoreService(simpleStoreService);
		tracingDemoDecorator.setDemo(demoImpl);
		profilingDemoDecorator.setDemo(tracingDemoDecorator);
		
		//Add Decorators
		StoreService decoratedStoreService = TracingDecorator.decorate(simpleStoreService);
		//SET ATTRIBUTES
		idGenerator = sequenceIdGenerator;
		booksService = mapBooksService;
		isbnGenerator = profilingIsbnGeneratorDecorator;
		storeService = decoratedStoreService;
		demo = profilingDemoDecorator;
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
	public static Demo demo() {
		return demo;
	}

}
