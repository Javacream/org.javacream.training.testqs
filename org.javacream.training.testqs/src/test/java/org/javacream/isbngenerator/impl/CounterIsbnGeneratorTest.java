package org.javacream.isbngenerator.impl;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.impl.CounterIsbnGenerator;
import org.javacream.util.testqs.TracingAspect;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CounterIsbnGeneratorTest {


	private String suffix;
	private String generatedIsbn;
	private String generatedIsbn2;
	private IsbnGenerator isbnGenerator;

	@Before //Test Fixture: Definition der Testdaten, Erzeugen der zu testenden Objekte
	public void init() {
		suffix = "-de";
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		counterIsbnGenerator.setSuffix(suffix);
		isbnGenerator = TracingAspect.aspect(counterIsbnGenerator);

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
	public void generatedIsbnMustHaveFourHyphens() {
		generatedIsbn = isbnGenerator.nextIsbn();
		String[] splitted = generatedIsbn.split("-");
		Assert.assertEquals(5, splitted.length);
	}
	@Test
	public void generatedIsbnsMustBeUnique() {
		generatedIsbn = isbnGenerator.nextIsbn();
		generatedIsbn2 = isbnGenerator.nextIsbn();
		Assert.assertNotEquals(generatedIsbn, generatedIsbn2);
	}
	
	
}