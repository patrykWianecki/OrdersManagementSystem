package com.app.parsers.implementations;

import com.app.model.*;
import com.app.parsers.interfaces.Parser;
import com.app.parsers.interfaces.RegularExpressions;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerOrderParser implements Parser<CustomerOrder> {
    @Override
    public CustomerOrder parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.CUSTOMER_ORDER_REGEX)) {
            return null;
        }
        String[] customerOrder = line.split(";");
        return CustomerOrder.builder()
                .id(Long.parseLong(customerOrder[0]))
                .date(LocalDate.parse(customerOrder[1]))
                .discount(new BigDecimal(customerOrder[2]))
                .quantity(Integer.valueOf(customerOrder[3]))
                .customer(Customer.builder().name(customerOrder[4]).build())
                .payment(Payment.builder().payment(EPayment.returnPayment(Integer.parseInt(customerOrder[5]))).build())
                .product(Product.builder().name(customerOrder[6]).build())
                .build();
    }
}
