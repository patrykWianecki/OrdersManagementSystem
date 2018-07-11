package com.app.service;

import com.app.dto.*;
import com.app.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MyService {
    void addCustomer(CustomerDto customerDto);

    void addShop(ShopDto shopDto);

    void addProducer(ProducerDto producerDto);

    void addProduct(ProductDto productDto);

    void addStock(StockDto stockDto);

    void addCustomerOrder(CustomerOrderDto customerOrderDto);

    Map<Category, Product> biggestProductPriceInEachCategory();

    Map<Customer, List<CustomerOrder>> productsOrderedByCustomersFromGivenCountry(Long id);

    List<Product> sortedProductsWithGuaranteeByCategory();

    List<Shop> shopsWithProductsInStockWithDifferentCountryThanShop();

    List<Producer> producersWithGivenTradeNameWhichProducesMoreThanGivenNumber();

    List<CustomerOrder> ordersWithGivenDateAndPriceAmount();

    List<Product> productsSortedByProducersWithGivenNameSurnameAndCountry(String name, String surname, Long id);

    List<Customer> customersWithMoreThanOneProductWithSameCountry();
}
