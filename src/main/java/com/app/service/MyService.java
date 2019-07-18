package com.app.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Category;
import com.app.model.Country;
import com.app.model.Customer;
import com.app.model.EGuarantee;
import com.app.model.Order;
import com.app.model.Producer;
import com.app.model.Product;
import com.app.model.Shop;
import com.app.model.Stock;
import com.app.model.Trade;
import com.app.model.dto.ProductDto;
import com.app.model.dto.ShopDto;
import com.app.model.dto.StockDto;
import com.app.repository.category.CategoryRepository;
import com.app.repository.category.CategoryRepositoryImpl;
import com.app.repository.country.CountryRepository;
import com.app.repository.country.CountryRepositoryImpl;
import com.app.repository.customer.CustomerRepository;
import com.app.repository.customer.CustomerRepositoryImpl;
import com.app.repository.order.OrderRepository;
import com.app.repository.order.OrderRepositoryImpl;
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
    private StockService stockService = new StockService();
    private OrderRepository orderRepository = new OrderRepositoryImpl();

    public Map<Category, Product> biggestProductPriceInEachCategory() {
        return productRepository.findAll()
            .stream()
            .collect(Collectors.groupingBy(product -> findCategoryByIdWithErrorCheck(product.getCategory().getId())))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                product -> product.getValue()
                    .stream()
                    .max(Comparator.comparing(Product::getPrice))
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing product with highest price")))
            );
    }

    public Map<Customer, List<Order>> productsOrderedByCustomersFromGivenCountry(Long id) {
        return orderRepository.findAll()
            .stream()
            .collect(Collectors.groupingBy(order -> findCustomerByIdWithErrorCheck(order.getCustomer().getId())))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                orders -> orders.getValue()
                    .stream()
                    .filter(x -> id.equals(x.getCustomer().getCountry().getId()))
                    .collect(Collectors.toList()))
            );
    }

    public List<Product> sortedProductsWithGuaranteeByCategory(Set<EGuarantee> guaranteeComponents) {
        return productRepository.findAll()
            .stream()
            .filter(product -> product.getGuaranteeComponents()
                .stream()
                .anyMatch(guaranteeComponent -> guaranteeComponent.getEGuarantees().containsAll(guaranteeComponents))
            )
            .collect(Collectors.toList());
    }

    /*Pobranie z bazy danych listy sklepów,
    które w magazynie posiadają produkty,
    których kraj pochodzenia jest inny niż kraje,
    w których występują oddziały sklepu.*/
    // TODO + TEST
    public List<Shop> shopsWithProductsInStockWithDifferentCountryThanShop() {

        // shop -> country
        // stock -> product && shop

        return null;
    }

    public List<Producer> producersWithGivenTradeNameWhichProducesMoreThanGivenNumber(String tradeName, Integer productsNumber) {
        return producerRepository.findAll()
            .stream()
            .map(producer -> {
                Long producerId = producer.getId();

                Trade trade = tradeRepository.findAll()
                    .stream()
                    .filter(t -> t.getProducers().stream().anyMatch(p -> producerId.equals(p.getId())))
                    .findFirst()
                    .orElse(null);

                Stock stock = stockRepository.findAll()
                    .stream()
                    .filter(s -> producerId.equals(s.getProduct().getProducer().getId()))
                    .findFirst()
                    .orElse(null);

                if (trade == null || stock == null) {
                    return null;
                }

                if (tradeName.equals(trade.getName()) && stock.getQuantity() > productsNumber) {
                    return producer;
                }

                return null;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    public List<Order> collectOrdersFromGivenDateRangeAndPriceGreaterThanGiven(LocalDate minOrderDate, LocalDate maxOrderDate,
        BigDecimal minPrice) {
        return orderRepository.findAll()
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

    public List<Product> collectProductsByGivenNameSurnameAndCountrySortedByProducer(String name, String surname, Long countryId) {
        return orderRepository.findAll()
            .stream()
            .map(order -> {
                Country country = findCountryByIdWithErrorCheck(countryId);
                Customer customer = customerRepository.findByNameAndSurnameAndCountry(name, surname, countryId)
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE,
                        "Missing customer with name " + name + ", surname " + surname + " and country " + country.getName()));

                if (order.getCustomer().getId().equals(customer.getId())) {
                    return order.getProduct();
                }

                return null;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    // TODO + TEST
    public List<Customer> collectCustomersWithProductsOrderedFromSameCountryAsClient() {
        return orderRepository.findAll()
            .stream()
            .filter(x -> {
                Country customerCountry = findCountryByIdWithErrorCheck(x.getCustomer().getId());
                Country productCountry = findCountryByIdWithErrorCheck(x.getProduct().getId());

                return true;
            })
            .map(Order::getCustomer)
            .collect(Collectors.toList());
    }

    public Long findAvailableCustomerId(String customerName, String customerSurname, Long countryId) {
        if (customerRepository.checkIfCustomerWithIdAndNameAndSurnameExists(countryId, customerName, customerSurname)) {
            throw new MyException(ExceptionCode.SERVICE, "Customer from chosen country already exists");
        }
        return countryId;
    }

    public Long findAvailableProducerName(String producerName, Long tradeId, Long countryId) {
        if (producerRepository.checkIfProducerWithNameTradeAndCountryExists(producerName, tradeId, countryId)) {
            throw new MyException(ExceptionCode.SERVICE, "Producer with given trade and country already exists");
        }
        return countryId;
    }

    public Long findAvailableProductName(String productName, Long categoryId, Long producerId) {
        if (productRepository.checkIfProductWithNameCategoryAndProducerExists(productName, categoryId, producerId)) {
            throw new MyException(ExceptionCode.SERVICE, "Product with given category and producer already exists");
        }
        return producerId;
    }

    public Long findAvailableShopName(String shopName, Long countryId) {
        if (shopRepository.checkIfShopWithNameAndCountryExists(shopName, countryId)) {
            throw new MyException(ExceptionCode.SERVICE, "Shop from chosen country already exists");
        }
        return countryId;
    }

    public void oneProductFromOneShopInStock(Long shopId, Long productId, Integer stockQuantity) {
        List<Stock> stocks = stockRepository.findAll()
            .stream()
            .filter(stock -> shopId.equals(stock.getShop().getId()) && productId.equals(stock.getProduct().getId()))
            .collect(Collectors.toList());

        if (stocks.size() != 0) {
            int updatedQuantity = stocks.get(0).getQuantity() + stockQuantity;
            long upId = stocks.get(0).getId();
            stockRepository.delete(upId);
            addStock(updatedQuantity, shopId, productId);
        } else {
            addStock(stockQuantity, shopId, productId);
        }
    }

    private void addStock(Integer quantity, Long shopId, Long productId) {
        stockService.addStock(StockDto.builder()
            .quantity(quantity)
            .shopDto(ShopDto.builder()
                .name(findShopByIdWithErrorCheck(shopId).getName())
                .build())
            .productDto(ProductDto.builder()
                .name(findProductByIdWithErrorCheck(productId).getName())
                .build())
            .build()
        );
    }

    public void updateProductQuantity(Integer quantity, Long productId) {
        Stock stockToUpdate = stockRepository.findAll()
            .stream()
            .filter(stock -> productId.equals(stock.getProduct().getId()))
            .findFirst()
            .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing stock with"));

        stockRepository.delete(stockToUpdate.getId());

        stockService.addStock(StockDto.builder()
            .quantity(stockToUpdate.getQuantity() - quantity)
            .productDto(ProductDto.builder()
                .name(findProductByIdWithErrorCheck(stockToUpdate.getProduct().getId()).getName())
                .build())
            .shopDto(ShopDto.builder()
                .name(findShopByIdWithErrorCheck(stockToUpdate.getShop().getId()).getName())
                .build())
            .build());
    }

    public Category findCategoryByIdWithErrorCheck(Long id) {
        return categoryRepository
            .findOne(id)
            .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing category with id " + id));
    }

    public Country findCountryByIdWithErrorCheck(Long id) {
        return countryRepository
            .findOne(id)
            .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing country with id " + id));
    }

    public Customer findCustomerByIdWithErrorCheck(Long id) {
        return customerRepository
            .findOne(id)
            .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing customer with id " + id));
    }

    // TODO TEST
    public Producer findProducerByIdWithErrorCheck(Long id) {
        return producerRepository
            .findOne(id)
            .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing producer with id " + id));
    }

    public Product findProductByIdWithErrorCheck(Long id) {
        return productRepository
            .findOne(id)
            .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing product with id " + id));
    }

    private Shop findShopByIdWithErrorCheck(Long id) {
        return shopRepository
            .findOne(id)
            .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing shop with id " + id));
    }

    // TODO TEST
    public Trade findTradeByIdWithErrorCheck(Long id) {
        return tradeRepository
            .findOne(id)
            .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing trade with id " + id));
    }
}
