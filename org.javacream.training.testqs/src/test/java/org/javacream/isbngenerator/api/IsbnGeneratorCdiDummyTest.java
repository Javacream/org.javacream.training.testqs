package org.javacream.isbngenerator.api;

import org.javacream.Context;
import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IsbnGeneratorCdiDummyTest {

	private String generatedIsbn;
	private String generatedIsbn2;
	private IsbnGenerator isbnGenerator;

	@Before // Test Fixture: Definition der Testdaten, Erzeugen der zu testenden Objekte
	public void init() {
		isbnGenerator = Context.isbnGeneratorDummy();
	}

	@Test
	public void generatedIsbnMustNotBeNull() {
		generatedIsbn = isbnGenerator.nextIsbn();
		Assert.assertNotNull(generatedIsbn);
	}


	//@Test Not With Dummy
	public void generatedIsbnMustHaveFourHyphens() {
		generatedIsbn = isbnGenerator.nextIsbn();
		String[] splitted = generatedIsbn.split("-");
		Assert.assertEquals(5, splitted.length);
	}

	//@Test Not With Dummy
	public void generatedIsbnsMustBeUnique() {
		generatedIsbn = isbnGenerator.nextIsbn();
		generatedIsbn2 = isbnGenerator.nextIsbn();
		Assert.assertNotEquals(generatedIsbn, generatedIsbn2);
	}
}