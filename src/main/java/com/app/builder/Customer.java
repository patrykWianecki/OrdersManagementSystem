package com.app.builder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Customer {
    private List<String> customers = new ArrayList<>();

    private Customer(CustomerBuilder builder) {
        this.customers = builder.customers;
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public String customer() {
        StringBuilder sb = new StringBuilder(customers.get(0) + "( ");
        sb.append(customers.subList(1, customers.size()).stream().collect(Collectors.joining((", "))));
        sb.append(" )");
        return sb.toString().toLowerCase();
    }

    public static class CustomerBuilder {
        private List<String> customers = new ArrayList<>();

        public CustomerBuilder id(Long id) {
            customers.add(0, MessageFormat.format("{0}", id));
            return this;
        }

        public CustomerBuilder name(String name) {
            customers.add(1, MessageFormat.format("{0}", name));
            return this;
        }

        public CustomerBuilder surname(String surname) {
            customers.add(2, MessageFormat.format("{0}", surname));
            return this;
        }

        // jak countryId?
        public CustomerBuilder countryId(Long countryId) {
            customers.add(3, MessageFormat.format("{0}", countryId));
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
