package com.app.model.dto;

import com.app.model.*;

import java.util.HashSet;

public interface MyMapper {

    static CategoryDto fromCategoryToCategoryDto(Category category) {
        return category == null ? null : CategoryDto
            .builder()
            .name(category.getName())
            .build();
    }

    static Category fromCategoryDtoToCategory(CategoryDto categoryDto) {
        return categoryDto == null ? null : Category
            .builder()
            .name(categoryDto.getName())
            .products(new HashSet<>())
            .build();
    }

    static CountryDto fromCountryToCountryDto(Country country) {
        return country == null ? null : CountryDto
            .builder()
            .name(country.getName())
            .build();
    }

    static Country fromCountryDtoToCountry(CountryDto countryDto) {
        return countryDto == null ? null : Country
            .builder()
            .name(countryDto.getName())
            .customers(new HashSet<>())
            .producers(new HashSet<>())
            .shops(new HashSet<>())
            .build();
    }

    static CustomerDto fromCustomerToCustomerDto(Customer customer) {
        return customer == null ? null : CustomerDto
            .builder()
            .name(customer.getName())
            .surname(customer.getSurname())
            .age(customer.getAge())
            .countryDto(customer.getCountry() == null ? null : fromCountryToCountryDto(customer.getCountry()))
            .build();
    }

    static Customer fromCustomerDtoToCustomer(CustomerDto customerDto) {
        return customerDto == null ? null : Customer
            .builder()
            .name(customerDto.getName())
            .surname(customerDto.getSurname())
            .age(customerDto.getAge())
            .country(customerDto.getCountryDto() == null ? null : fromCountryDtoToCountry(customerDto.getCountryDto()))
            .orders(new HashSet<>())
            .build();
    }

    static OrderDto fromOrderToOrderDto(Order order) {
        return order == null ? null : OrderDto
            .builder()
            .date(order.getDate())
            .discount(order.getDiscount())
            .quantity(order.getQuantity())
            .customerDto(order.getCustomer() == null ? null : fromCustomerToCustomerDto(order.getCustomer()))
            .paymentDto(order.getPayment() == null ? null : fromPaymentToPaymentDto(order.getPayment()))
            .productDto(order.getProduct() == null ? null : fromProductToProductDto(order.getProduct()))
            .build();
    }

    static Order fromOrderDtoToOrder(OrderDto orderDto) {
        return orderDto == null ? null : Order
            .builder()
            .date(orderDto.getDate())
            .discount(orderDto.getDiscount())
            .quantity(orderDto.getQuantity())
            .customer(orderDto.getCustomerDto() == null ? null : fromCustomerDtoToCustomer(orderDto.getCustomerDto()))
            .payment(orderDto.getPaymentDto() == null ? null : fromPaymentDtoToPayment(orderDto.getPaymentDto()))
            .product(orderDto.getProductDto() == null ? null : fromProductDtoToProduct(orderDto.getProductDto()))
            .build();
    }

    static GuaranteeComponentDto fromGuaranteeComponentToGuaranteeComponentDto(GuaranteeComponent guaranteeComponent) {
        return guaranteeComponent == null ? null : GuaranteeComponentDto
            .builder()
            .productDto(guaranteeComponent.getProduct() == null ? null : fromProductToProductDto(guaranteeComponent.getProduct()))
            .build();
    }

    static GuaranteeComponent fromGuaranteeComponentDtoToGuaranteeComponent(GuaranteeComponentDto guaranteeComponentDto) {
        return guaranteeComponentDto == null ? null : GuaranteeComponent
            .builder()
            .product(guaranteeComponentDto.getProductDto() == null ? null : fromProductDtoToProduct(guaranteeComponentDto.getProductDto()))
            .build();
    }

    static PaymentDto fromPaymentToPaymentDto(Payment payment) {
        return payment == null ? null : PaymentDto
            .builder()
            .id(payment.getId())
            .build();
    }

    static Payment fromPaymentDtoToPayment(PaymentDto paymentDto) {
        return paymentDto == null ? null : Payment
            .builder()
            .id(paymentDto.getId())
            .orders(new HashSet<>())
            .ePayments(new HashSet<>())
            .build();
    }

    static ProducerDto fromProducerToProducerDto(Producer producer) {
        return producer == null ? null : ProducerDto
            .builder()
            .name(producer.getName())
            .countryDto(producer.getCountry() == null ? null : fromCountryToCountryDto(producer.getCountry()))
            .tradeDto(producer.getTrade() == null ? null : fromTradeToTradeDto(producer.getTrade()))
            .build();
    }

    static Producer fromProducerDtoToProducer(ProducerDto producerDto) {
        return producerDto == null ? null : Producer
            .builder()
            .name(producerDto.getName())
            .country(producerDto.getCountryDto() == null ? null : fromCountryDtoToCountry(producerDto.getCountryDto()))
            .trade(producerDto.getTradeDto() == null ? null : fromTradeDtoToTrade(producerDto.getTradeDto()))
            .products(new HashSet<>())
            .build();
    }

    static ProductDto fromProductToProductDto(Product product) {
        return product == null ? null : ProductDto
            .builder()
            .name(product.getName())
            .price(product.getPrice())
            .categoryDto(product.getCategory() == null ? null : fromCategoryToCategoryDto(product.getCategory()))
            .producerDto(product.getProducer() == null ? null : fromProducerToProducerDto(product.getProducer()))
            .build();
    }

    static Product fromProductDtoToProduct(ProductDto productDto) {
        return productDto == null ? null : Product
            .builder()
            .name(productDto.getName())
            .price(productDto.getPrice())
            .category(productDto.getCategoryDto() == null ? null : fromCategoryDtoToCategory(productDto.getCategoryDto()))
            .producer(productDto.getProducerDto() == null ? null : fromProducerDtoToProducer(productDto.getProducerDto()))
            .orders(new HashSet<>())
            .guaranteeComponents(new HashSet<>())
            .stocks(new HashSet<>())
            .build();
    }

    static ShopDto fromShopToShopDto(Shop shop) {
        return shop == null ? null : ShopDto
            .builder()
            .name(shop.getName())
            .countryDto(shop.getCountry() == null ? null : fromCountryToCountryDto(shop.getCountry()))
            .build();
    }

    static Shop fromShopDtoToShop(ShopDto shopDto) {
        return shopDto == null ? null : Shop
            .builder()
            .name(shopDto.getName())
            .country(shopDto.getCountryDto() == null ? null : fromCountryDtoToCountry(shopDto.getCountryDto()))
            .stocks(new HashSet<>())
            .build();
    }

    static StockDto fromStockToStockDto(Stock stock) {
        return stock == null ? null : StockDto
            .builder()
            .quantity(stock.getQuantity())
            .productDto(stock.getProduct() == null ? null : fromProductToProductDto(stock.getProduct()))
            .shopDto(stock.getShop() == null ? null : fromShopToShopDto(stock.getShop()))
            .build();
    }

    static Stock fromStockDtoToStock(StockDto stockDto) {
        return stockDto == null ? null : Stock
            .builder()
            .quantity(stockDto.getQuantity())
            .product(stockDto.getProductDto() == null ? null : fromProductDtoToProduct(stockDto.getProductDto()))
            .shop(stockDto.getShopDto() == null ? null : fromShopDtoToShop(stockDto.getShopDto()))
            .build();
    }

    static TradeDto fromTradeToTradeDto(Trade trade) {
        return trade == null ? null : TradeDto
            .builder()
            .name(trade.getName())
            .build();
    }

    static Trade fromTradeDtoToTrade(TradeDto tradeDto) {
        return tradeDto == null ? null : Trade
            .builder()
            .name(tradeDto.getName())
            .producers(new HashSet<>())
            .build();
    }
}
