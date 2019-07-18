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
        String[] order = line.split(";");
        return Order.builder()
            .id(Long.parseLong(order[0]))
            .date(LocalDate.parse(order[1]))
            .discount(new BigDecimal(order[2]))
            .quantity(Integer.valueOf(order[3]))
            .customer(Customer.builder().name(order[4]).build())
            .product(Product.builder().name(order[6]).build())
            .build();
    }
}
