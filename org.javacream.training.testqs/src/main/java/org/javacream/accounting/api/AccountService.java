package org.javacream.accounting.api;

import org.javacream.customer.api.Customer;

public interface AccountService {

	long getAccountByCustomer(Customer c);
	double getLimit(long accountId);
	//...
}
