package com.app.service.menu;

import java.util.Scanner;

import com.app.model.State;
import com.app.model.dto.CountryDto;
import com.app.model.dto.ShopDto;
import com.app.service.CountryService;
import com.app.service.MyService;
import com.app.service.ShopService;
import com.app.validator.ShopValidator;
import com.app.validator.ToolsValidator;

import static com.app.model.State.*;

class ShopMenu {

    private static Scanner scanner = new Scanner(System.in);

    private MyService myService = new MyService();
    private ShopService shopService = new ShopService();
    private CountryService countryService = new CountryService();
    private ShopValidator shopValidator = new ShopValidator();
    private ToolsValidator toolsValidator = new ToolsValidator();

    State printShop() {
        System.out.println("1 - add new Shop");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: {
                return addShop();
            }
            case 0: {
                return INIT;
            }
            default: {
                System.out.println("Wrong choice!");
                return SHOP;
            }
        }
    }

    private State addShop() {
        System.out.println("Enter shop name");
        String name = shopValidator.validateName(scanner.nextLine());

        System.out.println("Chose country from list:");
        countryService.printAllCountries();
        long countryId = myService.findAvailableShopName(name, toolsValidator.chooseId(scanner.nextLine()));

        shopService.addShop(ShopDto.builder()
            .name(name)
            .countryDto(CountryDto.builder()
                .name(myService.findCountryByIdWithErrorCheck(countryId).getName())
                .build())
            .build());

        return SHOP;
    }
}
