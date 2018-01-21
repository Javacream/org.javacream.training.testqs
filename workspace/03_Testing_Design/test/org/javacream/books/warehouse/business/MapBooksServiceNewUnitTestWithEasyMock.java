package org.javacream.books.warehouse.business;

import org.easymock.EasyMock;
import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.IsbnGenerator;
import org.javacream.books.warehouse.StoreService;
import org.javacream.books.warehouse.business.MapBooksService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceNewUnitTestWithEasyMock {

	private final static String ISBN1 = "ISBN1-TEST";
	private final static String ISBN2 = "ISBN2-TEST";

	private static final String TITLE1 = "Title1";
	private static final String TITLE2 = "Title2";

	private static BooksService booksService;

	@BeforeClass
	public static void testBooksService() {
		MapBooksService mapBooksService = new MapBooksService();
		//CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		//counterIsbnGenerator.setSuffix("-TEST");
//		IsbnGeneratorMock testDriver = new IsbnGeneratorMock();
//		testDriver.addReturnValueForNextIsbn(ISBN1);
//		testDriver.addReturnValueForNextIsbn(ISBN2);
//		testDriver.play();
		IsbnGenerator isbnGeneratorMock = EasyMock.createStrictMock(IsbnGenerator.class);
		EasyMock.expect(isbnGeneratorMock.nextIsbn()).andReturn(ISBN1);
		EasyMock.expect(isbnGeneratorMock.nextIsbn()).andReturn(ISBN2);
		EasyMock.replay(isbnGeneratorMock);
		mapBooksService.setIsbnGenerator(isbnGeneratorMock);
		
		StoreService storeServiceMock = EasyMock.createStrictMock(StoreService.class);
		EasyMock.expect(storeServiceMock.getStock("books", ISBN1)).andReturn(42);
		EasyMock.expect(storeServiceMock.getStock("books", ISBN2)).andReturn(0);
		EasyMock.replay(storeServiceMock);
		//mapBooksService.setStoreService(GenericDummy.createDummy(StoreService.class));
		//testDriver.addReturnValueForNextIsbn(ISBN2);
		
		mapBooksService.setStoreService(storeServiceMock);
		
		booksService = mapBooksService;
	}

	@Test
	public void testNewBook() {

		String isbn1 = booksService.newBook(TITLE1);
		String isbn2 = booksService.newBook(TITLE2);

		Assert.assertEquals(ISBN1, isbn1);
		Assert.assertEquals(ISBN2, isbn2);

	}

}
