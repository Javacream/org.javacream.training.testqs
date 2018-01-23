package org.javacream.books.warehouse.decorators;

import org.javacream.books.warehouse.api.IsbnGenerator;

public class ProfilingIsbnGeneratorDecorator implements IsbnGenerator{
	private IsbnGenerator delegate;

	public void setDelegate(IsbnGenerator delegate) {
		this.delegate = delegate;
	}

	@Override
	public String nextIsbn() {
		long start = System.currentTimeMillis();
		String result = delegate.nextIsbn();
		System.out.println("calling nextIsbn took " + (System.currentTimeMillis() - start) + " msec");
		return result;
	}

}
