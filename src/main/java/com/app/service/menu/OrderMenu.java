package com.app.service.menu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import com.app.model.State;
import com.app.model.dto.CustomerDto;
import com.app.model.dto.OrderDto;
import com.app.model.dto.PaymentDto;
import com.app.model.dto.ProductDto;
import com.app.service.CustomerService;
import com.app.service.MyService;
import com.app.service.OrderService;
import com.app.service.PaymentService;
import com.app.service.ProductService;
import com.app.validator.OrderValidator;
import com.app.validator.ToolsValidator;

class OrderMenu {

    private static Scanner scanner = new Scanner(System.in);

    private MyService myService = new MyService();
    private OrderService orderService = new OrderService();
    private ProductService productService = new ProductService();
    private CustomerService customerService = new CustomerService();
    private PaymentService paymentService = new PaymentService();
    private ToolsValidator toolsValidator = new ToolsValidator();
    private OrderValidator orderValidator = new OrderValidator();

    State printOrder() {
        System.out.println("1 - add new Order");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        State state;
        switch (choice) {
            case 1: {
                state = addOrder();
                break;
            }
            case 0: {
                state = State.INIT;
                break;
            }
            default: {
                System.out.println("Wrong choice!");
                state = State.ORDER;
                break;
            }
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
        BigDecimal discount = orderValidator.validateDiscount(scanner.nextLine());

        System.out.println("Chose payment id from list:");
        paymentService.printAllPayments();
        long paymentId = toolsValidator.chooseId(scanner.nextLine());

        System.out.println("Enter quantity:");
        int quantity = orderValidator.validateQuantity(scanner.nextLine(), productId);
        myService.updateProductQuantity(quantity, productId);

        orderService.addOrder(OrderDto.builder()
            .productDto(ProductDto.builder()
                .name(myService.findProductByIdWithErrorCheck(productId).getName())
                .build())
            .quantity(quantity)
            .discount(discount)
            .date(LocalDate.now())
            .paymentDto(PaymentDto.builder()
                .id(paymentId)
                .build())
            .customerDto(CustomerDto.builder()
                .name(myService.findCustomerByIdWithErrorCheck(customerId).getName())
                .build())
            .build()
        );

        return State.ORDER;
    }
}
