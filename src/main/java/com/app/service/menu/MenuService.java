package com.app.service.menu;

import java.util.Scanner;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.State;
import com.app.repository.generic.DbConnection;

import static com.app.model.State.*;

public class MenuService {

    private static State state = INIT;
    private static Scanner scanner = new Scanner(System.in);

    private CategoryMenu categoryMenu = new CategoryMenu();
    private CountryMenu countryMenu = new CountryMenu();
    private CustomerMenu customerMenu = new CustomerMenu();
    private OrderMenu orderMenu = new OrderMenu();
    private ProducerMenu producerMenu = new ProducerMenu();
    private ProductMenu productMenu = new ProductMenu();
    private ShopMenu shopMenu = new ShopMenu();
    private StockMenu stockMenu = new StockMenu();
    private TradeMenu tradeMenu = new TradeMenu();
    private OtherMenu otherMenu = new OtherMenu();

    public void menu() {
        try {
            while (state != EXIT) {
                switch (state) {
                    case INIT: {
                        printInit();
                        break;
                    }
                    case CATEGORY: {
                        state = categoryMenu.printCategory();
                        break;
                    }
                    case COUNTRY: {
                        state = countryMenu.printCountry();
                        break;
                    }
                    case CUSTOMER: {
                        state = customerMenu.printCustomer();
                        break;
                    }
                    case ORDER: {
                        state = orderMenu.printOrder();
                        break;
                    }
                    case PRODUCER: {
                        state = producerMenu.printProducer();
                        break;
                    }
                    case PRODUCT: {
                        state = productMenu.printProduct();
                        break;
                    }
                    case SHOP: {
                        state = shopMenu.printShop();
                        break;
                    }
                    case STOCK: {
                        state = stockMenu.printStock();
                        break;
                    }
                    case TRADE: {
                        state = tradeMenu.printTrade();
                        break;
                    }
                    case OTHER: {
                        state = otherMenu.printOther();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new MyException(ExceptionCode.MENU, "Menu exception");
        }

        DbConnection.getInstance().close();
    }

    private static State printInit() {
        System.out.println("What do you want to do?");
        System.out.println("Chose number:");
        System.out.println("1 - Category");
        System.out.println("2 - Country");
        System.out.println("3 - Customer");
        System.out.println("4 - Order");
        System.out.println("5 - Producer");
        System.out.println("6 - Product");
        System.out.println("7 - Shop");
        System.out.println("8 - Stock");
        System.out.println("9 - Trade");
        System.out.println("10 - Other");
        System.out.println("0 - Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: {
                state = CATEGORY;
                break;
            }
            case 2: {
                state = COUNTRY;
                break;
            }
            case 3: {
                state = CUSTOMER;
                break;
            }
            case 4: {
                state = ORDER;
                break;
            }
            case 5: {
                state = PRODUCER;
                break;
            }
            case 6: {
                state = PRODUCT;
                break;
            }
            case 7: {
                state = SHOP;
                break;
            }
            case 8: {
                state = STOCK;
                break;
            }
            case 9: {
                state = TRADE;
                break;
            }
            case 10: {
                state = OTHER;
                break;
            }
            case 0: {
                state = EXIT;
                System.out.println("CIAO");
                break;
            }
            default: {
                System.out.println("Wrong choice!");
                state = INIT;
                break;
            }
        }

        return state;
    }
}
