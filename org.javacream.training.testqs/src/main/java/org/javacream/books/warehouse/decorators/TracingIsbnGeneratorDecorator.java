package org.javacream.books.warehouse.decorators;

import org.javacream.books.warehouse.api.IsbnGenerator;

public class TracingIsbnGeneratorDecorator implements IsbnGenerator{
	private IsbnGenerator delegate;

	public void setDelegate(IsbnGenerator delegate) {
		this.delegate = delegate;
	}

	@Override
	public String nextIsbn() {
		System.out.println("Entering nextIsbn");
		String result = delegate.nextIsbn();
		System.out.println("Exiting nextIsbn");
		return result;
	}

}
