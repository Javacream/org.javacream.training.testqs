package org.javacream.store.impl;

import java.util.HashMap;
import java.util.Properties;

import org.javacream.store.api.StoreService;
import org.javacream.util.PropertiesUtil;

public class SimpleStoreService implements StoreService {

	private HashMap<String, Properties> store;

	private PropertiesUtil propertiesUtil;
	private String ressourceName;
	public void setPropertiesUtil(PropertiesUtil propertiesUtil) {
		this.propertiesUtil = propertiesUtil;
	}

	public void setRessourceName(String ressourceName) {
		this.ressourceName = ressourceName;
	}

	{
		store = new HashMap<String, Properties>();
		store.put("books", propertiesUtil.getProperties(ressourceName));
	}

	@Override
	public int getStock(String category, String id) {
		try {
			return Integer.parseInt(store.get(category).get(id).toString());
		} catch (Exception e) {
			return 0;
		}
	}

}
