package org.javacream.training.books.warehouse.business;

import org.javacream.books.warehouse.business.CounterIsbnGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author rainer
 *
 */
public class CounterIsbnGeneratorTest {

	
	private String suffix;
	private String generatedIsbn;
	private String generatedIsbn2;
	private CounterIsbnGenerator counterIsbnGenerator;

	@Before //Test Fixture: Definition der Testdaten, Erzeugen der zu testenden Objekte
	public void init() {
		suffix = "-de";
		counterIsbnGenerator = new CounterIsbnGenerator();
		counterIsbnGenerator.setSuffix(suffix);

	}
	@Test
	public void generatedIsbnMustNotBeNull() {
		generatedIsbn = counterIsbnGenerator.nextIsbn();
		Assert.assertNotNull(generatedIsbn);
	}
	@Test
	public void generatedIsbnMustEndWithProvidedSuffix() {
		generatedIsbn = counterIsbnGenerator.nextIsbn();
		Assert.assertTrue(generatedIsbn.endsWith(suffix));
	}
	@Test
	public void generatedIsbnsMustBeUnique() {
		generatedIsbn = counterIsbnGenerator.nextIsbn();
		generatedIsbn2 = counterIsbnGenerator.nextIsbn();
		Assert.assertNotEquals(generatedIsbn, generatedIsbn2);
	}

	
//	public static void main(String[] args) {
//		CounterIsbnGeneratorTest test = new CounterIsbnGeneratorTest();
//		test.testCounterIsbnGenerator();
//	}
}
