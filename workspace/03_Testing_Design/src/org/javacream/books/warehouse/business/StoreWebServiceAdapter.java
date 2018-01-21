package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.StoreService;
import org.javacream.store.ws.client.StoreWebServiceImpl;
import org.javacream.store.ws.client.StoreWebServiceImplService;

public class StoreWebServiceAdapter implements StoreService {

	private StoreWebServiceImpl storeWebServiceImpl = new StoreWebServiceImplService().getStoreWebServiceImplPort();

	@Override
	public int getStock(String category, String id) {
		return storeWebServiceImpl.getStock(category, id);
	}
}
