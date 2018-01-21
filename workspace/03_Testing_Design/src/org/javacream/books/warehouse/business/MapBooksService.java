package org.javacream.books.warehouse.business;

import java.util.HashMap;
import java.util.Map;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.IsbnGenerator;
import org.javacream.books.warehouse.StoreService;


public class MapBooksService implements BooksService{

	private static Map<String, Book> books;

	private IsbnGenerator isbnGenerator;

	public void setIsbnGenerator(IsbnGenerator isbnGenerator) {
		this.isbnGenerator = isbnGenerator;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	private StoreService storeService;

	{
		books = new HashMap<String, Book>();
	}


	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.withinterfaces.BooksService#newBook(java.lang.String)
	 */
	@Override
	public String newBook(String title) {
		if (title == null){
			throw new IllegalArgumentException("title was null");
		}
		if (title.trim().length() < 3){
			throw new IllegalArgumentException("title to short: " + title);
		}
		String isbn = isbnGenerator.nextIsbn();
		//String isbn = "1-" + Long.toString(System.nanoTime()); 
		//isbn = "ISBN1-" + isbn;
		//isbn = "1-" + Long.toString(System.nanoTime()); 
		//String isbn = "1-234-567-890";
		
		isbn = isbnGenerator.nextIsbn();
		Book book = new Book();
		book.setTitle(title);
		book.setIsbn(isbn);
		books.put(book.getIsbn(), book);
//		book.setIsbn(isbnGenerator.nextIsbn());
//		books.put(book.getIsbn(), book);

		return book.getIsbn();
	}

	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.withinterfaces.BooksService#findBookByIsbn(java.lang.String)
	 */
	@Override
	public Book findBookByIsbn(String isbn) {
		if (isbn == null){
			throw new IllegalArgumentException("isbn was null");
		}
		Book book = (Book) books.get(isbn);
		if (book == null){
			throw new IllegalArgumentException("book not found");
		}
		book.setAvailable(storeService.getStock("books", isbn) > 0);
		return book;
	}
	
	

}
