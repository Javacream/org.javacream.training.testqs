package org.javacream.books.warehouse;


public interface BooksService {

	public abstract String newBook(String title);

	public abstract Book findBookByIsbn(String isbn);

}