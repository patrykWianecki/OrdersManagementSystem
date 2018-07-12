package com.app.service.name;

import com.app.model.Country;
import com.app.repository.CountryRepository;
import com.app.repository.CountryRepositoryImpl;
import com.app.repository.CustomerRepository;
import com.app.repository.CustomerRepositoryImpl;

import java.util.Comparator;
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

    // tak czy find_by_name_and_surname i rzuca błąd
    @Override
    public Long OneCustomerFromOneCountry(String customerName, String customerSurname, Long countryId) {
        /*Customer customer
                = customerRepository
                .findByNameAndSurname(customerName, customerSurname)
                .orElseThrow(() -> new Errors("CUSTOMER IN THIS COUNTRY ALREADY EXISTS ", LocalDate.now()));*/
        List<Long> customersCountryId
                = customerRepository
                .findAll()
                .stream()
                .filter(x -> x.getName().equals(customerName) && x.getSurname().equals(customerSurname))
                .map(x -> x.getCountry().getId())
                .collect(Collectors.toList());
        Long finalCountryId = countryId;
        while (countryId.equals(customersCountryId
                .stream()
                .filter(x -> x.equals(finalCountryId))
                .findAny()
                .get())
                ) {
            System.out.println("Customer " + customerName + " " + customerSurname + " already exist in this country!\nChose other country");
            availableCountryName(customersCountryId);
            countryId = scanner.nextLong();
            scanner.nextLine();
        }

        return countryId;
    }

    private static void availableCountryName(List<Long> id) {
        List<Long> countriesId = getCountriesId();
        countriesId.removeAll(id);
        countryRepository
                .findAll()
                .stream()
                .filter(countryId -> countriesId.contains(countryId.getId()))
                .sorted(Comparator.comparing(Country::getId))
                .forEach(country -> System.out.println(country.getId() + ". " + country.getName()));
    }

    private static List<Long> getCountriesId() {
        return countryRepository
                .findAll()
                .stream()
                .map(Country::getId)
                .collect(Collectors.toList());
    }
}
