package org.javacream.books.warehouse.api;

import java.util.List;

public interface BooksService {

	String newBook(String title);
	List<String> newBooks(List<String> titles);

	Book findBookByIsbn(String isbn);

}