package com.app.service.menu;

import java.util.Scanner;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.State;
import com.app.repository.generic.DbConnection;

public class MenuService {

    private static State state = State.INIT;
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
            while (state != State.EXIT) {
                switch (state) {
                    case INIT:
                        printInit();
                        break;
                    case CATEGORY:
                        state = categoryMenu.printCategory();
                        break;
                    case COUNTRY:
                        state = countryMenu.printCountry();
                        break;
                    case CUSTOMER:
                        state = customerMenu.printCustomer();
                        break;
                    case ORDER:
                        state = orderMenu.printOrder();
                        break;
                    case PRODUCER:
                        state = producerMenu.printProducer();
                        break;
                    case PRODUCT:
                        state = productMenu.printProduct();
                        break;
                    case SHOP:
                        state = shopMenu.printShop();
                        break;
                    case STOCK:
                        state = stockMenu.printStock();
                        break;
                    case TRADE:
                        state = tradeMenu.printTrade();
                        break;
                    case OTHER:
                        state = otherMenu.printOther();
                        break;
                }
            }
        } catch (Exception e) {
            throw new MyException(ExceptionCode.MENU, "");
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
            case 1:
                state = State.CATEGORY;
                break;
            case 2:
                state = State.COUNTRY;
                break;
            case 3:
                state = State.CUSTOMER;
                break;
            case 4:
                state = State.ORDER;
                break;
            case 5:
                state = State.PRODUCER;
                break;
            case 6:
                state = State.PRODUCT;
                break;
            case 7:
                state = State.SHOP;
                break;
            case 8:
                state = State.STOCK;
                break;
            case 9:
                state = State.TRADE;
                break;
            case 10:
                state = State.OTHER;
                break;
            case 0: {
                state = State.EXIT;
                System.out.println("CIAO");
                break;
            }
            default: {
                System.out.println("Wrong choice!");
                state = State.INIT;
                break;
            }
        }
        return state;
    }
}
