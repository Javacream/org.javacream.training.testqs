package org.javacream.util.idgenerator.implementation;

import java.util.Random;

import org.javacream.util.idgenerator.api.IdGenerator;

public class RandomIdGenerator implements IdGenerator {

	private Random random;

	{
		random = new Random(this.hashCode() + System.currentTimeMillis());
	}

	@Override
	public long next() {
		return random.nextLong();
	}

}
