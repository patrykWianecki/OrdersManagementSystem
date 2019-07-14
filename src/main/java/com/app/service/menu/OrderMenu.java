package com.app.service.menu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import com.app.model.dto.CustomerDto;
import com.app.model.dto.OrderDto;
import com.app.model.dto.PaymentDto;
import com.app.model.dto.ProductDto;
import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.State;
import com.app.repository.customer.CustomerRepository;
import com.app.repository.customer.CustomerRepositoryImpl;
import com.app.repository.product.ProductRepository;
import com.app.repository.product.ProductRepositoryImpl;
import com.app.service.OrderService;
import com.app.service.CustomerService;
import com.app.service.MyService;
import com.app.service.ProductService;
import com.app.validator.CustomerOrderValidator;
import com.app.validator.ToolsValidator;

public class OrderMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static State state;

    private MyService myService = new MyService();
    private OrderService orderService = new OrderService();
    private ProductService productService = new ProductService();
    private CustomerService customerService = new CustomerService();
    private ProductRepository productRepository = new ProductRepositoryImpl();
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private ToolsValidator toolsValidator = new ToolsValidator();
    private CustomerOrderValidator customerOrderValidator = new CustomerOrderValidator();

    State printOrder() {
        System.out.println("1 - add new Order");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                state = addOrder();
                break;
            case 0:
                state = State.INIT;
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.ORDER;
                break;
        }
        return state;
    }

    private State addOrder() {
        System.out.println("Chose product from list:");
        productService.printAllProducts();

        long productId = toolsValidator.chooseId(scanner.nextLine());
        System.out.println("Chose customer from list:");
        customerService.printAllCustomers();

        long customerId = toolsValidator.chooseId(scanner.nextLine());
        System.out.println("Enter discount:");
        BigDecimal discount = customerOrderValidator.validateDiscount(scanner.nextLine());

        System.out.println("Chose payment id from list:");
        // TODO
//        myService.printAllPayments();
//        int paymentId = myService.setPaymentType(scanner.nextLine());

        System.out.println("Enter quantity:");
        int quantity = customerOrderValidator.validateQuantity(scanner.nextLine(), productId);
        myService.updateProductQuantity(quantity, productId);

        orderService.addCustomerOrder(OrderDto
            .builder()
            .productDto(ProductDto
                .builder()
                .name(productRepository
                    .findOne(productId)
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))
                    .getName()
                )
                .build())
            .quantity(quantity)
            .discount(discount)
            .date(LocalDate.now())
            .paymentDto(PaymentDto
                .builder()
                // TODO
//                .payment(EPayment.returnPayment(paymentId))
                .build())
            .customerDto(CustomerDto
                .builder()
                .name(customerRepository
                    .findOne(customerId)
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))
                    .getName()
                )
                .build())
            .build()
        );

        return State.ORDER;
    }
}
