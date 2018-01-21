package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.BooksService;
import org.javacream.util.test.BaseDecorator;
import org.javacream.util.test.TracingDecorator;
import org.javacream.util.test.TracingInvocationHandler;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceNewUnitTestWithTestDecorations {

	private static final String TITLE1 = "Title1";
	private static final String TITLE2 = "Title2";

	private static BooksService booksService;

	@BeforeClass
	public static void testBooksService() {
		MapBooksService mapBooksService = new MapBooksService();
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		counterIsbnGenerator.setSuffix("-TEST");
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		simpleStoreService.setPropertiesUtil(TracingInvocationHandler.decorate(new SimplePropertiesUtil()));
		mapBooksService.setStoreService(simpleStoreService);
		mapBooksService.setIsbnGenerator(counterIsbnGenerator);

		
		//BooksService tracingBooksService2 = TracingInvocationHandler.decorate(mapBooksService);
//		booksService = PerformanceInvocationHandler.decorate(mapBooksService);
//		booksService = TracingInvocationHandler.decorate(booksService);

		booksService = BaseDecorator.decorate(mapBooksService, TracingDecorator.class);
	}

	@Test
	public void testNewBook() {

		String isbn1 = booksService.newBook(TITLE1);
		Assert.assertNotNull(isbn1);
		String isbn2 = booksService.newBook(TITLE2);
		Assert.assertNotNull(isbn2);
		Assert.assertFalse(isbn1.equals(isbn2));


	}

}
