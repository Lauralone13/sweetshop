package io.everyonecodes.sweetmarket.logic;

import io.everyonecodes.sweetmarket.domain.Customer;
import io.everyonecodes.sweetmarket.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	// Admins only
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
}
