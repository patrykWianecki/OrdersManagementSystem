package com.app.repository.customer;

import com.app.model.Customer;
import com.app.repository.generic.GenericRepository;

import java.util.Optional;

public interface CustomerRepository extends GenericRepository<Customer> {

    Optional<Customer> findByNameAndSurname(String name, String surname);

    Optional<Customer> findByNameAndSurnameAndCountry(String name, String surname, Long countryId);
}
