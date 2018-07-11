package com.app.dto;

import com.app.model.*;

import java.util.HashSet;

public class MyMapper {
    public CategoryDto fromCategoryToCategoryDto(Category category) {
        return category == null ? null : CategoryDto
                .builder()
                .name(category.getName())
                .build();
    }

    public Category fromCategoryDtoToCategory(CategoryDto categoryDto) {
        return categoryDto == null ? null : Category
                .builder()
                .name(categoryDto.getName())
                .products(new HashSet<>())
                .build();
    }

    public CountryDto fromCountryToCountryDto(Country country) {
        return country == null ? null : CountryDto
                .builder()
                .name(country.getName())
                .build();
    }

    public Country fromCountryDtoToCountry(CountryDto countryDto) {
        return countryDto == null ? null : Country
                .builder()
                .name(countryDto.getName())
                .customers(new HashSet<>())
                .producers(new HashSet<>())
                .shops(new HashSet<>())
                .build();
    }

    public CustomerDto fromCustomerToCustomerDto(Customer customer) {
        return customer == null ? null : CustomerDto
                .builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .age(customer.getAge())
                .countryDto(customer.getCountry() == null ? null : fromCountryToCountryDto(customer.getCountry()))
                .build();
    }

    public Customer fromCustomerDtoToCustomer(CustomerDto customerDto) {
        return customerDto == null ? null : Customer
                .builder()
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .age(customerDto.getAge())
                .country(customerDto.getCountryDto() == null ? null : fromCountryDtoToCountry(customerDto.getCountryDto()))
                .customerOrders(new HashSet<>())
                .build();
    }

    public CustomerOrderDto fromCustomerOrderToCustomerOrderDto(CustomerOrder customerOrder) {
        return customerOrder == null ? null : CustomerOrderDto
                .builder()
                .date(customerOrder.getDate())
                .discount(customerOrder.getDiscount())
                .quantity(customerOrder.getQuantity())
                .customerDto(customerOrder.getCustomer() == null ? null : fromCustomerToCustomerDto(customerOrder.getCustomer()))
                .paymentDto(customerOrder.getPayment() == null ? null : fromPaymentToPaymentDto(customerOrder.getPayment()))
                .productDto(customerOrder.getProduct() == null ? null : fromProductToProductDto(customerOrder.getProduct()))
                .build();
    }

    public CustomerOrder fromCustomerOrderDtoToCustomerOrder(CustomerOrderDto customerOrderDto) {
        return customerOrderDto == null ? null : CustomerOrder
                .builder()
                .date(customerOrderDto.getDate())
                .discount(customerOrderDto.getDiscount())
                .quantity(customerOrderDto.getQuantity())
                .customer(customerOrderDto.getCustomerDto() == null ? null : fromCustomerDtoToCustomer(customerOrderDto.getCustomerDto()))
                .payment(customerOrderDto.getPaymentDto() == null ? null : fromPaymentDtoToPayment(customerOrderDto.getPaymentDto()))
                .product(customerOrderDto.getProductDto() == null ? null : fromProductDtoToProduct(customerOrderDto.getProductDto()))
                .build();
    }

    public GuaranteeComponentDto fromGuaranteeComponentToGuaranteeComponentDto(GuaranteeComponent guaranteeComponent) {
        return guaranteeComponent == null ? null : GuaranteeComponentDto
                .builder()
                .guaranteeComponent(guaranteeComponent.getGuaranteeComponent())
                .productDto(guaranteeComponent.getProduct() == null ? null : fromProductToProductDto(guaranteeComponent.getProduct()))
                .build();
    }

    public GuaranteeComponent fromGuaranteeComponentDtoToGuaranteeComponent(GuaranteeComponentDto guaranteeComponentDto) {
        return guaranteeComponentDto == null ? null : GuaranteeComponent
                .builder()
                .guaranteeComponent(guaranteeComponentDto.getGuaranteeComponent())
                .product(guaranteeComponentDto.getProductDto() == null ? null : fromProductDtoToProduct(guaranteeComponentDto.getProductDto()))
                .eGuarantees(new HashSet<>())
                .build();
    }

    public PaymentDto fromPaymentToPaymentDto(Payment payment) {
        return payment == null ? null : PaymentDto
                .builder()
                .payment(payment.getPayment())
                .build();
    }

    public Payment fromPaymentDtoToPayment(PaymentDto paymentDto) {
        return paymentDto == null ? null : Payment
                .builder()
                .payment(paymentDto.getPayment())
                .customerOrders(new HashSet<>())
                .ePayments(new HashSet<>())
                .build();
    }

    public ProducerDto fromProducerToProducerDto(Producer producer) {
        return producer == null ? null : ProducerDto
                .builder()
                .name(producer.getName())
                .countryDto(producer.getCountry() == null ? null : fromCountryToCountryDto(producer.getCountry()))
                .tradeDto(producer.getTrade() == null ? null : fromTradeToTradeDto(producer.getTrade()))
                .build();
    }

    public Producer fromProducerDtoToProducer(ProducerDto producerDto) {
        return producerDto == null ? null : Producer
                .builder()
                .name(producerDto.getName())
                .country(producerDto.getCountryDto() == null ? null : fromCountryDtoToCountry(producerDto.getCountryDto()))
                .trade(producerDto.getTradeDto() == null ? null : fromTradeDtoToTrade(producerDto.getTradeDto()))
                .products(new HashSet<>())
                .build();
    }

    public ProductDto fromProductToProductDto(Product product) {
        return product == null ? null : ProductDto
                .builder()
                .name(product.getName())
                .price(product.getPrice())
                .categoryDto(product.getCategory() == null ? null : fromCategoryToCategoryDto(product.getCategory()))
                .producerDto(product.getProducer() == null ? null : fromProducerToProducerDto(product.getProducer()))
                .build();
    }

    public Product fromProductDtoToProduct(ProductDto productDto) {
        return productDto == null ? null : Product
                .builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .category(productDto.getCategoryDto() == null ? null : fromCategoryDtoToCategory(productDto.getCategoryDto()))
                .producer(productDto.getProducerDto() == null ? null : fromProducerDtoToProducer(productDto.getProducerDto()))
                .customerOrders(new HashSet<>())
                .guaranteeComponents(new HashSet<>())
                .stocks(new HashSet<>())
                .build();
    }

    public ShopDto fromShopToShopDto(Shop shop) {
        return shop == null ? null : ShopDto
                .builder()
                .name(shop.getName())
                .countryDto(shop.getCountry() == null ? null : fromCountryToCountryDto(shop.getCountry()))
                .build();
    }

    public Shop fromShopDtoToShop(ShopDto shopDto) {
        return shopDto == null ? null : Shop
                .builder()
                .name(shopDto.getName())
                .country(shopDto.getCountryDto() == null ? null : fromCountryDtoToCountry(shopDto.getCountryDto()))
                .stocks(new HashSet<>())
                .build();
    }

    public StockDto fromStockToStockDto(Stock stock) {
        return stock == null ? null : StockDto
                .builder()
                .quantity(stock.getQuantity())
                .productDto(stock.getProduct() == null ? null : fromProductToProductDto(stock.getProduct()))
                .shopDto(stock.getShop() == null ? null : fromShopToShopDto(stock.getShop()))
                .build();
    }

    public Stock fromStockDtoToStock(StockDto stockDto) {
        return stockDto == null ? null : Stock
                .builder()
                .quantity(stockDto.getQuantity())
                .product(stockDto.getProductDto() == null ? null : fromProductDtoToProduct(stockDto.getProductDto()))
                .shop(stockDto.getShopDto() == null ? null : fromShopDtoToShop(stockDto.getShopDto()))
                .build();
    }

    public TradeDto fromTradeToTradeDto(Trade trade) {
        return trade == null ? null : TradeDto
                .builder()
                .name(trade.getName())
                .build();
    }

    public Trade fromTradeDtoToTrade(TradeDto tradeDto) {
        return tradeDto == null ? null : Trade
                .builder()
                .name(tradeDto.getName())
                .producers(new HashSet<>())
                .build();
    }
}
