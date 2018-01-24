package org.javacream.accounting.api;

public interface AddressService {

	boolean validate(String address);
	double distance(String address1, String address2);
}
