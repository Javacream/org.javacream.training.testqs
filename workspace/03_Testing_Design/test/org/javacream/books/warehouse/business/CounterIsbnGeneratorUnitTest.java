package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.business.CounterIsbnGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CounterIsbnGeneratorUnitTest {

	private CounterIsbnGenerator isbnGenerator;
	private static final String SUFFIX = "-TEST-SUFFIX";
	@Before public void init(){
		isbnGenerator = new CounterIsbnGenerator();
		isbnGenerator.setSuffix(SUFFIX);
	}

	@Test
	public void testNextIsbnFormat() {
		String isbn = isbnGenerator.nextIsbn();
		Assert.assertNotNull("generated ISBN must not be null!", isbn);
		String start = isbn.substring(0, isbn.indexOf("-"));
		Integer.parseInt(start);
	}
	
	@Test
	public void testSuffix(){
		String isbn = isbnGenerator.nextIsbn();
		Assert.assertTrue("ISBN must end with " + SUFFIX, isbn.endsWith(SUFFIX));
	}
	@Test
	public void testUnique(){
		String isbn = isbnGenerator.nextIsbn();
		String isbn2 = isbnGenerator.nextIsbn();
		Assert.assertTrue("ISBNs must be unique", !isbn.equals(isbn2));
	}

}
