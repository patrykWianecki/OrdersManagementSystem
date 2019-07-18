package com.app.service.menu;

import java.util.Scanner;

import com.app.model.dto.CountryDto;
import com.app.model.State;
import com.app.service.CountryService;
import com.app.validator.CountryValidator;

import static com.app.model.State.*;

class CountryMenu {

    private static Scanner scanner = new Scanner(System.in);

    private CountryService countryService = new CountryService();
    private CountryValidator countryValidator = new CountryValidator();

    State printCountry() {
        System.out.println("0 - exit");
        System.out.println("1 - add new Country");
        int choice = scanner.nextInt();
        scanner.nextLine();

        State state;
        switch (choice) {
            case 0: {
                state = INIT;
                break;
            }
            case 1: {
                state = addCountry();
                break;
            }
            default: {
                System.out.println("Wrong choice!");
                state = COUNTRY;
                break;
            }
        }

        return state;
    }

    private State addCountry() {
        System.out.println("Enter country name:");
        String name = countryValidator.validateName(scanner.nextLine());

        countryService.addCountry(CountryDto.builder()
            .name(name)
            .build()
        );

        return COUNTRY;
    }
}
