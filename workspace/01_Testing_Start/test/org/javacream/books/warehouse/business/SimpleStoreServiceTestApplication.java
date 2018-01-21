package org.javacream.books.warehouse.business;

public class SimpleStoreServiceTestApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int expectedStock = 11;
		String testIsbn = "1-123-12345-1-TEST";
		String category = "books";

		SimpleStoreService simpleStoreService = new SimpleStoreService();

		int result = simpleStoreService.getStock(category, testIsbn);

		if (result == expectedStock) {
			System.out.println("OK");
		} else {
			System.err.println("Not OK");
		}

	}

}
