package org.javacream.books.warehouse.test;

import java.util.LinkedList;

import org.javacream.books.warehouse.IsbnGenerator;

public class IsbnGeneratorConfigurableTestDriver implements IsbnGenerator {

	private boolean playMode;
	private LinkedList<String> isbns = new LinkedList<String>();

	public void addReturnValueForNextIsbn(String isbn) {
		if (!playMode) {
			this.isbns.add(isbn);
			return;
		}
		throw new IllegalStateException("configuration allowed only during record mode");
	}

	@Override
	public String nextIsbn() {
		if (playMode) {
		return isbns.removeFirst();
		}
		throw new IllegalStateException("business calls allowed only during play mode");
		
	}

	public void play(){
		playMode = true;
	}
}
