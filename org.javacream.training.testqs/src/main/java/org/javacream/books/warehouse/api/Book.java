package org.javacream.books.warehouse.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	private String isbn;
	private String title;
	private boolean available;
	private double price;

}
