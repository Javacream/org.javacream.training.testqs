package org.javacream.books.warehouse.test;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BooksService;

public class PerformanceBooksService implements BooksService{

	private BooksService booksService;

	public void setBooksService(BooksService booksService) {
		this.booksService = booksService;
	}

	public String newBook(String title) {
		String result = booksService.newBook(title);
		System.out.println("Calling newBook took ..." );
		return result;
	}

	public Book findBookByIsbn(String isbn) {
		Book result = booksService.findBookByIsbn(isbn);
		System.out.println("Calling findBookByIsbn took ..." );
		return result;
	}
	
	
}
