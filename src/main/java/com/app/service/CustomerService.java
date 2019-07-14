package com.app.service;

import java.util.Comparator;

import com.app.model.dto.CustomerDto;
import com.app.model.dto.MyMapper;
import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Country;
import com.app.model.Customer;
import com.app.repository.country.CountryRepository;
import com.app.repository.country.CountryRepositoryImpl;
import com.app.repository.customer.CustomerRepository;
import com.app.repository.customer.CustomerRepositoryImpl;

public class CustomerService {

    private MyMapper mapper = new MyMapper();
    private CountryRepository countryRepository = new CountryRepositoryImpl();
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();

    public void addCustomer(CustomerDto customerDto) {
        try {
            String countryName = customerDto.getCountryDto().getName();

            Country country = countryRepository
                .findByName(countryName)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing country with name " + countryName));

            Customer customer = mapper.fromCustomerDtoToCustomer(customerDto);
            customer.setCountry(country);

            customerRepository.addOrUpdate(customer);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "Add customer exception");
        }
    }

    public void printAllCustomers() {
        customerRepository
            .findAll()
            .stream()
            .sorted(Comparator.comparing(Customer::getId))
            .forEach(customer -> System.out.println(customer.getId() + ". " + customer.getName() + " " + customer.getSurname()));
    }
}
