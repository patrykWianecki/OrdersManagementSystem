package com.app.service.menu;

import java.util.Scanner;

import com.app.model.State;
import com.app.service.MyService;
import com.app.service.ProductService;
import com.app.service.ShopService;
import com.app.validator.StockValidator;
import com.app.validator.ToolsValidator;

import static com.app.model.State.*;

class StockMenu {

    private static Scanner scanner = new Scanner(System.in);

    private MyService myService = new MyService();
    private ShopService shopService = new ShopService();
    private ProductService productService = new ProductService();
    private StockValidator stockValidator = new StockValidator();
    private ToolsValidator toolsValidator = new ToolsValidator();

    State printStock() {
        System.out.println("1 - add new Stock");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: {
                return addStock();
            }
            case 0: {
                return INIT;
            }
            default: {
                System.out.println("Wrong choice!");
                return STOCK;
            }
        }
    }

    private State addStock() {
        System.out.println("Enter quantity");
        int quantity = stockValidator.validateQuantity(scanner.nextLine());

        System.out.println("Chose shop from list:");
        System.out.println(shopService.printAllShops());

        long shopId = toolsValidator.chooseId(scanner.nextLine());
        System.out.println("Chose product from list:");
        productService.printAllProducts();

        long productId = toolsValidator.chooseId(scanner.nextLine());
        myService.oneProductFromOneShopInStock(shopId, productId, quantity);

        return STOCK;
    }
}
