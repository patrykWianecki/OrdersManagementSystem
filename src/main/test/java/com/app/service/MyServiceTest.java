package com.app.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.exparity.hamcrest.date.LocalDateMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.app.exception.MyException;
import com.app.model.Category;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.Producer;
import com.app.model.Product;
import com.app.model.dto.StockDto;
import com.app.repository.category.CategoryRepository;
import com.app.repository.country.CountryRepository;
import com.app.repository.customer.CustomerRepository;
import com.app.repository.order.OrderRepository;
import com.app.repository.producer.ProducerRepository;
import com.app.repository.product.ProductRepository;
import com.app.repository.shop.ShopRepository;
import com.app.repository.stock.StockRepository;
import com.app.repository.trade.TradeRepository;

import static com.app.model.EGuarantee.*;
import static com.app.service.builders.MockDataForServiceTests.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MyServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProducerRepository producerRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ShopRepository shopRepository;
    @Mock
    private StockRepository stockRepository;
    @Mock
    private TradeRepository tradeRepository;
    @Mock
    private StockService stockService;

    @InjectMocks
    private MyService myService;

    @Test
    void should_successfully_find_categories_with_product_that_costs_most() {
        // given
        when(productRepository.findAll()).thenReturn(createProducts());
        when(categoryRepository.findOne(anyLong())).thenReturn(Optional.of(createCategories().get(1)));

        // when
        Map<Category, Product> actualMap = myService.biggestProductPriceInEachCategory();

        // then
        assertNotNull(actualMap);
        assertEquals(1, actualMap.size());
        assertEquals("CATEGORY_NAME_2", actualMap.keySet().stream().map(Category::getName).findFirst().orElse(null));
        assertEquals("PRODUCT_NAME_2", actualMap.values().stream().map(Product::getName).findFirst().orElse(null));
        assertEquals(BigDecimal.valueOf(200), actualMap.values().stream().map(Product::getPrice).findFirst().orElse(null));
    }

    @Test
    void should_successfully_find_products_ordered_by_customer_from_given_country() {
        // given
        when(orderRepository.findAll()).thenReturn(createOrders());
        when(customerRepository.findOne(anyLong())).thenReturn(Optional.of(createCustomers().get(0)));

        // when
        Map<Customer, List<Order>> actualMap = myService.productsOrderedByCustomersFromGivenCountry(1L);

        // then
        assertNotNull(actualMap);
        assertEquals(1, actualMap.size());
        assertEquals("CUSTOMER_NAME_1", actualMap.keySet().stream().findFirst().map(Customer::getName).orElse(null));
        assertEquals("CUSTOMER_SURNAME_1", actualMap.keySet().stream().findFirst().map(Customer::getSurname).orElse(null));
        assertEquals(20, actualMap.keySet().stream().findFirst().map(Customer::getAge).orElse(null));

        List<Order> actualOrdersOfCustomer = actualMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        assertNotNull(actualOrdersOfCustomer);
        assertEquals(2, actualOrdersOfCustomer.size());

        Order actualFirstOrder = actualOrdersOfCustomer.get(0);
        assertEquals("PRODUCT_NAME_1", actualFirstOrder.getProduct().getName());
        assertEquals(BigDecimal.valueOf(100), actualFirstOrder.getProduct().getPrice());

        Order actualSecondOrder = actualOrdersOfCustomer.get(1);
        assertEquals("PRODUCT_NAME_2", actualSecondOrder.getProduct().getName());
        assertEquals(BigDecimal.valueOf(200), actualSecondOrder.getProduct().getPrice());
    }

    @Test
    void should_successfully_find_sorted_products_with_given_guarantees() {
        // given
        when(productRepository.findAll()).thenReturn(createProducts());

        // when
        List<Product> actualProducts = myService.sortedProductsWithGuaranteeByCategory(Set.of(EXCHANGE, SERVICE));

        // then
        assertNotNull(actualProducts);
        assertEquals(2, actualProducts.size());

        Product actualFirstProduct = actualProducts.get(0);
        assertEquals("PRODUCT_NAME_1", actualFirstProduct.getName());
        assertEquals(BigDecimal.valueOf(100), actualFirstProduct.getPrice());

        Product actualSecondProduct = actualProducts.get(1);
        assertEquals("PRODUCT_NAME_2", actualSecondProduct.getName());
        assertEquals(BigDecimal.valueOf(200), actualSecondProduct.getPrice());
    }

    @Test
    void should_successfully_find_producers_with_given_trade_name_and_who_produced_more_than_given() {
        // given
        when(tradeRepository.findAll()).thenReturn(createTrades());
        when(stockRepository.findAll()).thenReturn(createStocks());
        when(producerRepository.findAll()).thenReturn(createProducers());

        // when
        List<Producer> actualProducers = myService.producersWithGivenTradeNameWhichProducesMoreThanGivenNumber("TRADE_NAME_1", 10);

        // then
        assertNotNull(actualProducers);
        assertEquals(1, actualProducers.size());

        Producer actualProducer = actualProducers.get(0);
        assertEquals(1L, actualProducer.getId());
        assertEquals("PRODUCER_NAME_1", actualProducer.getName());
        assertEquals("TRADE_NAME_1", actualProducer.getTrade().getName());
    }

    @Test
    void should_successfully_find_orders_from_given_date_range_and_price() {
        // given
        LocalDate currentDate = LocalDate.now();
        when(orderRepository.findAll()).thenReturn(createOrders());

        // when
        List<Order> actualOrders = myService
            .collectOrdersFromGivenDateRangeAndPriceGreaterThanGiven(currentDate.plusDays(3), currentDate, BigDecimal.valueOf(50));

        // then
        assertNotNull(actualOrders);
        assertEquals(2, actualOrders.size());

        Order actualFirstOrder = actualOrders.get(0);
        assertEquals(1L, actualFirstOrder.getId());
        assertEquals(100, actualFirstOrder.getQuantity());
        assertThat(currentDate, LocalDateMatchers.within(1, ChronoUnit.DAYS, actualFirstOrder.getDate()));
        assertEquals(BigDecimal.valueOf(10), actualFirstOrder.getDiscount());

        Order actualSecondOrder = actualOrders.get(1);
        assertEquals(2L, actualSecondOrder.getId());
        assertEquals(200, actualSecondOrder.getQuantity());
        assertThat(currentDate, LocalDateMatchers.within(2, ChronoUnit.DAYS, actualSecondOrder.getDate()));
        assertEquals(BigDecimal.valueOf(20), actualSecondOrder.getDiscount());
    }

    @Test
    void should_successfully_find_products_by_given_name_surname_and_country_sorted_by_producer() {
        // given
        when(countryRepository.findOne(anyLong())).thenReturn(Optional.of(createCountries().get(0)));
        when(customerRepository.findByNameAndSurnameAndCountry(anyString(), anyString(), anyLong()))
            .thenReturn(Optional.of(createCustomers().get(0)));
        when(orderRepository.findAll()).thenReturn(createOrders());

        // when
        List<Product> actualProducts = myService
            .collectProductsByGivenNameSurnameAndCountrySortedByProducer("CUSTOMER_NAME_1", "CUSTOMER_SURNAME_1", 1L);

        System.out.println(actualProducts);
        // then
        assertNotNull(actualProducts);
        assertEquals(2, actualProducts.size());

        Product actualFirstProduct = actualProducts.get(0);
        assertEquals(1L, actualFirstProduct.getId());
        assertEquals("PRODUCT_NAME_1", actualFirstProduct.getName());
        assertEquals(BigDecimal.valueOf(100), actualFirstProduct.getPrice());

        Product actualSecondProduct = actualProducts.get(1);
        assertEquals(2L, actualSecondProduct.getId());
        assertEquals("PRODUCT_NAME_2", actualSecondProduct.getName());
        assertEquals(BigDecimal.valueOf(200), actualSecondProduct.getPrice());
    }

    @Test
    void should_successfully_check_if_customer_with_given_parameters_does_not_exist() {
        // given
        when(customerRepository.checkIfCustomerWithIdAndNameAndSurnameExists(anyLong(), anyString(), anyString())).thenReturn(false);

        // when
        Long actualId = myService.findAvailableCustomerId("CUSTOMER_NAME_1", "CUSTOMER_SURNAME_1", 1L);

        // then
        assertEquals(1L, actualId);
    }

    @Test
    void should_throw_exception_when_customer_with_given_parameters_exist() {
        // given
        when(customerRepository.checkIfCustomerWithIdAndNameAndSurnameExists(anyLong(), anyString(), anyString())).thenReturn(true);

        // when
        assertThrows(MyException.class, () -> myService.findAvailableCustomerId("CUSTOMER_NAME_1", "CUSTOMER_SURNAME_1", 1L));

        // then
    }

    @Test
    void should_successfully_check_if_producer_with_given_parameters_does_not_exist() {
        // given
        when(producerRepository.checkIfProducerWithNameTradeAndCountryExists(anyString(), anyLong(), anyLong())).thenReturn(false);

        // when
        Long actualId = myService.findAvailableProducerName("PRODUCER_NAME_1", 1L, 1L);

        // then
        assertEquals(1L, actualId);
    }

    @Test
    void should_throw_exception_when_producer_with_given_parameters_exist() {
        // given
        when(producerRepository.checkIfProducerWithNameTradeAndCountryExists(anyString(), anyLong(), anyLong())).thenReturn(true);

        // when
        assertThrows(MyException.class, () -> myService.findAvailableProducerName("PRODUCER_NAME_1", 1L, 1L));

        // then
    }

    @Test
    void should_successfully_check_if_product_with_given_parameters_does_not_exist() {
        // given
        when(productRepository.checkIfProductWithNameCategoryAndProducerExists(anyString(), anyLong(), anyLong())).thenReturn(false);

        // when
        Long actualId = myService.findAvailableProductName("PRODUCT_NAME_1", 1L, 1L);

        // then
        assertEquals(1L, actualId);
    }

    @Test
    void should_throw_exception_when_product_with_given_parameters_exist() {
        // given
        when(productRepository.checkIfProductWithNameCategoryAndProducerExists(anyString(), anyLong(), anyLong())).thenReturn(true);

        // when
        assertThrows(MyException.class, () -> myService.findAvailableProductName("PRODUCT_NAME_1", 1L, 1L));

        // then
    }

    @Test
    void should_successfully_check_if_shop_with_given_parameters_does_not_exist() {
        // given
        when(shopRepository.checkIfShopWithNameAndCountryExists(anyString(), anyLong())).thenReturn(false);

        // when
        Long actualId = myService.findAvailableShopName("SHOP_NAME_1", 1L);

        // then
        assertEquals(1L, actualId);
    }

    @Test
    void should_throw_exception_when_shop_with_given_parameters_exist() {
        // given
        when(shopRepository.checkIfShopWithNameAndCountryExists(anyString(), anyLong())).thenReturn(true);

        // when
        assertThrows(MyException.class, () -> myService.findAvailableShopName("SHOP_NAME_1", 1L));

        // then
    }

    @Test
    void should_successfully_add_stock() {
        // given
        when(stockRepository.findAll()).thenReturn(createStocks());
        when(shopRepository.findOne(anyLong())).thenReturn(Optional.of(createShops().get(0)));
        when(productRepository.findOne(anyLong())).thenReturn(Optional.of(createProducts().get(0)));

        // when
        myService.oneProductFromOneShopInStock(1L, 1L, 10);
        myService.oneProductFromOneShopInStock(1L, 2L, 10);

        // then
        verify(stockService, times(2)).addStock(any());
    }

    @Test
    void should_successfully_update_product_quantity_in_stock() {
        // given
        when(stockRepository.findAll()).thenReturn(createStocks());
        when(shopRepository.findOne(anyLong())).thenReturn(Optional.of(createShops().get(0)));
        when(productRepository.findOne(anyLong())).thenReturn(Optional.of(createProducts().get(0)));

        // when
        myService.updateProductQuantity(10, 1L);

        // then
        verify(stockRepository, times(1)).delete(anyLong());
        ArgumentCaptor<StockDto> captor = ArgumentCaptor.forClass(StockDto.class);
        verify(stockService, times(1)).addStock(captor.capture());
        assertEquals(140, captor.getValue().getQuantity());
    }
}