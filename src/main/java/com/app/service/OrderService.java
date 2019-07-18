package com.app.service;

import com.app.model.dto.OrderDto;
import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.Payment;
import com.app.model.Product;
import com.app.repository.customer.CustomerRepository;
import com.app.repository.customer.CustomerRepositoryImpl;
import com.app.repository.order.OrderRepository;
import com.app.repository.order.OrderRepositoryImpl;
import com.app.repository.payment.PaymentRepository;
import com.app.repository.payment.PaymentRepositoryImpl;
import com.app.repository.product.ProductRepository;
import com.app.repository.product.ProductRepositoryImpl;

import static com.app.model.dto.MyMapper.*;

public class OrderService {

    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private PaymentRepository paymentRepository = new PaymentRepositoryImpl();
    private ProductRepository productRepository = new ProductRepositoryImpl();
    private OrderRepository orderRepository = new OrderRepositoryImpl();

    public void addOrder(OrderDto orderDto) {
        try {
            String customerName = orderDto.getCustomerDto().getName();
            String customerSurname = orderDto.getCustomerDto().getSurname();
            Long paymentId = orderDto.getPaymentDto().getId();
            String productName = orderDto.getProductDto().getName();

            Customer customer = customerRepository.findByNameAndSurname(customerName, customerSurname)
                .orElseThrow(
                    () -> new MyException(ExceptionCode.SERVICE, "Missing customer called " + customerName + " " + customerSurname));

            Payment payment = paymentRepository.findOne(paymentId)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing payment with id " + paymentId));

            Product product = productRepository.findByName(productName)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing product with name " + productName));

            Order order = fromOrderDtoToOrder(orderDto);
            order.setCustomer(customer);
            order.setProduct(product);
            order.setPayment(payment);
            orderRepository.addOrUpdate(order);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "Add customer order exception");
        }
    }
}
