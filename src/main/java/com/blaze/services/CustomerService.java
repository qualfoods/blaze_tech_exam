package com.blaze.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.blaze.domain.Customer;
import com.blaze.repository.CustomerRepository;
import com.blaze.services.interfaces.ICustomerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService{
	
	private final CustomerRepository customerRepository;
	private final MongoTemplate mongoTemplate;
	
	@Override
	public Customer create(Customer customer) {		
		return customerRepository.save(customer);
	}

	@Override
	public Map<String, Object> getAllCustomersInPage(int pageNo, int pageSize, String sortBy, Customer customer) {
		Example<Customer> example = Example.of(customer);
		Map<String, Object> response = new HashMap<>();
		
		Sort sort = Sort.by(sortBy);
		Pageable page = PageRequest.of(pageNo, pageSize);
		Page<Customer> customPage = customerRepository.findAll(example, page);
		
		response.put("data", customPage.getContent());
		response.put("totalPages",customPage.getTotalPages());
		response.put("total", customPage.getTotalElements());
		response.put("page", customPage.getNumber());
		System.out.println(customPage.getContent());
		return response;
	}

	public Optional<Customer> update(Customer customer){
		Optional<Customer> customOptional = customerRepository.findById(customer.getId());
		if(customOptional.isPresent()) {
			Customer existCustom = customOptional.get();
			customer.setCreated_date(existCustom.getCreated_date());
			
			customerRepository.save(customer);
			return Optional.of(customer);
		}
		return customOptional;
	}
	
}
