package com.app.service.menu;

import java.util.Scanner;

import com.app.model.State;
import com.app.service.CategoryService;
import com.app.service.CountryService;
import com.app.service.ProducerService;
import com.app.service.ProductService;
import com.app.service.ShopService;
import com.app.service.TradeService;

import static com.app.model.State.*;

class OtherMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static State state;

    private CategoryService categoryService = new CategoryService();
    private CountryService countryService = new CountryService();
    private ProducerService producerService = new ProducerService();
    private ProductService productService = new ProductService();
    private ShopService shopService = new ShopService();
    private TradeService tradeService = new TradeService();

    State printOther() {
        System.out.println("\n0 - exit");
        System.out.println("1 - print information");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0: {
                state = INIT;
                break;
            }
            case 1: {
                state = printInfo();
                break;
            }
            default: {
                System.out.println("Wrong choice!");
                state = OTHER;
                break;
            }
        }

        return state;
    }

    private State printInfo() {
        System.out.println("Chose number from list:");
        System.out.println("0 - exit");
        System.out.println("1 - show categories");
        System.out.println("2 - show countries");
        System.out.println("3 - show producers");
        System.out.println("4 - show products");
        System.out.println("5 - show shops");
        System.out.println("6 - show trades");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0: {
                state = OTHER;
                break;
            }
            case 1: {
                categoryService.printAllCategories();
                state = OTHER;
                break;
            }
            case 2: {
                countryService.printAllCountries();
                state = OTHER;
                break;
            }
            case 3: {
                producerService.printAllProducers();
                state = OTHER;
                break;
            }
            case 4: {
                productService.printAllProducts();
                state = OTHER;
                break;
            }
            case 5: {
                shopService.printAllShops();
                state = OTHER;
                break;
            }
            case 6: {
                System.out.println(tradeService.printAllTrades());
                state = OTHER;
                break;
            }
            default: {
                System.out.println("Wrong choice!");
                state = OTHER;
                break;
            }
        }

        return state;
    }
}
