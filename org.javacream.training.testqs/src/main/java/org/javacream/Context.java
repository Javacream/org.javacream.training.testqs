package org.javacream;

import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.impl.CounterIsbnGenerator;

public abstract class Context {
	
	private static IsbnGenerator isbnGenerator;
	
	static {
		//Create Objects
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		
		//set Dependencies
		counterIsbnGenerator.setSuffix("-de");
		
		isbnGenerator = counterIsbnGenerator;
	}
	
	public static IsbnGenerator isbnGenerator() {
		return isbnGenerator;
	}

}
