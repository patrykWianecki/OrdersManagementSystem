package com.app.service.builders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.app.model.Category;
import com.app.model.Country;
import com.app.model.Customer;
import com.app.model.EGuarantee;
import com.app.model.EPayment;
import com.app.model.GuaranteeComponent;
import com.app.model.Order;
import com.app.model.Payment;
import com.app.model.Producer;
import com.app.model.Product;
import com.app.model.Shop;
import com.app.model.Stock;
import com.app.model.Trade;

public class MockDataForServiceTests {

    public static Category createCategory() {
        return Category.builder()
            .id(1L)
            .name("CATEGORY_NAME_1")
            .products(new HashSet<>(/*createProducts()*/))
            .build();
    }

    public static Country createCountry() {
        return Country.builder()
            .id(1L)
            .name("COUNTRY_NAME_1")
            .customers(new HashSet<>(/*createCustomers()*/))
            .producers(new HashSet<>(/*createProducers()*/))
            .shops(new HashSet<>(/*createShops()*/))
            .build();
    }

    public static Customer createCustomer() {
        return Customer.builder()
            .id(1L)
            .name("CUSTOMER_NAME_1")
            .surname("CUSTOMER_SURNAME_1")
            .age(20)
            .country(createCountry())
            .orders(new HashSet<>(/*createOrders()*/))
            .build();
    }

    public static Order createOrder() {
        return Order.builder()
            .id(1L)
            .quantity(100)
            .discount(BigDecimal.valueOf(10))
            .date(LocalDate.now())
            .customer(createCustomer())
            .payment(createPayment())
            .product(createProduct())
            .build();
    }

    public static GuaranteeComponent createGuaranteeComponent() {
        return GuaranteeComponent.builder()
            .eGuarantees(new HashSet<>(/*createEGuarantees()*/))
            .product(createProduct())
            .build();
    }

    public static List<EGuarantee> createEGuarantees() {
        return List.of(EGuarantee.EXCHANGE, EGuarantee.SERVICE, EGuarantee.HELP_DESK);
    }

    public static Payment createPayment() {
        return Payment.builder()
            .id(1L)
            .ePayments(new HashSet<>(/*createEPayments()*/))
            .orders(new HashSet<>(/*createOrders()*/))
            .build();
    }

    public static List<EPayment> createEPayments() {
        return List.of(EPayment.CASH, EPayment.CARD);
    }

    public static Producer createProducer() {
        return Producer.builder()
            .id(1L)
            .name("PRODUCER_NAME_1")
            .country(createCountry())
            .products(new HashSet<>(/*createProducts()*/))
            .trade(createTrade())
            .build();
    }

    public static Product createProduct() {
        return Product.builder()
            .id(1L)
            .name("PRODUCT_NAME_1")
            .price(BigDecimal.valueOf(200))
            .category(createCategory())
            .guaranteeComponents(new HashSet<>(/*createGuaranteeComponents()*/))
            .orders(new HashSet<>(/*createOrders()*/))
            .producer(createProducer())
            .stocks(new HashSet<>(/*createStocks()*/))
            .build();
    }

    public static Shop createShop() {
        return Shop.builder()
            .id(1L)
            .name("SHOP_NAME_1")
            .country(createCountry())
            .stocks(new HashSet<>(/*createStocks()*/))
            .build();
    }

    public static Stock createStock() {
        return Stock.builder()
            .id(1L)
            .quantity(200)
            .product(createProduct())
            .shop(createShop())
            .build();
    }

    public static Trade createTrade() {
        return Trade.builder()
            .id(1L)
            .name("TRADE_NAME_1")
            .producers(new HashSet<>(/*createProducers()*/))
            .build();
    }

    public static List<Category> createCategories() {
        return List.of(Category.builder()
            .id(1L)
            .name("CATEGORY_NAME_1")
            .products(new HashSet<>(/*createProducts()*/))
            .build());
    }

    public static List<Country> createCountries() {
        return List.of(Country.builder()
            .id(1L)
            .name("COUNTRY_NAME_1")
            .customers(new HashSet<>(/*createCustomers()*/))
            .producers(new HashSet<>(/*createProducers()*/))
            .shops(new HashSet<>(/*createShops()*/))
            .build());
    }

    public static List<Customer> createCustomers() {
        return List.of(Customer.builder()
            .id(1L)
            .name("CUSTOMER_NAME_1")
            .surname("CUSTOMER_SURNAME_1")
            .age(20)
            .country(createCountry())
            .orders(new HashSet<>(/*createOrders()*/))
            .build());
    }

    public static List<GuaranteeComponent> createGuaranteeComponents() {
        return List.of(GuaranteeComponent.builder()
            .eGuarantees(new HashSet<>(/*createEGuarantees()*/))
            .product(createProduct())
            .build());
    }

    public static List<Order> createOrders() {
        return List.of(Order.builder()
            .id(1L)
            .quantity(100)
            .discount(BigDecimal.valueOf(10))
            .date(LocalDate.now())
            .customer(createCustomer())
            .payment(createPayment())
            .product(createProduct())
            .build());
    }

    public static List<Payment> createPayments() {
        return List.of(Payment.builder()
            .id(1L)
            .ePayments(new HashSet<>(/*createEPayments()*/))
            .orders(new HashSet<>(/*createOrders()*/))
            .build());
    }

    public static List<Producer> createProducers() {
        return List.of(Producer.builder()
            .id(1L)
            .name("PRODUCER_NAME_1")
            .country(createCountry())
            .products(new HashSet<>(/*createProducts()*/))
            .trade(createTrade())
            .build());
    }

    public static List<Product> createProducts() {
        return List.of(Product.builder()
            .id(1L)
            .name("PRODUCT_NAME_1")
            .price(BigDecimal.valueOf(100))
            .category(createCategory())
            .producer(createProducer())
            .orders(new HashSet<>(/*createOrders()*/))
            .guaranteeComponents(new HashSet<>(/*createGuaranteeComponents()*/))
            .stocks(new HashSet<>(/*createStocks()*/))
            .build());
    }

    public static List<Shop> createShops() {
        return List.of(Shop.builder()
            .id(1L)
            .name("SHOP_NAME_1")
            .country(createCountry())
            .stocks(new HashSet<>(/*createStocks()*/))
            .build());
    }

    public static List<Stock> createStocks() {
        return List.of(Stock.builder()
            .id(1L)
            .quantity(150)
            .product(createProduct())
            .shop(createShop())
            .build());
    }

    public static List<Trade> createTrades() {
        return List.of(Trade.builder()
            .id(1L)
            .name("TRADE_NAME_1")
            .producers(new HashSet<>(createProducers()))
            .build());
    }
}
