package org.javacream.store.webservice;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class StoreWebServiceImpl implements StoreWebService {

	@Override
	public @WebResult(name = "stock")
	int getStock(@WebParam(name = "category") String category,
			@WebParam(name = "item") String item) {
		System.out.println("Get stock...");
		return 42;
	}

	public static void main(String[] args) {
		StoreWebServiceImpl impl = new StoreWebServiceImpl();
		//Endpoint.publish("http://10.28.10.1:9090/store", impl);
		Endpoint.publish("http://localhost:9090/store", impl);
	}
}
