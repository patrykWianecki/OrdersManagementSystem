package com.app.parser.implementation;

import com.app.model.Country;
import com.app.model.Customer;
import com.app.parser.interfaces.Parser;
import com.app.parser.interfaces.RegularExpressions;

public class CustomerParser implements Parser<Customer> {

    @Override
    public Customer parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.CUSTOMER_REGEX)) {
            return null;
        }
        String[] customer = line.split(";");
        return Customer.builder()
            .id(Long.valueOf(customer[0]))
            .age(Integer.valueOf(customer[1]))
            .name(customer[2])
            .surname(customer[3])
            .country(Country.builder().name(customer[4]).build())
            .build();
    }
}
