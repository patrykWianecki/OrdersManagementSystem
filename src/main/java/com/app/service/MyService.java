package com.app.service;

import com.app.model.*;
import com.app.dto.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MyService {
    void addCategory(CategoryDto categoryDto);

    void addCountry(CountryDto countryDto);

    void addCustomer(CustomerDto customerDto);

    void addCustomerOrder(CustomerOrderDto customerOrderDto);

    void addProducer(ProducerDto producerDto);

    void addProduct(ProductDto productDto);

    void addShop(ShopDto shopDto);

    void addStock(StockDto stockDto);

    void addTrade(TradeDto tradeDto);

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

    void printAllCustomers();

    void printAllPayments();

    void printAllProducers();

    void printAllProducts();

    void printAllShops();

    void printAllTrades();

    String setCategoryName(String name);

    String setCountryName(String name);

    BigDecimal setOrderDiscount(String discount);

    LocalDate setOrderDate();

    Integer setOrderQuantity(String quantity, Long productId);

    String setCustomerName(String name);

    String setCustomerSurname(String surname);

    Integer setCustomerAge(String age);

    Integer setPaymentType(String number);

    String setProducerName(String name);

    String setProductName(String name);

    BigDecimal setProductPrice(String price);

    String setShopName(String name);

    int setQuantity(String quantity);

    String setTradeName(String name);

    Long setId(String id);

    Long oneCustomerFromOneCountry(String customerName, String customerSurname, Long customerId);

    Long oneProducerNameAndTradeFromOneCountry(String producerName, Long tradeId, Long countryId);

    Long oneProductNameAndCategoryFromOneProducer(String productName, Long categoryId, Long producerId);

    Long oneShopFromOneCountry(String shopName, Long countryId);

    void oneProductFromOneShopInStock(Long shopId, Long productId, Integer stockQuantity);

    void updateProductQuantity(Integer quantity, Long productId);

    void initializeData();
}
