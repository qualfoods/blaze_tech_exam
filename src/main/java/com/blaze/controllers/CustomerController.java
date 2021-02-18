package com.blaze.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blaze.domain.Customer;
import com.blaze.model.CustomerCreatePojo;
import com.blaze.services.interfaces.ICustomerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
@CrossOrigin(origins="*")
public class CustomerController {

	private final ICustomerService customerService;

	@PostMapping("/new")
	public ResponseEntity<?> newCustomer(@RequestBody CustomerCreatePojo customerPojo) {
		System.out.println(customerPojo.toString());
		Customer customer = new Customer();
		customer.setFirstName(customerPojo.getFirstName());
		customer.setLastName(customerPojo.getLastName());
		customer.setEmail(customerPojo.getEmail());
		customer.setPhone(customerPojo.getPhone());
		
		Customer savedCustomer = customerService.create(customer);
		
		System.out.println("save method");
		return ResponseEntity.ok(savedCustomer);
	}
	
	@PostMapping("/list")
	public Map<String, Object> getAllCustomersInPage(
					            @RequestParam(name = "pageNum", defaultValue = "0") int pageNo,
					            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
					            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
					            @RequestBody Customer customer
								){
		return customerService.getAllCustomersInPage(pageNo, pageSize, sortBy, customer);
		
		
	}
	
	@PutMapping()
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
		Optional<Customer> customOptional = customerService.update(customer);
		if(customOptional.isPresent()) {
			return ResponseEntity.ok(customOptional.get());
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Customer");
		}
	}
	
	
}
