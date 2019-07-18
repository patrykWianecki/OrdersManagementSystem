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

import static com.app.model.EGuarantee.*;

public class MockDataForServiceTests {

    public static List<Category> createCategories() {
        return List.of(Category.builder()
                .id(1L)
                .name("CATEGORY_NAME_1")
                .build(),
            Category.builder()
                .id(2L)
                .name("CATEGORY_NAME_2")
                .build());
    }

    public static List<Country> createCountries() {
        return List.of(Country.builder()
                .id(1L)
                .name("COUNTRY_NAME_1")
                .build(),
            Country.builder()
                .id(2L)
                .name("COUNTRY_NAME_2")
                .build());
    }

    public static List<Customer> createCustomers() {
        return List.of(Customer.builder()
                .id(1L)
                .name("CUSTOMER_NAME_1")
                .surname("CUSTOMER_SURNAME_1")
                .age(20)
                .country(createCountries().get(0))
                .build(),
            Customer.builder()
                .id(2L)
                .name("CUSTOMER_NAME_2")
                .surname("CUSTOMER_SURNAME_2")
                .age(30)
                .country(createCountries().get(1))
                .build());
    }

    public static List<GuaranteeComponent> createGuaranteeComponents() {
        return List.of(GuaranteeComponent.builder()
                .eGuarantees(Set.of(EXCHANGE, MONEY_BACK, SERVICE))
                .build(),
            GuaranteeComponent.builder()
                .eGuarantees(Set.of(EXCHANGE, HELP_DESK, SERVICE))
                .build());
    }

    public static List<Order> createOrders() {
        return List.of(Order.builder()
                .id(1L)
                .quantity(100)
                .discount(BigDecimal.valueOf(10))
                .date(LocalDate.now().plusDays(1))
                .customer(createCustomers().get(0))
                .payment(createPayments().get(0))
                .product(createProducts().get(0))
                .build(),
            Order.builder()
                .id(2L)
                .quantity(200)
                .discount(BigDecimal.valueOf(20))
                .date(LocalDate.now().plusDays(2))
                .customer(createCustomers().get(0))
                .payment(createPayments().get(1))
                .product(createProducts().get(1))
                .build());
    }

    public static List<Payment> createPayments() {
        return List.of(Payment.builder()
                .id(1L)
                .build(),
            Payment.builder()
                .id(2L)
                .build());
    }

    public static List<Producer> createProducers() {
        return List.of(Producer.builder()
                .id(1L)
                .name("PRODUCER_NAME_1")
                .country(createCountries().get(0))
                .trade(createTrades().get(0))
                .build(),
            Producer.builder()
                .id(2L)
                .name("PRODUCER_NAME_2")
                .country(createCountries().get(1))
                .trade(createTrades().get(1))
                .build());
    }

    public static List<Product> createProducts() {
        return List.of(Product.builder()
                .id(1L)
                .name("PRODUCT_NAME_1")
                .price(BigDecimal.valueOf(100))
                .category(createCategories().get(0))
                .producer(createProducers().get(0))
                .guaranteeComponents(Set.of(createGuaranteeComponents().get(0)))
                .build(),
            Product.builder()
                .id(2L)
                .name("PRODUCT_NAME_2")
                .price(BigDecimal.valueOf(200))
                .category(createCategories().get(1))
                .producer(createProducers().get(1))
                .guaranteeComponents(Set.of(createGuaranteeComponents().get(1)))
                .build());
    }

    public static List<Shop> createShops() {
        return List.of(Shop.builder()
                .id(1L)
                .name("SHOP_NAME_1")
                .country(createCountries().get(0))
                .build(),
            Shop.builder()
                .id(2L)
                .name("SHOP_NAME_2")
                .country(createCountries().get(1))
                .build());
    }

    public static List<Stock> createStocks() {
        return List.of(Stock.builder()
                .id(1L)
                .quantity(150)
                .product(createProducts().get(0))
                .shop(createShops().get(0))
                .build(),
            Stock.builder()
                .id(2L)
                .quantity(250)
                .product(createProducts().get(1))
                .shop(createShops().get(1))
                .build());
    }

    public static List<Trade> createTrades() {
        return List.of(Trade.builder()
                .id(1L)
                .name("TRADE_NAME_1")
                .producers(Set.of(Producer.builder().id(1L).build()))
                .build(),
            Trade.builder()
                .id(2L)
                .name("TRADE_NAME_2")
                .producers(new HashSet<>())
                .build());
    }
}
