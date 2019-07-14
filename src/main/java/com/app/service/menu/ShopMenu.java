package com.app.service.menu;

import java.util.Scanner;

import com.app.model.dto.CountryDto;
import com.app.model.dto.ShopDto;
import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.State;
import com.app.repository.country.CountryRepository;
import com.app.repository.country.CountryRepositoryImpl;
import com.app.service.CountryService;
import com.app.service.MyService;
import com.app.service.ShopService;
import com.app.validator.ShopValidator;
import com.app.validator.ToolsValidator;

class ShopMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static State state;

    private MyService myService = new MyService();
    private ShopService shopService = new ShopService();
    private CountryService countryService = new CountryService();
    private ShopValidator shopValidator = new ShopValidator();
    private ToolsValidator toolsValidator = new ToolsValidator();
    private CountryRepository countryRepository = new CountryRepositoryImpl();

    State printShop() {
        System.out.println("1 - add new Shop");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                state = addShop();
                break;
            case 0:
                state = State.INIT;
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.SHOP;
                break;
        }
        return state;
    }

    private State addShop() {
        System.out.println("Enter shop name");
        String name = shopValidator.validateName(scanner.nextLine());

        System.out.println("Chose country from list:");
        countryService.printAllCountries();
        long countryId = myService.oneShopFromOneCountry(name, toolsValidator.chooseId(scanner.nextLine()));

        shopService.addShop(ShopDto
            .builder()
            .name(name)
            .countryDto(CountryDto
                .builder()
                .name(countryRepository
                    .findOne(countryId)
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))
                    .getName())
                .build())
            .build());

        return State.SHOP;
    }
}
