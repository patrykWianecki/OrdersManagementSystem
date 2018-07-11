package com.app;

import com.app.dto.*;
import com.app.model.EPayment;
import com.app.model.Errors;
import com.app.repository.*;
import com.app.service.MyService;
import com.app.service.MyServiceImpl;
import com.app.service.name.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class App {

    private static MyService myService = new MyServiceImpl();
    private static CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private static CountryRepository countryRepository = new CountryRepositoryImpl();
    private static PaymentRepository paymentRepository = new PaymentRepositoryImpl();
    private static ProducerRepository producerRepository = new ProducerRepositoryImpl();
    private static ProductRepository productRepository = new ProductRepositoryImpl();
    private static ShopRepository shopRepository = new ShopRepositoryImpl();
    private static TradeRepository tradeRepository = new TradeRepositoryImpl();
    private static Scanner scanner = new Scanner(System.in);
    private static CategoryService categoryService = new CategoryServiceImpl();
    private static CountryService countryService = new CountryServiceImpl();
    private static CustomerOrdersService ordersService = new CustomerOrdersServiceImpl();
    private static CustomerService customerService = new CustomerServiceImpl();
    private static PaymentService paymentService = new PaymentServiceImpl();
    private static ProducerService producerService = new ProducerServiceImpl();
    private static ProductService productService = new ProductServiceImpl();
    private static ShopService shopService = new ShopServiceImpl();
    private static StockService stockService = new StockServiceImpl();
    private static TradeService tradeService = new TradeServiceImpl();
    private static State state = State.INIT;

    public static void main(String[] args) {
//        MyService myService = new MyServiceImpl();
//        System.out.println(myService.biggestProductPriceInEachCategory());
//        System.out.println(myService.productsOrderedByCustomersFromGivenCountry((long) 2));
        try {
            menu();
        } catch (Errors e) {
            e.printStackTrace();
        }

    }

    private enum State {
        INIT, EXIT, CATEGORY, COUNTRY, CUSTOMER, PRODUCER, PRODUCT, SHOP, STOCK, TRADE, ORDER, OTHER
    }

    private static void menu() {
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
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0:
                state = State.INIT;
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
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0:
                state = State.INIT;
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
                state = State.CUSTOMER;
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
                state = State.ORDER;
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
                state = State.PRODUCER;
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
                state = State.PRODUCT;
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
                state = State.SHOP;
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
                state = State.STOCK;
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
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0:
                state = State.INIT;
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
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0:
                state = State.INIT;
                break;
            case 1:
                printInfo();
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
                categoryService.printAllCategories();
                state = State.OTHER;
                break;
            }
            case 2: {
                countryService.printAllCountries();
                state = State.OTHER;
                break;
            }
            case 3: {
                producerService.printAllProducers();
                state = State.OTHER;
                break;
            }
            case 4: {
                productService.printAllProducts();
                state = State.OTHER;
                break;
            }
            case 5: {
                shopService.printAllShops();
                state = State.OTHER;
                break;
            }
            case 6: {
                tradeService.printAllTrades();
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

    private static State addCustomer() throws Errors {
        System.out.println("Enter customer name");
        String name = customerService.setCustomerName(scanner.nextLine());

        System.out.println("Enter customer surname");
        String surname = customerService.setCustomerSurname(scanner.nextLine());

        System.out.println("Enter customer age");
        int age = customerService.setCustomerAge(scanner.nextLine());

        System.out.println("Chose country id from list:");
        countryService.printAllCountries();
        long countryId = scanner.nextLong();
        scanner.nextLine();

        String countryName
                = countryRepository
                .findOne(countryId)
                .orElseThrow(() -> new Errors("MENU - ADD_CUSTOMER, COUNTRY NOT FOUND ", LocalDate.now()))
                .getName();

        myService.addCustomer(CustomerDto
                .builder()
                .name(name)
                .surname(surname)
                .age(age)
                .countryDto(CountryDto
                        .builder()
                        .name(countryName)
                        .build())
                .build());

        return state;
    }

    private static State addOrder() throws Errors {
        System.out.println("Chose product id from list:");
        productService.printAllProducts();
        long productId = scanner.nextLong();
        scanner.nextLine();
        String productName
                = productRepository
                .findOne(productId)
                .orElseThrow(() -> new Errors("MENU - ADD_ORDER, PRODUCT NOT FOUND", LocalDate.now()))
                .getName();

        System.out.println("Enter discount:");
        BigDecimal discount = ordersService.setOrderDiscount(scanner.nextLine());

        System.out.println("Enter quantity:");
        int quantity = ordersService.setOrderQuantity(scanner.nextLine(), productId);

        System.out.println("Chose payment id from list:");
        paymentService.printAllPayments();
        int paymentId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter customer name");
        String name = customerService.setCustomerName(scanner.nextLine());

        System.out.println("Enter customer surname");
        String surname = customerService.setCustomerSurname(scanner.nextLine());

        System.out.println("Enter customer age");
        int age = customerService.setCustomerAge(scanner.nextLine());

        System.out.println("Chose country id from list:");
        countryService.printAllCountries();
        long countryId = scanner.nextLong();
        scanner.nextLine();

        String countryName
                = countryRepository
                .findOne(countryId)
                .orElseThrow(() -> new Errors("MENU - ADD_CUSTOMER, COUNTRY NOT FOUND ", LocalDate.now()))
                .getName();

        myService.addCustomerOrder(
                CustomerOrderDto
                        .builder()
                        .productDto(ProductDto
                                .builder()
                                .name(productName)
                                .build())
                        .quantity(quantity)
                        .discount(discount)
                        .date(LocalDate.now())
                        .paymentDto(PaymentDto
                                .builder()
                                .payment(EPayment.returnPayment(paymentId))
                                .build())
                        .customerDto(CustomerDto
                                .builder()
                                .name(name)
                                .surname(surname)
                                .age(age)
                                .countryDto(CountryDto
                                        .builder()
                                        .name(countryName)
                                        .build())
                                .build())
                        .build()
        );

        state = State.ORDER;
        return state;
    }

    private static State addProducer() throws Errors {
        System.out.println("Enter producer name");
        String name = producerService.setProducerName(scanner.nextLine());

        System.out.println("Chose country id from list");
        countryService.printAllCountries();
        long countryId = scanner.nextLong();
        scanner.nextLine();

        String countryName
                = countryRepository
                .findOne(countryId)
                .orElseThrow(() -> new Errors("MENU - PRODUCER, COUNTRY NOT FOUND ", LocalDate.now()))
                .getName();

        System.out.println("Chose trade id from list");
        tradeService.printAllTrades();
        long tradeId = scanner.nextLong();
        scanner.nextLine();

        String tradeName
                = tradeRepository
                .findOne(tradeId)
                .orElseThrow(() -> new Errors("MENU - ADD_PRODUCER, TRADE NOT FOUND ", LocalDate.now()))
                .getName();

        myService.addProducer(ProducerDto
                .builder()
                .name(name)
                .countryDto(CountryDto
                        .builder()
                        .name(countryName)
                        .build())
                .tradeDto(TradeDto
                        .builder()
                        .name(tradeName)
                        .build())
                .build()
        );

        state = State.PRODUCER;
        return state;
    }

    private static State addProduct() throws Errors {
        System.out.println("Enter product name");
        String name = productService.setProductName(scanner.nextLine());

        System.out.println("Enter product price");
        BigDecimal price = productService.setProductPrice(scanner.nextLine());

        System.out.println("Chose category id from list");
        categoryService.printAllCategories();
        long categoryId = scanner.nextLong();
        scanner.nextLine();
        String categoryName
                = categoryRepository
                .findOne(categoryId)
                .orElseThrow(() -> new Errors("MENU - ADD_PRODUCT, CATEGORY NOT FOUND ", LocalDate.now()))
                .getName();

        System.out.println("Chose producer id from list");
        producerService.printAllProducers();
        long producerId = scanner.nextLong();
        scanner.nextLine();
        String producerName
                = producerRepository
                .findOne(producerId)
                .orElseThrow(() -> new Errors("MENU - ADD.PRODUCT, PRODUCER NOT FOUND ", LocalDate.now()))
                .getName();

        myService.addProduct(
                ProductDto
                        .builder()
                        .name(name)
                        .price(price)
                        .categoryDto(CategoryDto
                                .builder()
                                .name(categoryName)
                                .build())
                        .producerDto(ProducerDto
                                .builder()
                                .name(producerName)
                                .build())
                        .build()
        );

        state = State.PRODUCT;
        return state;
    }

    private static State addShop() throws Errors {
        System.out.println("Enter shop name");
        String name = shopService.setShopName(scanner.nextLine());

        System.out.println("Chose country id from list:");
        countryService.printAllCountries();
        long countryId = scanner.nextLong();
        scanner.nextLine();

        myService.addShop(
                ShopDto
                        .builder()
                        .name(name)
                        .countryDto(CountryDto
                                .builder()
                                .name(countryRepository
                                        .findOne(countryId)
                                        .orElseThrow(() -> new Errors("MENU - ADD_SHOP, COUNTRY NOT FOUND ", LocalDate.now()))
                                        .getName())
                                .build())
                        .build()
        );

        state = State.SHOP;
        return state;
    }

    private static State addStock() throws Errors {
        System.out.println("Chose product id from list");
        productService.printAllProducts();
        long productId = scanner.nextLong();
        scanner.nextLine();
        String productName
                = productRepository
                .findOne(productId)
                .orElseThrow(() -> new Errors("MENU - ADD_STOCK, PRODUCT NOT FOUND", LocalDate.now()))
                .getName();

        System.out.println("Chose shop id from list");
        shopService.printAllShops();
        long shopId = scanner.nextLong();
        scanner.nextLine();
        String shopName
                = shopRepository
                .findOne(shopId)
                .orElseThrow(() -> new Errors("MENU ADD_STOCK, SHOP NOT FOUND", LocalDate.now()))
                .getName();

        System.out.println("Enter quantity");
        int quantity = stockService.setQuantity(scanner.nextLine());

        myService.addStock(
                StockDto
                        .builder()
                        .quantity(quantity)
                        .shopDto(ShopDto
                                .builder()
                                .name(shopName)
                                .build())
                        .productDto(ProductDto
                                .builder()
                                .name(productName)
                                .build())
                        .build()
        );

        state = State.STOCK;
        return state;
    }
}
