package com.app.service;

import com.app.dto.*;
import com.app.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MyService {
    void addCustomer(CustomerDto customerDto);

    void addShop(ShopDto shopDto);

    void addProducer(ProducerDto producerDto);

    void addProduct(ProductDto productDto);

    void addStock(StockDto stockDto);

    void updateStock(Integer quantity, Long id);

    void addCustomerOrder(CustomerOrderDto customerOrderDto);

    Map<Category, Product> biggestProductPriceInEachCategory();

    Map<Customer, List<CustomerOrder>> productsOrderedByCustomersFromGivenCountry(Long id);

    List<Product> sortedProductsWithGuaranteeByCategory();

    List<Shop> shopsWithProductsInStockWithDifferentCountryThanShop();

    List<Producer> producersWithGivenTradeNameWhichProducesMoreThanGivenNumber();

    List<CustomerOrder> ordersWithGivenDateAndPriceAmount();

    List<Product> productsSortedByProducersWithGivenNameSurnameAndCountry(String name, String surname, Long id);

    List<Customer> customersWithMoreThanOneProductWithSameCountry();

    void printAllCategories();

    void printAllCountries();

    void printAllPayments();

    void printAllProducers();

    void printAllProducts();

    void printAllShops();

    void printAllTrades();

    String setCountryName(String name);

    BigDecimal setOrderDiscount(String discount);

    LocalDate setOrderDate();

    Integer setOrderQuantity(String quantity, Long productId);

    String setCustomerName(String name);

    String setCustomerSurname(String surname);

    Integer setCustomerAge(String age);

    Long OneCustomerFromOneCountry(String customerName, String customerSurname, Long customerId);

    String setProducerName(String name);

    Long OneProducerNameAndTradeFromOneCountry(String producerName, Long tradeId, Long countryId);

    String setProductName(String name);

    BigDecimal setProductPrice(String price);

    Long OneProductNameAndCategoryFromOneProducer(String productName, Long categoryId, Long producerId);

    String setShopName(String name);

    Long OneShopFromOneCountry(String shopName, Long countryId);

    int setQuantity(String quantity);

    void OneProductFromOneShop(Long shopId, Long productId, Integer stockQuantity);

    String setTradeName(String name);
}
