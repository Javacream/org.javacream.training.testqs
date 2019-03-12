package org.javacream.books.warehouse.impl.mock;

import org.easymock.EasyMock;
import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NewBookUnitTest {

	private BooksService booksService;

	private static final String ISBN = "ISBN-1-2-3-4";
	@Before
	public void init() {
		MapBooksService mapBooksService = new MapBooksService();
		mapBooksService.setIsbnGenerator(createMockForIsbnGenerator());
		booksService = mapBooksService;
	}


	private IsbnGenerator createMockForIsbnGenerator() {
		IsbnGenerator isbnGeneratorMock = EasyMock.createMock(IsbnGenerator.class);
		EasyMock.expect(isbnGeneratorMock.nextIsbn()).andReturn("ISBN-1-2-3-4");
		EasyMock.replay(isbnGeneratorMock);
		return isbnGeneratorMock;
	}

	@Test
	public void newBookCreatesIsbn() {
		String isbn = booksService.newBook("Hugo");
		Assert.assertEquals(ISBN, isbn);
	}

}
