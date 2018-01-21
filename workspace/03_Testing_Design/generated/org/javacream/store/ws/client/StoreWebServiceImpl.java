
package org.javacream.store.ws.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "StoreWebServiceImpl", targetNamespace = "http://webservice.store.javacream.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface StoreWebServiceImpl {


    /**
     * 
     * @param category
     * @param item
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(name = "stock", targetNamespace = "")
    @RequestWrapper(localName = "getStock", targetNamespace = "http://webservice.store.javacream.org/", className = "org.javacream.store.ws.client.GetStock")
    @ResponseWrapper(localName = "getStockResponse", targetNamespace = "http://webservice.store.javacream.org/", className = "org.javacream.store.ws.client.GetStockResponse")
    @Action(input = "http://webservice.store.javacream.org/StoreWebServiceImpl/getStockRequest", output = "http://webservice.store.javacream.org/StoreWebServiceImpl/getStockResponse")
    public int getStock(
        @WebParam(name = "category", targetNamespace = "")
        String category,
        @WebParam(name = "item", targetNamespace = "")
        String item);

}
