package com.app.repository;

import com.app.model.Customer;
import com.app.repository.generic.GenericRepository;

import java.util.Optional;

public interface CustomerRepository extends GenericRepository<Customer> {
    Optional<Customer> findByNameAndSurname(String name, String surname);
}
