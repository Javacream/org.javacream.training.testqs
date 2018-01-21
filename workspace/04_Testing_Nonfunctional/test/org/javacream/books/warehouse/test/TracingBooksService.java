package org.javacream.books.warehouse.test;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BooksService;

public class TracingBooksService implements BooksService{

	private BooksService booksService;

	public void setBooksService(BooksService booksService) {
		this.booksService = booksService;
	}

	public String newBook(String title) {
		System.out.println("Entering newBook" );
		return booksService.newBook(title);
	}

	public Book findBookByIsbn(String isbn) {
		System.out.println("Entering findBookByIsbn" );
		return booksService.findBookByIsbn(isbn);
	}
	
	
}
