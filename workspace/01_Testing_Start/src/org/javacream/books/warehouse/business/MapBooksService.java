package org.javacream.books.warehouse.business;

import java.util.HashMap;
import java.util.Map;

import org.javacream.books.warehouse.Book;

public class MapBooksService{

	private static Map<String, Book> books;

	private CounterIsbnGenerator isbnGenerator;

	private SimpleStoreService storeService;

	{
		books = new HashMap<String, Book>();
		isbnGenerator = new CounterIsbnGenerator();
		storeService = new SimpleStoreService();
	}

	public String newBook(String title) {
		String isbn = isbnGenerator.nextIsbn();
		Book book = new Book();
		book.setTitle(title);
		book.setIsbn(isbnGenerator.nextIsbn());
		books.put(isbn, book);
		return isbn;
	}

	public Book findBookByIsbn(String isbn) {
		Book book = (Book) books.get(isbn);
		if (book == null){
			throw new NullPointerException("book not found");
		}
		book.setAvailable(storeService.getStock("books", isbn) >= 0);
		return book;
	}
	
	

}
