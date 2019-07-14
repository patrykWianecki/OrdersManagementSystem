package com.app.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.app.model.dto.ProductDto;
import com.app.model.dto.ShopDto;
import com.app.model.dto.StockDto;
import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Category;
import com.app.model.Country;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.EGuarantee;
import com.app.model.Producer;
import com.app.model.Product;
import com.app.model.Shop;
import com.app.model.Stock;
import com.app.model.Trade;
import com.app.repository.category.CategoryRepository;
import com.app.repository.category.CategoryRepositoryImpl;
import com.app.repository.country.CountryRepository;
import com.app.repository.country.CountryRepositoryImpl;
import com.app.repository.customer.CustomerRepository;
import com.app.repository.customer.CustomerRepositoryImpl;
import com.app.repository.customerorder.CustomerOrderRepository;
import com.app.repository.customerorder.CustomerOrderRepositoryImpl;
import com.app.repository.guaranteecomponent.GuaranteeComponentRepository;
import com.app.repository.guaranteecomponent.GuaranteeComponentRepositoryImpl;
import com.app.repository.producer.ProducerRepository;
import com.app.repository.producer.ProducerRepositoryImpl;
import com.app.repository.product.ProductRepository;
import com.app.repository.product.ProductRepositoryImpl;
import com.app.repository.shop.ShopRepository;
import com.app.repository.shop.ShopRepositoryImpl;
import com.app.repository.stock.StockRepository;
import com.app.repository.stock.StockRepositoryImpl;
import com.app.repository.trade.TradeRepository;
import com.app.repository.trade.TradeRepositoryImpl;

public class MyService {

    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private CountryRepository countryRepository = new CountryRepositoryImpl();
    private ProducerRepository producerRepository = new ProducerRepositoryImpl();
    private ProductRepository productRepository = new ProductRepositoryImpl();
    private ShopRepository shopRepository = new ShopRepositoryImpl();
    private StockRepository stockRepository = new StockRepositoryImpl();
    private TradeRepository tradeRepository = new TradeRepositoryImpl();
    private GuaranteeComponentRepository guaranteeComponentRepository = new GuaranteeComponentRepositoryImpl();
    private StockService stockService = new StockService();
    private CustomerOrderRepository customerOrderRepository = new CustomerOrderRepositoryImpl();

    public Map<Category, Product> biggestProductPriceInEachCategory() {
        return productRepository
            .findAll()
            .stream()
            .collect(Collectors.groupingBy(product -> categoryRepository
                .findOne(product.getCategory().getId())
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                p -> p.getValue()
                    .stream()
                    .max(Comparator.comparing(Product::getPrice))
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO")))
            );
    }

    public Map<Customer, List<Order>> productsOrderedByCustomersFromGivenCountry(Long id) {
        return customerOrderRepository
            .findAll()
            .stream()
            .collect(Collectors.groupingBy(order -> customerRepository
                .findOne(order.getCustomer().getId())
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO")))
            )
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                v -> v.getValue()
                    .stream()
                    .filter(x -> x.getCustomer().getCountry().getId().equals(id))
                    .collect(Collectors.toList()))
            );
    }

    public List<Product> sortedProductsWithGuaranteeByCategory(Set<EGuarantee> guaranteeComponents) {
        return productRepository
            .findAll()
            .stream()
            .filter(product -> product.getGuaranteeComponents().containsAll(guaranteeComponents))
            .collect(Collectors.toList());
    }

    /*Pobranie z bazy danych listy sklepów,
    które w magazynie posiadają produkty,
    których kraj pochodzenia jest inny niż kraje,
    w których występują oddziały sklepu.*/
    public List<Shop> shopsWithProductsInStockWithDifferentCountryThanShop() {

        // shop -> country
        // stock -> product && shop

        return null;
    }

    public List<Producer> producersWithGivenTradeNameWhichProducesMoreThanGivenNumber(String tradeName,
        Integer productsNumber) {
        return producerRepository
            .findAll()
            .stream()
            .map(producer -> {
                Long producerId = producer.getId();

                Trade trade = tradeRepository
                    .findAll()
                    .stream()
                    .filter(t -> t.getProducers().stream().anyMatch(p -> p.getId().equals(producerId)))
                    .findFirst()
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing product in trade repository"));

                Stock stock = stockRepository
                    .findAll()
                    .stream()
                    .filter(s -> s.getProduct().getProducer().getId().equals(producerId))
                    .findFirst()
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing product in stock repository"));

                if (trade.getName().equals(tradeName) && stock.getQuantity() > productsNumber) {
                    return producer;
                }

                return null;
            })
            .collect(Collectors.toList());
    }

    public List<Order> collectOrdersFromGivenDateRangeAndPriceGreaterThanGiven(LocalDate minOrderDate,
        LocalDate maxOrderDate,
        BigDecimal minPrice) {
        return customerOrderRepository
            .findAll()
            .stream()
            .filter(order -> {
                LocalDate[] minDate = {minOrderDate};
                LocalDate[] maxDate = {maxOrderDate};
                if (minOrderDate.isAfter(maxOrderDate)) {
                    minDate = new LocalDate[]{maxOrderDate};
                    maxDate = new LocalDate[]{minOrderDate};
                }
                return order.getDate().isAfter(minDate[0]) && order.getDate().isBefore(maxDate[0]);
            })
            .filter(order -> order.getProduct().getPrice().compareTo(minPrice) > 0)
            .collect(Collectors.toList());
    }

    public List<Product> collectProductsByGivenNameSurnameAndCountrySortedByProducer(String name,
        String surname, Long countryId) {
        return customerOrderRepository
            .findAll()
            .stream()
            .map(order -> {
                Country country = countryRepository
                    .findOne(countryId)
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing country with id " + countryId));
                Customer customer = customerRepository
                    .findByNameAndSurnameAndCountry(name, surname, countryId)
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE,
                        "Missing customer with name " + name + ", surname " + surname + " and country " + country.getName()));

                if (order.getCustomer().getId().equals(customer.getId())) {
                    return order.getProduct();
                }

                return null;
            })
            .collect(Collectors.toList());
    }

    public List<Customer> collectCustomersWithProductsOrderedFromSameCountryAsClient() {
        // product ma miec ten sam kraj co klient
        return customerOrderRepository
            .findAll()
            .stream()
            .filter(x -> {
                Country customerCountry = countryRepository
                    .findOne(x.getCustomer().getId())
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing customer in country"));
                Country productCountry = countryRepository
                    .findOne(x.getProduct().getId())
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing ..."));

                return true;
            })
            .map(Order::getCustomer)
            .collect(Collectors.toList());
    }

    public Long oneCustomerFromOneCountry(String customerName, String customerSurname, Long countryId) {
        List<Customer> customers
            = customerRepository
            .findAll()
            .stream()
            .filter(customer -> customer.getName().equals(customerName)
                && customer.getSurname().equals(customerSurname)
                && customer.getCountry().getId().equals(countryId))
            .collect(Collectors.toList());
        if (customers.size() != 0) {
            throw new MyException(ExceptionCode.SERVICE, "Customer from chosen country already exists");
        }
        return countryId;
    }

    public Long oneProducerNameAndTradeFromOneCountry(String producerName, Long tradeId, Long countryId) {
        List<Producer> producers
            = producerRepository
            .findAll()
            .stream()
            .filter(producer -> producer.getName().equals(producerName)
                && producer.getTrade().getId().equals(tradeId)
                && producer.getCountry().getId().equals(countryId))
            .collect(Collectors.toList());
        if (producers.size() != 0) {
            throw new MyException(ExceptionCode.SERVICE, "Producer with given trade and country already exists");
        }
        return countryId;
    }

    public Long oneProductNameAndCategoryFromOneProducer(String productName, Long categoryId, Long producerId) {
        List<Product> products
            = productRepository
            .findAll()
            .stream()
            .filter(product -> product.getName().equals(productName)
                && product.getCategory().getId().equals(categoryId)
                && product.getProducer().getId().equals(producerId))
            .collect(Collectors.toList());
        if (products.size() != 0) {
            throw new MyException(ExceptionCode.SERVICE, "Product with given category and producer already exists");
        }
        return producerId;
    }

    public Long oneShopFromOneCountry(String shopName, Long countryId) {
        List<Shop> shops
            = shopRepository
            .findAll()
            .stream()
            .filter(shop -> shop.getName().equals(shopName) && shop.getCountry().getId().equals(countryId))
            .collect(Collectors.toList());
        if (shops.size() != 0) {
            throw new MyException(ExceptionCode.SERVICE, "Shop from chosen country already exists");
        }
        return countryId;
    }

    public void updateProductQuantity(Integer quantity, Long productId) {
        List<Stock> stocks = stockRepository
            .findAll()
            .stream()
            .filter(id -> id.getProduct().getId().equals(productId))
            .collect(Collectors.toList());
        Stock stock = stocks.get(0);
        stockRepository.delete(stock.getId());
        stockService.addStock(StockDto
            .builder()
            .quantity(stock.getQuantity() - quantity)
            .productDto(ProductDto
                .builder()
                .name(productRepository
                    .findOne(stock.getProduct().getId())
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))
                    .getName())
                .build())
            .shopDto(ShopDto
                .builder()
                .name(shopRepository
                    .findOne(stock.getShop().getId())
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))
                    .getName())
                .build())
            .build());
    }
}
