package org.javacream.util.idgenerator;

import org.javacream.util.idgenerator.implementation.SequenceIdGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SequenceIdGeneratorTest {
	private long generatedId;
	private SequenceIdGenerator idGenerator;

	@Before public void init() {
		idGenerator = new SequenceIdGenerator();
	}
	@Test
	public void generatedIdsMustBeSequential() {
		generatedId= idGenerator.next();
		long generatedId2 = idGenerator.next();
		Assert.assertTrue((generatedId2 - generatedId) == 1 );
	}

}
