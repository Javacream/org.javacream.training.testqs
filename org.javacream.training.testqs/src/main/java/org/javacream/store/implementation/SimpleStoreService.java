package org.javacream.store.implementation;

import java.util.HashMap;
import java.util.Properties;

import org.javacream.store.api.StoreService;

public class SimpleStoreService implements StoreService {

	private HashMap<String, Properties> store;

	public void setStore(HashMap<String, Properties> store) {
		this.store = store;
	}

	{
		store = new HashMap<String, Properties>();
		//store.put("books", PropertiesUtil.getProperties("books-store.properties"));
	}

	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.StoreService#getStock(java.lang.String, java.lang.String)
	 */
	@Override
	public int getStock(String category, String id) {
		try {
			return Integer.parseInt(store.get(category).get(id).toString());
		} catch (Exception e) {
			return 0;
		}
	}

}
