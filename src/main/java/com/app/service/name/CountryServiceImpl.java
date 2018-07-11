package com.app.service.name;

import com.app.model.Country;
import com.app.repository.CountryRepository;
import com.app.repository.CountryRepositoryImpl;

import java.util.Comparator;
import java.util.Scanner;

public class CountryServiceImpl implements CountryService {
    private Scanner scanner = new Scanner(System.in);
    private CountryRepository countryRepository = new CountryRepositoryImpl();

    @Override
    public String setCountryName(String name) {
        while (!name.matches("([A-Z]+)|")) {
            System.out.println("Wrong country name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public void printAllCountries() {
        countryRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Country::getId))
                .forEach(n -> System.out.println(n.getId() + ". " + n.getName()));
    }
}
