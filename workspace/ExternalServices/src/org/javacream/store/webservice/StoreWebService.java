package org.javacream.store.webservice;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface StoreWebService {
	public @WebResult(name = "stock")
	int getStock(@WebParam(name = "category") String category,
			@WebParam(name = "item") String item);
}
