package io.everyonecodes.sweetmarket.repositories;

import io.everyonecodes.sweetmarket.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findCustomerByUsername(String username);
}
