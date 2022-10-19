package io.everyonecodes.sweetmarket.security;

import io.everyonecodes.sweetmarket.domain.Customer;
import io.everyonecodes.sweetmarket.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<Customer> oCustomer = customerRepository.findCustomerByUsername(username);
		if (oCustomer.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomerPrincipal(oCustomer.get());
	}
}
