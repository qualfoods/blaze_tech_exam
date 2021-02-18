package com.blaze.boostrap;

import java.util.function.Supplier;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.blaze.domain.Customer;
import com.blaze.repository.CustomerRepository;

@Component
public class CustomerBoostrap implements ApplicationListener<ContextRefreshedEvent>{

	private final CustomerRepository customerRepository;
	
	public CustomerBoostrap(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadCustomers();
		
	}
	
	private void loadCustomers() {
		Customer c1 = new Customer();
		c1.setFirstName("Eric");
		c1.setLastName("Idrogo");
		c1.setEmail("ericdavid.idrogo@gmail.com");
		c1.setPhone("970356166");
		
		customerRepository.save(c1);
		
		  
		
	}
	

}
