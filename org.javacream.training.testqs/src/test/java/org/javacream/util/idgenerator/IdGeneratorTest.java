package org.javacream.util.idgenerator;

import org.javacream.Context;
import org.javacream.util.idgenerator.api.IdGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IdGeneratorTest {
	private long generatedId;
	private IdGenerator idGenerator;

	@Before
	public void init() {
		idGenerator = Context.idGenerator();
	}

	@Test
	public void generatedIdMustNotBeNull() {
		generatedId = idGenerator.next();
		Assert.assertNotNull(generatedId);
	}

	@Test
	public void generatedIdsMustBeUnique() {
		generatedId = idGenerator.next();
		long generatedId2 = idGenerator.next();
		Assert.assertNotEquals(generatedId, generatedId2);
	}

}
