package com.app.service;

import java.util.Comparator;

import com.app.model.Country;
import com.app.model.dto.CountryDto;
import com.app.repository.country.CountryRepository;
import com.app.repository.country.CountryRepositoryImpl;

import static com.app.model.dto.MyMapper.*;

public class CountryService {

    private CountryRepository countryRepository = new CountryRepositoryImpl();

    public void addCountry(CountryDto countryDto) {
        Country country = fromCountryDtoToCountry(countryDto);
        countryRepository.addOrUpdate(country);
    }

    public void printAllCountries() {
        countryRepository.findAll()
            .stream()
            .sorted(Comparator.comparing(Country::getId))
            .forEach(country -> System.out.println(country.getId() + ". " + country.getName()));
    }
}
