package com.app.service.name;

import com.app.model.Customer;
import com.app.model.Errors;
import com.app.repository.CountryRepository;
import com.app.repository.CountryRepositoryImpl;
import com.app.repository.CustomerRepository;
import com.app.repository.CustomerRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    private static Scanner scanner = new Scanner(System.in);
    private static CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private static CountryRepository countryRepository = new CountryRepositoryImpl();

    @Override
    public String setCustomerName(String name) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Wrong customer name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public String setCustomerSurname(String surname) {
        while (!surname.matches("([A-Z]+)")) {
            System.out.println("Wrong customer surname!" + "\n" + "Enter again:");
            surname = scanner.nextLine();
        }
        return surname;
    }

    @Override
    public Integer setCustomerAge(String age) {
        while (!age.matches("([1-9]+[0-9])" + "|" + "([1]+[0-2]+[0-9])")
                || Integer.valueOf(age) < 18
                || Integer.valueOf(age) > 120) {
            System.out.println("Wrong age format or age is to small!" + "\n" + "Enter again:");
            age = scanner.nextLine();
        }
        return Integer.valueOf(age);
    }

    @Override
    public Long OneCustomerFromOneCountry(String customerName, String customerSurname, Long countryId) throws Errors {
        List<Customer> customers
                = customerRepository
                .findAll()
                .stream()
                .filter(customer -> customer.getName().equals(customerName)
                        && customer.getSurname().equals(customerSurname)
                        && customer.getCountry().getId().equals(countryId))
                .collect(Collectors.toList());
        if (customers.size() != 0) {
            throw new Errors("CUSTOMER FROM CHOSEN COUNTRY ALREADY EXISTS ", LocalDate.now());
        }
        return countryId;
    }
}
