package com.blaze.services.interfaces;

import java.util.Map;
import java.util.Optional;

import com.blaze.domain.Customer;

public interface ICustomerService {
	Customer create(Customer customer);
	Map<String, Object> getAllCustomersInPage(int pageNo, int pageSize,String sortBy,Customer customer);
	Optional<Customer> update(Customer customer);
}
