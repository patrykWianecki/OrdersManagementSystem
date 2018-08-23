package com.app;

import com.app.dto.*;
import com.app.model.EMessage;
import com.app.model.EPayment;
import com.app.model.Errors;
import com.app.repository.*;
import com.app.repository.generic.DbConnection;
import com.app.service.MyService;
import com.app.service.MyServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author Patryk Wianecki
 * @version 1.0
 */

public class App {

    private static MyService myService = new MyServiceImpl();
    private static CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private static CountryRepository countryRepository = new CountryRepositoryImpl();
    private static CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private static ProducerRepository producerRepository = new ProducerRepositoryImpl();
    private static ProductRepository productRepository = new ProductRepositoryImpl();
    private static TradeRepository tradeRepository = new TradeRepositoryImpl();
    private static Scanner scanner = new Scanner(System.in);
    private static State state = State.INIT;

    public static void main(String[] args) {
        myService.initializeData();
        DbConnection.getInstance().close();
//        menu();
    }

    private enum State {
        INIT, EXIT, CATEGORY, COUNTRY, CUSTOMER, PRODUCER, PRODUCT, SHOP, STOCK, TRADE, ORDER, OTHER
    }

    private static void menu() {
        try {
            while (state != State.EXIT) {
                switch (state) {
                    case INIT:
                        printInit();
                        break;
                    case CATEGORY:
                        printCategory();
                        break;
                    case COUNTRY:
                        printCountry();
                        break;
                    case CUSTOMER:
                        printCustomer();
                        break;
                    case ORDER:
                        printOrder();
                        break;
                    case PRODUCER:
                        printProducer();
                        break;
                    case PRODUCT:
                        printProduct();
                        break;
                    case SHOP:
                        printShop();
                        break;
                    case STOCK:
                        printStock();
                        break;
                    case TRADE:
                        printTrade();
                        break;
                    case OTHER:
                        printOther();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            case 0:
                state = State.EXIT;
                System.out.println("CIAO");
                break;
            default:
                System.out.println("Wrong choice!");
                state = state.INIT;
                break;
        }
        return state;
    }

    private static State printCategory() {
        System.out.println("0 - exit");
        System.out.println("1 - add new Category");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0:
                state = State.INIT;
                break;
            case 1:
                addCategory();
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.CATEGORY;
                break;
        }
        return state;
    }

    private static State printCountry() {
        System.out.println("0 - exit");
        System.out.println("1 - add new Country");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0:
                state = State.INIT;
                break;
            case 1:
                addCountry();
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.COUNTRY;
                break;
        }
        return state;
    }

    private static State printCustomer() {
        System.out.println("1 - add new Customer");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addCustomer();
                break;
            case 0:
                state = State.INIT;
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.CUSTOMER;
                break;
        }
        return state;
    }

    private static State printOrder() {
        System.out.println("1 - add new Order");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addOrder();
                break;
            case 0:
                state = State.INIT;
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.ORDER;
                break;
        }
        return state;
    }

    private static State printProducer() {
        System.out.println("1 - add new Producer");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addProducer();
                break;
            case 0:
                state = State.INIT;
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.PRODUCER;
                break;
        }
        return state;
    }

    private static State printProduct() {
        System.out.println("1 - add new Product");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addProduct();
                break;
            case 0:
                state = State.INIT;
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.PRODUCT;
                break;
        }
        return state;
    }

    private static State printShop() {
        System.out.println("1 - add new Shop");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addShop();
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

    private static State printStock() {
        System.out.println("1 - add new Stock");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addStock();
                break;
            case 0:
                state = State.INIT;
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.STOCK;
                break;
        }
        return state;
    }

    private static State printTrade() {
        System.out.println("0 - exit");
        System.out.println("1 - add new Trade");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0:
                state = State.INIT;
                break;
            case 1:
                addTrade();
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.TRADE;
                break;
        }
        return state;
    }

    private static State printOther() {
        System.out.println("\n0 - exit");
        System.out.println("1 - print information");
        System.out.println("2 - add data from file");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0:
                state = State.INIT;
                break;
            case 1:
                printInfo();
                break;
            case 2:
                myService.initializeData();
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.OTHER;
                break;
        }
        return state;
    }

    private static State printInfo() {
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
                state = State.OTHER;
                break;
            }
            case 1: {
                myService.printAllCategories();
                state = State.OTHER;
                break;
            }
            case 2: {
                myService.printAllCountries();
                state = State.OTHER;
                break;
            }
            case 3: {
                myService.printAllProducers();
                state = State.OTHER;
                break;
            }
            case 4: {
                myService.printAllProducts();
                state = State.OTHER;
                break;
            }
            case 5: {
                myService.printAllShops();
                state = State.OTHER;
                break;
            }
            case 6: {
                myService.printAllTrades();
                state = State.OTHER;
                break;
            }
            default: {
                System.out.println("Wrong choice!");
                state = State.OTHER;
                break;
            }
        }
        return state;
    }

    private static State addCategory() {
        System.out.println("Enter Category name:");
        String name = myService.setCategoryName(scanner.nextLine());
        myService.addCategory(CategoryDto
                .builder()
                .name(name)
                .build());
        state = State.CATEGORY;
        return state;
    }

    private static State addCountry() {
        System.out.println("Enter country name:");
        String name = myService.setCountryName(scanner.nextLine());
        myService.addCountry(CountryDto
                .builder()
                .name(name)
                .build());
        state = State.COUNTRY;
        return state;
    }

    private static State addCustomer() {
        System.out.println("Enter customer name:");
        String name = myService.setCustomerName(scanner.nextLine());
        System.out.println("Enter customer surname:");
        String surname = myService.setCustomerSurname(scanner.nextLine());
        System.out.println("Enter customer age:");
        int age = myService.setCustomerAge(scanner.nextLine());
        System.out.println("Chose country from list:");
        myService.printAllCountries();
        long countryId = myService.oneCustomerFromOneCountry(name, surname, myService.setId(scanner.nextLine()));
        myService.addCustomer(CustomerDto
                .builder()
                .name(name)
                .surname(surname)
                .age(age)
                .countryDto(CountryDto
                        .builder()
                        .name(countryRepository
                                .findOne(countryId)
                                .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " COUNTRY ", LocalDate.now()))
                                .getName())
                        .build())
                .build());
        state = State.CUSTOMER;
        return state;
    }

    private static State addOrder() {
        System.out.println("Chose product from list:");
        myService.printAllProducts();
        long productId = myService.setId(scanner.nextLine());
        System.out.println("Chose customer from list:");
        myService.printAllCustomers();
        long customerId = myService.setId(scanner.nextLine());
        System.out.println("Enter date:");
        LocalDate date = myService.setOrderDate();
        System.out.println("Enter discount:");
        BigDecimal discount = myService.setOrderDiscount(scanner.nextLine());
        System.out.println("Chose payment id from list:");
        myService.printAllPayments();
        int paymentId = myService.setPaymentType(scanner.nextLine());
        System.out.println("Enter quantity:");
        int quantity = myService.setOrderQuantity(scanner.nextLine(), productId);
        myService.updateProductQuantity(quantity, productId);

        myService.addCustomerOrder(CustomerOrderDto
                .builder()
                .productDto(ProductDto
                        .builder()
                        .name(productRepository
                                .findOne(productId)
                                .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " PRODUCT ", LocalDate.now()))
                                .getName())
                        .build())
                .quantity(quantity)
                .discount(discount)
                .date(date)
                .paymentDto(PaymentDto
                        .builder()
                        .payment(EPayment.returnPayment(paymentId))
                        .build())
                .customerDto(CustomerDto
                        .builder()
                        .name(customerRepository
                                .findOne(customerId)
                                .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " CUSTOMER ", LocalDate.now()))
                                .getName())
                        .build())
                .build());
        state = State.ORDER;
        return state;
    }

    private static State addProducer() {
        System.out.println("Enter producer name");
        String name = myService.setProducerName(scanner.nextLine());
        System.out.println("Chose trade from list:");
        myService.printAllTrades();
        long tradeId = myService.setId(scanner.nextLine());
        System.out.println("Chose country from list:");
        myService.printAllCountries();
        long countryId = myService.oneProducerNameAndTradeFromOneCountry(name, tradeId, myService.setId(scanner.nextLine()));
        myService.addProducer(ProducerDto
                .builder()
                .name(name)
                .countryDto(CountryDto
                        .builder()
                        .name(countryRepository
                                .findOne(countryId)
                                .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " COUNTRY ", LocalDate.now()))
                                .getName())
                        .build())
                .tradeDto(TradeDto
                        .builder()
                        .name(tradeRepository
                                .findOne(tradeId)
                                .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " TRADE ", LocalDate.now()))
                                .getName())
                        .build())
                .build());
        state = State.PRODUCER;
        return state;
    }

    private static State addProduct() {
        System.out.println("Enter product name");
        String name = myService.setProductName(scanner.nextLine());
        System.out.println("Enter product price");
        BigDecimal price = myService.setProductPrice(scanner.nextLine());
        System.out.println("Chose category from list:");
        myService.printAllCategories();
        long categoryId = myService.setId(scanner.nextLine());
        System.out.println("Chose producer from list:");
        myService.printAllProducers();
        long producerId = myService.oneProductNameAndCategoryFromOneProducer(name, categoryId, myService.setId(scanner.nextLine()));
        myService.addProduct(ProductDto
                .builder()
                .name(name)
                .price(price)
                .categoryDto(CategoryDto
                        .builder()
                        .name(categoryRepository
                                .findOne(categoryId)
                                .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " CATEGORY ", LocalDate.now()))
                                .getName())
                        .build())
                .producerDto(ProducerDto
                        .builder()
                        .name(producerRepository
                                .findOne(producerId)
                                .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " PRODUCER ", LocalDate.now()))
                                .getName())
                        .build())
                .build());
        state = State.PRODUCT;
        return state;
    }

    private static State addShop() {
        System.out.println("Enter shop name");
        String name = myService.setShopName(scanner.nextLine());
        System.out.println("Chose country from list:");
        myService.printAllCountries();
        long countryId = myService.oneShopFromOneCountry(name, myService.setId(scanner.nextLine()));
        myService.addShop(ShopDto
                .builder()
                .name(name)
                .countryDto(CountryDto
                        .builder()
                        .name(countryRepository
                                .findOne(countryId)
                                .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " COUNTRY ", LocalDate.now()))
                                .getName())
                        .build())
                .build());
        state = State.SHOP;
        return state;
    }

    private static State addStock() {
        System.out.println("Enter quantity");
        int quantity = myService.setQuantity(scanner.nextLine());
        System.out.println("Chose shop from list:");
        myService.printAllShops();
        long shopId = myService.setId(scanner.nextLine());
        System.out.println("Chose product from list:");
        myService.printAllProducts();
        long productId = myService.setId(scanner.nextLine());
        myService.oneProductFromOneShopInStock(shopId, productId, quantity);
        state = State.STOCK;
        return state;
    }

    private static State addTrade() {
        System.out.println("Enter trade name:");
        String name = myService.setTradeName(scanner.nextLine());
        myService.addTrade(TradeDto
                .builder()
                .name(name)
                .build());
        state = State.TRADE;
        return state;
    }
}