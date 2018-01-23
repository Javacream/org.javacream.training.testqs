package org.javacream.training.books.warehouse.business;

import org.javacream.Context;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author rainer
 *
 */
public class IsbnGeneratorTest {

	
	private String suffix;
	private String generatedIsbn;
	private String generatedIsbn2;
	private IsbnGenerator isbnGenerator;

	@Before //Test Fixture: Definition der Testdaten, Erzeugen der zu testenden Objekte
	public void init() {
		suffix = "-de";
		isbnGenerator = Context.isbnGenerator();
		
	}
	@Test
	public void generatedIsbnMustNotBeNull() {
		generatedIsbn = isbnGenerator.nextIsbn();
		Assert.assertNotNull(generatedIsbn);
	}
	@Test
	public void generatedIsbnMustEndWithProvidedSuffix() {
		generatedIsbn = isbnGenerator.nextIsbn();
		Assert.assertTrue(generatedIsbn.endsWith(suffix));
	}
	@Test
	public void generatedIsbnsMustBeUnique() {
		generatedIsbn = isbnGenerator.nextIsbn();
		generatedIsbn2 = isbnGenerator.nextIsbn();
		Assert.assertNotEquals(generatedIsbn, generatedIsbn2);
	}

	
//	public static void main(String[] args) {
//		CounterIsbnGeneratorTest test = new CounterIsbnGeneratorTest();
//		test.testCounterIsbnGenerator();
//	}
}
