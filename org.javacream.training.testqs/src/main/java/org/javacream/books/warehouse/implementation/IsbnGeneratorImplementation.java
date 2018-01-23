package org.javacream.books.warehouse.implementation;

import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.util.idgenerator.api.IdGenerator;

public class IsbnGeneratorImplementation implements IsbnGenerator {

	private IdGenerator idGenerator;
	private String suffix;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.javacream.books.warehouse.business.IsbnGenerator#nextIsbn()
	 */
	@Override
	public String nextIsbn() {
		return idGenerator.next() + "-123-12345-1" + suffix;
	}

	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
