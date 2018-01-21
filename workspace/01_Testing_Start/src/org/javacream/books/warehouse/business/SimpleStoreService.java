package org.javacream.books.warehouse.business;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class SimpleStoreService {

	private HashMap<String, Properties> store;

	{
		store = new HashMap<String, Properties>();
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getResourceAsStream(
					"books-store.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		store.put("books", properties);
	}

	public int getStock(String category, String id) {

		return Integer.parseInt(store.get(category).get(id).toString());
	}

	public static void main(String[] args) {
		SimpleStoreService service = new SimpleStoreService();
		System.out.println(service.getStock("books", "1-123-12345-1-TEST"));
	}
}
