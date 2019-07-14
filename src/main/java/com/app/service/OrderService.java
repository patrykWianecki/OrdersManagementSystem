package com.app.service;

import com.app.model.dto.OrderDto;
import com.app.model.dto.MyMapper;
import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.Payment;
import com.app.model.Product;
import com.app.repository.customer.CustomerRepository;
import com.app.repository.customer.CustomerRepositoryImpl;
import com.app.repository.customerorder.CustomerOrderRepository;
import com.app.repository.customerorder.CustomerOrderRepositoryImpl;
import com.app.repository.payment.PaymentRepository;
import com.app.repository.payment.PaymentRepositoryImpl;
import com.app.repository.product.ProductRepository;
import com.app.repository.product.ProductRepositoryImpl;

public class OrderService {

    private MyMapper mapper = new MyMapper();
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private PaymentRepository paymentRepository = new PaymentRepositoryImpl();
    private ProductRepository productRepository = new ProductRepositoryImpl();
    private CustomerOrderRepository customerOrderRepository = new CustomerOrderRepositoryImpl();

    public void addCustomerOrder(OrderDto customerOrderDto) {
        try {
            String customerName = customerOrderDto.getCustomerDto().getName();
            String customerSurname = customerOrderDto.getCustomerDto().getSurname();
            Long paymentId = customerOrderDto.getPaymentDto().getId();
            String productName = customerOrderDto.getProductDto().getName();

            Customer customer
                = customerRepository
                .findByNameAndSurname(customerName, customerSurname)
                .orElseThrow(
                    () -> new MyException(ExceptionCode.SERVICE, "Missing customer called " + customerName + " " + customerSurname));

            Payment payment
                = paymentRepository
                .findOne(paymentId)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing payment with id " + paymentId));

            Product product = productRepository
                .findByName(productName)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing product with name " + productName));

            Order customerOrder = mapper.fromCustomerOrderDtoToCustomerOrder(customerOrderDto);
            customerOrder.setCustomer(customer);
            customerOrder.setProduct(product);
            customerOrder.setPayment(payment);
            customerOrderRepository.addOrUpdate(customerOrder);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "Add customer order exception");
        }
    }
}
