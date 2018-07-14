package com.app.service;

import com.app.dto.*;
import com.app.model.*;
import com.app.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MyServiceImpl implements MyService {

    private MyMapper myMapper = new MyMapper();
    private static Scanner scanner = new Scanner(System.in);
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private CountryRepository countryRepository = new CountryRepositoryImpl();
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private CustomerOrderRepository customerOrderRepository = new CustomerOrderRepositoryImpl();
    private PaymentRepository paymentRepository = new PaymentRepositoryImpl();
    private ProducerRepository producerRepository = new ProducerRepositoryImpl();
    private ProductRepository productRepository = new ProductRepositoryImpl();
    private ShopRepository shopRepository = new ShopRepositoryImpl();
    private StockRepository stockRepository = new StockRepositoryImpl();
    private TradeRepository tradeRepository = new TradeRepositoryImpl();
    private Errors errors = new Errors();
    private final static LocalDate MAX_DATE = LocalDate.of(2020, 12, 31);
    private final static LocalDate ACTUAL_DATE = LocalDate.now();

    @Override
    public void addCustomer(CustomerDto customerDto) throws Errors {
        try {
            Country country
                    = countryRepository
                    .findByName(customerDto.getCountryDto().getName())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " Country " + customerDto.getCountryDto().getName(), LocalDate.now()));

            Customer customer = myMapper.fromCustomerDtoToCustomer(customerDto);
            customer.setCountry(country);
            customerRepository.addOrUpdate(customer);
        } catch (Exception e) {
            errors.addSuppressed(e);
        }
    }

    @Override
    public void addShop(ShopDto shopDto) throws Errors {
        try {
            Country country
                    = countryRepository
                    .findByName(shopDto.getCountryDto().getName())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " Country " + shopDto.getCountryDto().getName(), LocalDate.now()));

            Shop shop = myMapper.fromShopDtoToShop(shopDto);
            shop.setCountry(country);
            shopRepository.addOrUpdate(shop);
        } catch (Exception e) {
            errors.addSuppressed(e);
        }
    }

    @Override
    public void addProducer(ProducerDto producerDto) throws Errors {
        try {
            Country country
                    = countryRepository
                    .findByName(producerDto.getCountryDto().getName())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " Country " + producerDto.getCountryDto().getName(), LocalDate.now()));
            Trade trade
                    = tradeRepository
                    .findByName(producerDto.getTradeDto().getName())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " Trade " + producerDto.getTradeDto().getName(), LocalDate.now()));

            Producer producer = myMapper.fromProducerDtoToProducer(producerDto);
            producer.setCountry(country);
            producer.setTrade(trade);
            producerRepository.addOrUpdate(producer);
        } catch (Exception e) {
            errors.addSuppressed(e);
        }
    }

    @Override
    public void addProduct(ProductDto productDto) throws Errors {
        try {
            Category category
                    = categoryRepository
                    .findByName(productDto.getCategoryDto().getName())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " Category " + productDto.getCategoryDto().getName(), LocalDate.now()));
            Producer producer
                    = producerRepository
                    .findByName(productDto.getProducerDto().getName())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " Producer " + productDto.getProducerDto().getName(), LocalDate.now()));

            Product product = myMapper.fromProductDtoToProduct(productDto);
            product.setCategory(category);
            product.setProducer(producer);
            productRepository.addOrUpdate(product);
        } catch (Exception e) {
            errors.addSuppressed(e);
        }
    }

    @Override
    public void addStock(StockDto stockDto) throws Errors {
        try {
            Product product
                    = productRepository
                    .findByName(stockDto.getProductDto().getName())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " PRODUCT " + stockDto.getProductDto().getName(), LocalDate.now()));
            Shop shop
                    = shopRepository
                    .findByName(stockDto.getShopDto().getName())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " SHOP " + stockDto.getShopDto().getName(), LocalDate.now()));

            Stock stock = myMapper.fromStockDtoToStock(stockDto);
            stock.setProduct(product);
            stock.setShop(shop);
            stockRepository.addOrUpdate(stock);
        } catch (Exception e) {
            errors.addSuppressed(e);
        }
    }

    @Override
    public void updateStock(Integer quantity, Long id) {
        try {
            Stock stock
                    = stockRepository
                    .findOne(id)
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " STOCK ", LocalDate.now()));
            stock.setQuantity(quantity);
        } catch (Exception e) {
            errors.addSuppressed(e);
        }
    }

    @Override
    public void addCustomerOrder(CustomerOrderDto customerOrderDto) throws Errors {
        try {
            Customer customer
                    = customerRepository
                    .findByNameAndSurname(customerOrderDto.getCustomerDto().getName(), customerOrderDto.getCustomerDto().getSurname())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " Customer "
                            + customerOrderDto.getCustomerDto().getName() + " "
                            + customerOrderDto.getCustomerDto().getSurname(), LocalDate.now()));
            Payment payment
                    = paymentRepository
                    .findOne(customerOrderDto.getPaymentDto().getId())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " Payment " + customerOrderDto.getPaymentDto().getPayment(), LocalDate.now()));
            Product product
                    = productRepository
                    .findByName(customerOrderDto.getProductDto().getName())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " Product " + customerOrderDto.getProductDto().getName(), LocalDate.now()));

            CustomerOrder customerOrder = myMapper.fromCustomerOrderDtoToCustomerOrder(customerOrderDto);
            customerOrder.setCustomer(customer);
            customerOrder.setProduct(product);
            customerOrder.setPayment(payment);
            customerOrderRepository.addOrUpdate(customerOrder);
        } catch (Exception e) {
            errors.addSuppressed(e);
        }
    }

    @Override
    public Map<Category, Product> biggestProductPriceInEachCategory() throws Errors {
        return productRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(p -> categoryRepository
                        .findOne(p.getCategory().getId())
                        .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " categoryId " + p.getCategory().getId(), LocalDate.now()))))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        p -> p.getValue()
                                .stream()
                                .max(Comparator.comparing(Product::getPrice))
                                .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " Product with max price ", LocalDate.now())))
                );
    }

    @Override
    public Map<Customer, List<CustomerOrder>> productsOrderedByCustomersFromGivenCountry(Long id) throws Errors {
        return customerOrderRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(p -> customerRepository
                        .findOne(p.getCustomer().getId())
                        .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " customerId " + p.getCustomer().getId(), LocalDate.now()))
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        v -> v.getValue()
                                .stream()
                                .filter(x -> x.getCustomer().getCountry().getId().equals(id))
                                .collect(Collectors.toList())
                ));
    }

    @Override
    public List<Product> sortedProductsWithGuaranteeByCategory() throws Errors {
        return null;
    }

    @Override
    public List<Shop> shopsWithProductsInStockWithDifferentCountryThanShop() throws Errors {
        return null;
    }

    @Override
    public List<Producer> producersWithGivenTradeNameWhichProducesMoreThanGivenNumber() throws Errors {
        return null;
    }

    @Override
    public List<CustomerOrder> ordersWithGivenDateAndPriceAmount() throws Errors {
        return null;
    }

    @Override
    public List<Product> productsSortedByProducersWithGivenNameSurnameAndCountry(String name, String surname, Long id) {
        return customerRepository
                .findAll()
                .stream()
                .map(p -> productRepository.findByName(name).orElseThrow(() -> new Errors("DUPA, ", LocalDate.now())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> customersWithMoreThanOneProductWithSameCountry() throws Errors {
        return null;
    }

    @Override
    public void printAllCategories() {
        categoryRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Category::getId))
                .forEach(s -> System.out.println(s.getId() + ". " + s.getName()));
    }

    @Override
    public void printAllCountries() {
        countryRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Country::getId))
                .forEach(n -> System.out.println(n.getId() + ". " + n.getName()));
    }

    @Override
    public void printAllPayments() {
        paymentRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Payment::getId))
                .forEach(x -> System.out.println(x.getId() + ". " + x.getPayment()));
    }

    @Override
    public void printAllProducers() {
        producerRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Producer::getId))
                .forEach(s -> System.out.println(s.getId() + ". " + s.getName()));
    }

    @Override
    public void printAllProducts() {
        productRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Product::getId))
                .forEach(x -> System.out.println(x.getId() + ". " + x.getName()));
    }

    @Override
    public void printAllShops() {
        shopRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Shop::getId))
                .forEach(s -> System.out.println(s.getId() + ". " + s.getName()));
    }

    @Override
    public void printAllTrades() {
        tradeRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Trade::getId))
                .forEach(s -> System.out.println(s.getId() + ". " + s.getName()));
    }

    @Override
    public String setCountryName(String name) {
        while (!name.matches("([A-Z]+)|")) {
            System.out.println("Wrong country name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public Integer setOrderQuantity(String quantity, Long productId) {
        Integer stockQuantity
                = stockRepository
                .findOne(productId)
                .orElseThrow(() -> new Errors("ORDERS SERVICE, PRODUCT NOT FOUND ", LocalDate.now()))
                .getQuantity();

        while (!quantity.matches("[0-9]+")
                || Integer.valueOf(quantity) < 1
                || Integer.valueOf(quantity) > 100
                || Integer.valueOf(quantity) > stockQuantity) {
            if (Integer.valueOf(quantity) > stockQuantity) System.out.println("Not enough products in stock!");
            else System.out.println("Wrong quantity!");
            System.out.println("Enter again:");
            quantity = scanner.nextLine();
        }

        stockRepository.addOrUpdate(Stock
                .builder()
                .quantity(stockQuantity - Integer.valueOf(quantity))
                .build());
        return Integer.valueOf(quantity);
    }

    @Override
    public BigDecimal setOrderDiscount(String discount) {
        while (!discount.matches("([0-9])|([1-9]+[0-9])|([1]+[0]+[0])")
                || (BigDecimal.valueOf(Integer.valueOf(discount)).compareTo(BigDecimal.valueOf(100)) > 0)
                ) {
            System.out.println("Wrong order discount!" + "\n" + "Enter again:");
            discount = scanner.nextLine();
        }
        return BigDecimal.valueOf(Integer.valueOf(discount));
    }

    @Override
    public LocalDate setOrderDate() {
        String dash = "-";
        String year = String.valueOf(setCorrectYear(ACTUAL_DATE, MAX_DATE));
        String month = String.valueOf(setCorrectMonth(ACTUAL_DATE, year));
        String day = String.valueOf(setCorrectDay(ACTUAL_DATE, month));
        return LocalDate.parse(String.valueOf(new StringBuilder()
                .append(year).append(dash).append(month).append(dash).append(day)));
    }

    private static String setCorrectDay(LocalDate actualDate, String month) {
        System.out.println("Enter day:");
        String day = scanner.nextLine();
        if (Integer.valueOf(month).equals(actualDate.getMonthValue())) {
            while (!day.matches("[0-9]{2}")
                    || Integer.valueOf(day) < 1
                    || Integer.valueOf(day) > 31
                    || Integer.valueOf(day) < actualDate.getDayOfMonth()) {
                System.out.println("Wrong day!" + "\n" + "Enter again:");
                day = scanner.nextLine();
            }
        } else {
            while (!day.matches("[0-9]{2}")
                    || Integer.valueOf(day) < 1
                    || Integer.valueOf(day) > 31) {
                System.out.println("Wrong day!" + "\n" + "Enter again:");
                day = scanner.nextLine();
            }
        }
        return day;
    }

    private static String setCorrectMonth(LocalDate actualDate, String year) {
        System.out.println("Enter month:");
        String month = scanner.nextLine();
        if (Integer.valueOf(year).equals(actualDate.getYear())) {
            while (!month.matches("[0-9]{2}")
                    || Integer.valueOf(month) < 1
                    || Integer.valueOf(month) > 12
                    || Integer.valueOf(month) < actualDate.getMonthValue()) {
                System.out.println("Wrong month!" + "\n" + "Enter again:");
                month = scanner.nextLine();
            }
        } else {
            while (!month.matches("[0-9]{2}")
                    || Integer.valueOf(month) < 1
                    || Integer.valueOf(month) > 12) {
                System.out.println("Wrong month!" + "\n" + "Enter again:");
                month = scanner.nextLine();
            }
        }
        return month;
    }

    private static String setCorrectYear(LocalDate actualDate, LocalDate maxDate) {
        System.out.println("Enter year:");
        String year = scanner.nextLine();
        while (!year.matches("[0-9]{4}")
                || Integer.valueOf(year) < actualDate.getYear()
                || Integer.valueOf(year) > maxDate.getYear()) {
            System.out.println("Wrong year!" + "\n" + "Enter again:");
            year = scanner.nextLine();
        }
        return year;
    }

    @Override
    public String setCustomerName(String name) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Wrong customer name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public String setCustomerSurname(String surname) {
        while (!surname.matches("([A-Z]+)")) {
            System.out.println("Wrong customer surname!" + "\n" + "Enter again:");
            surname = scanner.nextLine();
        }
        return surname;
    }

    @Override
    public Integer setCustomerAge(String age) {
        while (!age.matches("([1-9]+[0-9])" + "|" + "([1]+[0-2]+[0-9])")
                || Integer.valueOf(age) < 18
                || Integer.valueOf(age) > 120) {
            System.out.println("Wrong age format or age is to small!" + "\n" + "Enter again:");
            age = scanner.nextLine();
        }
        return Integer.valueOf(age);
    }

    @Override
    public Long OneCustomerFromOneCountry(String customerName, String customerSurname, Long countryId) throws Errors {
        List<Customer> customers
                = customerRepository
                .findAll()
                .stream()
                .filter(customer -> customer.getName().equals(customerName)
                        && customer.getSurname().equals(customerSurname)
                        && customer.getCountry().getId().equals(countryId))
                .collect(Collectors.toList());
        if (customers.size() != 0) {
            throw new Errors("CUSTOMER FROM CHOSEN COUNTRY ALREADY EXISTS ", LocalDate.now());
        }
        return countryId;
    }

    @Override
    public String setProducerName(String name) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Wrong product name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public Long OneProducerNameAndTradeFromOneCountry(String producerName, Long tradeId, Long countryId) throws Errors {
        List<Producer> producers
                = producerRepository
                .findAll()
                .stream()
                .filter(producer -> producer.getName().equals(producerName)
                        && producer.getTrade().getId().equals(tradeId)
                        && producer.getCountry().getId().equals(countryId))
                .collect(Collectors.toList());
        if (producers.size() != 0) {
            throw new Errors("PRODUCER WITH GIVEN TRADE AND COUNTRY ALREADY EXISTS ", LocalDate.now());
        }
        return countryId;
    }

    @Override
    public String setProductName(String name) {
        while (!name.matches("(([A-Z])+)")) {
            System.out.println("Wrong product name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public BigDecimal setProductPrice(String price) {
        while (BigDecimal.valueOf(Long.parseLong(price)).compareTo(BigDecimal.ZERO) < 1) {
            System.out.println("Wrong product price!" + "\n" + "Enter again:");
            price = scanner.nextLine();
            scanner.nextLine();
        }
        return BigDecimal.valueOf(Long.parseLong(price));
    }

    @Override
    public Long OneProductNameAndCategoryFromOneProducer(String productName, Long categoryId, Long producerId) throws Errors {
        List<Product> products
                = productRepository
                .findAll()
                .stream()
                .filter(product -> product.getName().equals(productName)
                        && product.getCategory().getId().equals(categoryId)
                        && product.getProducer().getId().equals(producerId))
                .collect(Collectors.toList());
        if (products.size() != 0) {
            throw new Errors("PRODUCT WITH GIVEN CATEGORY AND PRODUCER ALREADY EXISTS ", LocalDate.now());
        }
        return producerId;
    }

    @Override
    public String setShopName(String name) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Wrong shop name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public Long OneShopFromOneCountry(String shopName, Long countryId) throws Errors {
        List<Shop> shops
                = shopRepository
                .findAll()
                .stream()
                .filter(shop -> shop.getName().equals(shopName) && shop.getCountry().getId().equals(countryId))
                .collect(Collectors.toList());
        if (shops.size() != 0) {
            throw new Errors("SHOP FROM CHOSEN COUNTRY ALREADY EXISTS ", LocalDate.now());
        }
        return countryId;
    }

    @Override
    public int setQuantity(String quantity) {
        while (Integer.valueOf(quantity) < 1) {
            System.out.println("Wrong quantity!" + "\n" + "Enter again:");
            quantity = scanner.nextLine();
        }
        return Integer.parseInt(quantity);
    }

    @Override
    public void OneProductFromOneShop(Long shopId, Long productId, Integer stockQuantity) throws Errors {
        List<Stock> stocks
                = stockRepository
                .findAll()
                .stream()
                .filter(stock -> stock.getShop().getId().equals(shopId)
                        && stock.getProduct().getId().equals(productId))
                .collect(Collectors.toList());

        if (stocks.size() != 0) {
            int updatedQuantity = stocks.get(0).getQuantity() + stockQuantity;
            long upId = stocks.get(0).getId();
            stockRepository.delete(upId);
            updateStock(updatedQuantity, upId);
        } else {
            addStock(
                    StockDto
                            .builder()
                            .quantity(stockQuantity)
                            .shopDto(ShopDto
                                    .builder()
                                    .name(shopRepository
                                            .findOne(shopId)
                                            .orElseThrow(() -> new Errors("MENU ADD_STOCK, SHOP NOT FOUND", LocalDate.now()))
                                            .getName())
                                    .build())
                            .productDto(ProductDto
                                    .builder()
                                    .name(productRepository
                                            .findOne(productId)
                                            .orElseThrow(() -> new Errors("MENU - ADD_STOCK, PRODUCT NOT FOUND", LocalDate.now()))
                                            .getName())
                                    .build())
                            .build()
            );
        }
    }

    @Override
    public String setTradeName(String name) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Wrong trade name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }
}
