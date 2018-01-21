
package org.javacream.store.ws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.javacream.store.ws.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetStockResponse_QNAME = new QName("http://webservice.store.javacream.org/", "getStockResponse");
    private final static QName _GetStock_QNAME = new QName("http://webservice.store.javacream.org/", "getStock");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.javacream.store.ws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetStock }
     * 
     */
    public GetStock createGetStock() {
        return new GetStock();
    }

    /**
     * Create an instance of {@link GetStockResponse }
     * 
     */
    public GetStockResponse createGetStockResponse() {
        return new GetStockResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStockResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.store.javacream.org/", name = "getStockResponse")
    public JAXBElement<GetStockResponse> createGetStockResponse(GetStockResponse value) {
        return new JAXBElement<GetStockResponse>(_GetStockResponse_QNAME, GetStockResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStock }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.store.javacream.org/", name = "getStock")
    public JAXBElement<GetStock> createGetStock(GetStock value) {
        return new JAXBElement<GetStock>(_GetStock_QNAME, GetStock.class, null, value);
    }

}
