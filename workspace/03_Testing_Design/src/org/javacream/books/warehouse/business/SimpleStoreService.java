package org.javacream.books.warehouse.business;

import java.util.HashMap;
import java.util.Properties;

import org.javacream.books.warehouse.PropertiesUtil;
import org.javacream.books.warehouse.StoreService;

public class SimpleStoreService implements StoreService {

	private HashMap<String, Properties> store;

	//private PropertiesUtil propertiesUtil; 
	public void setPropertiesUtil(PropertiesUtil propertiesUtil) {
		//this.propertiesUtil = propertiesUtil;
		store.put("books", propertiesUtil.getProperties("books-store.properties"));
	}

	{
		store = new HashMap<String, Properties>();
		//propertiesUtil = new SimplePropertiesUtil();
	}

	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.withinterfaces.StoreService#getStock(java.lang.String, java.lang.String)
	 */
	@Override
	public int getStock(String category, String id) {
		try {
			return Integer.parseInt(store.get(category).get(id).toString());
		} catch (Exception e) {
			//e.printStackTrace();
			return 0;
		}
	}

}
