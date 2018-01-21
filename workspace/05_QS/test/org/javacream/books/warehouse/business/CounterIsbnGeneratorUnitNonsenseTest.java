package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.IsbnGenerator;
import org.javacream.books.warehouse.test.IsbnGeneratorMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CounterIsbnGeneratorUnitNonsenseTest {

	private static String ISBN1 = "ISBN1:TEST";
	private static String ISBN2 = "ISBN2:TEST";
	private static String ISBN3 = "ISBN3:TEST";
	private IsbnGenerator isbnGenerator;
	@Before public void init(){
		IsbnGeneratorMock testDriver = new IsbnGeneratorMock();
		testDriver.addReturnValueForNextIsbn(ISBN1);
		testDriver.addReturnValueForNextIsbn(ISBN2);
		testDriver.addReturnValueForNextIsbn(ISBN3);
		testDriver.play();
		isbnGenerator = testDriver;
	}

	@Test
	public void testNextIsbnFormat() {
		String isbn = isbnGenerator.nextIsbn();
		Assert.assertEquals(ISBN1, isbn);
		isbn = isbnGenerator.nextIsbn();
		Assert.assertEquals(ISBN2, isbn);
		isbn = isbnGenerator.nextIsbn();
		Assert.assertEquals(ISBN3, isbn);
	}
	
}
