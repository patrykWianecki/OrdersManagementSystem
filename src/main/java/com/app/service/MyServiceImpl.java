package com.app.service;

import com.app.dto.*;
import com.app.model.*;
import com.app.repository.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyServiceImpl implements MyService {

    private MyMapper myMapper = new MyMapper();
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
            throw new Errors(EMessage.SERVICE_ADD + " Customer " + e.getMessage(), LocalDate.now());
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
            throw new Errors(EMessage.SERVICE_ADD + " Shop " + e.getMessage(), LocalDate.now());
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
            throw new Errors(EMessage.SERVICE_ADD + " Producer " + e.getMessage(), LocalDate.now());
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
            throw new Errors(EMessage.SERVICE_ADD + " Product " + e.getMessage(), LocalDate.now());
        }
    }

    @Override
    public void addStock(StockDto stockDto) throws Errors {
        try {
            Product product
                    = productRepository
                    .findByName(stockDto.getProductDto().getName())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " Product " + stockDto.getProductDto().getName(), LocalDate.now()));
            Shop shop
                    = shopRepository
                    .findByName(stockDto.getProductDto().getName())
                    .orElseThrow(() -> new Errors(EMessage.FAILED_TO_GET + " Shop " + stockDto.getShopDto().getName(), LocalDate.now()));

            Stock stock = myMapper.fromStockDtoToStock(stockDto);
            stock.setProduct(product);
            stock.setShop(shop);
            stockRepository.addOrUpdate(stock);
        } catch (Exception e) {
            errors.addSuppressed(e);
            throw new Errors(EMessage.SERVICE_ADD + " Stock " + e.getMessage(), LocalDate.now());
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
            throw new Errors(EMessage.SERVICE_ADD + " Customer Order " + e.getMessage(), LocalDate.now());
        }
    }

    @Override
    public Map<Category, Product> biggestProductPriceInEachCategory() {
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
    public Map<Customer, List<CustomerOrder>> productsOrderedByCustomersFromGivenCountry(Long id) {
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
    public List<Product> sortedProductsWithGuaranteeByCategory() {
        return null;
    }

    @Override
    public List<Shop> shopsWithProductsInStockWithDifferentCountryThanShop() {
        return null;
    }

    @Override
    public List<Producer> producersWithGivenTradeNameWhichProducesMoreThanGivenNumber() {
        return null;
    }

    @Override
    public List<CustomerOrder> ordersWithGivenDateAndPriceAmount() {
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
    public List<Customer> customersWithMoreThanOneProductWithSameCountry() {
        return null;
    }
}
