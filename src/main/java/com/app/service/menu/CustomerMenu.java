package com.app.service.menu;

import java.util.Scanner;

import com.app.model.State;
import com.app.model.dto.CountryDto;
import com.app.model.dto.CustomerDto;
import com.app.service.CountryService;
import com.app.service.CustomerService;
import com.app.service.MyService;
import com.app.validator.CustomerValidator;
import com.app.validator.ToolsValidator;

import static com.app.model.State.*;

class CustomerMenu {

    private static final String NAME = "name";
    private static final String SURNAME = "surname";

    private static Scanner scanner = new Scanner(System.in);

    private MyService myService = new MyService();
    private CustomerService customerService = new CustomerService();
    private CountryService countryService = new CountryService();
    private CustomerValidator customerValidator = new CustomerValidator();
    private ToolsValidator toolsValidator = new ToolsValidator();

    State printCustomer() {
        System.out.println("1 - add new Customer");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        State state;
        switch (choice) {
            case 1: {
                state = addCustomer();
                break;
            }
            case 0: {
                state = INIT;
                break;
            }
            default: {
                System.out.println("Wrong choice!");
                state = CUSTOMER;
                break;
            }
        }

        return state;
    }

    private State addCustomer() {
        System.out.println("Enter customer name:");
        String name = customerValidator.validateNameOrSurname(scanner.nextLine(), NAME);

        System.out.println("Enter customer surname:");
        String surname = customerValidator.validateNameOrSurname(scanner.nextLine(), SURNAME);

        System.out.println("Enter customer age:");
        int age = customerValidator.validateAge(scanner.nextLine());

        System.out.println("Chose country from list:");
        countryService.printAllCountries();

        long countryId = myService.findAvailableCustomerId(name, surname, toolsValidator.chooseId(scanner.nextLine()));

        customerService.addCustomer(CustomerDto.builder()
            .name(name)
            .surname(surname)
            .age(age)
            .countryDto(CountryDto.builder()
                .name(myService.findCountryByIdWithErrorCheck(countryId).getName())
                .build())
            .build());

        return CUSTOMER;
    }
}
