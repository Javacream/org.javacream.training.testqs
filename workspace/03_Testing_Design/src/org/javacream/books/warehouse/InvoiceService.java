package org.javacream.books.warehouse;

public interface InvoiceService {

	void newInvoice();
	void sendInvoice();
	boolean check(String customerId, double price);
}
