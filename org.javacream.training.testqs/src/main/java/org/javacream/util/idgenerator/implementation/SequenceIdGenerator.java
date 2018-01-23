package org.javacream.util.idgenerator.implementation;

import org.javacream.util.idgenerator.api.IdGenerator;

public class SequenceIdGenerator implements IdGenerator {

	private long counter;


	@Override
	public long next() {
		return counter++;
	}

}
