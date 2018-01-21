package org.javacream.books.warehouse.business;

import java.util.ArrayList;
import java.util.List;

import org.javacream.books.warehouse.BooksBatchInserter;
import org.javacream.books.warehouse.BooksService;

public class BooksBatchInserterImpl implements BooksBatchInserter {

	private BooksService booksService;

	public void setBooksService(BooksService booksService) {
		this.booksService = booksService;
	}

	@Override
	public List<String> insert(List<String> titles) {
			ArrayList<String> isbns = new ArrayList<String>(titles.size());
			for (String title: titles){
				isbns.add(booksService.newBook(title));
			}
			return isbns;
			
	}
}
