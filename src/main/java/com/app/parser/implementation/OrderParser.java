package com.app.parser.implementation;

import com.app.model.*;
import com.app.parser.interfaces.Parser;
import com.app.parser.interfaces.RegularExpressions;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderParser implements Parser<Order> {

    @Override
    public Order parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.ORDER_REGEX)) {
            return null;
        }
        String[] customerOrder = line.split(";");
        return Order.builder()
            .id(Long.parseLong(customerOrder[0]))
            .date(LocalDate.parse(customerOrder[1]))
            .discount(new BigDecimal(customerOrder[2]))
            .quantity(Integer.valueOf(customerOrder[3]))
            .customer(Customer.builder().name(customerOrder[4]).build())
            .product(Product.builder().name(customerOrder[6]).build())
            .build();
    }
}
