package org.javacream.books.warehouse.impl;

import org.javacream.books.warehouse.api.IsbnGenerator;

public class CounterIsbnGenerator implements IsbnGenerator{

	private int counter;
	private String suffix;
	
	@Override
	public String nextIsbn() {
		return counter++ + "-123-12345-1" + suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
