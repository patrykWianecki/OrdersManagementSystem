package com.app.builder;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerOrder {
    private List<String> orders = new ArrayList<>();

    private CustomerOrder(CustomerOrderBuilder builder) {
        this.orders = builder.orders;
    }

    public static CustomerOrder.CustomerOrderBuilder builder() {
        return new CustomerOrder.CustomerOrderBuilder();
    }

    public String order() {
        StringBuilder sb = new StringBuilder(orders.get(0) + "( ");
        sb.append(orders.subList(1, orders.size()).stream().collect(Collectors.joining((", "))));
        sb.append(" )");
        return sb.toString().toLowerCase();
    }

    public static class CustomerOrderBuilder {
        private List<String> orders = new ArrayList<>();

        public CustomerOrderBuilder name(Long id) {
            orders.add(0, MessageFormat.format("{0}", id));
            return this;
        }

        public CustomerOrderBuilder date(LocalDate date) {
            orders.add(1, MessageFormat.format("{0}", date));
            return this;
        }

        public CustomerOrderBuilder discount(BigDecimal discount) {
            orders.add(2, MessageFormat.format("{0}", discount));
            return this;
        }

        public CustomerOrderBuilder quantity(Integer quantity) {
            orders.add(3, MessageFormat.format("{0}", quantity));
            return this;
        }

        public CustomerOrderBuilder customerId(Long customerId) {
            orders.add(4, MessageFormat.format("{0}", customerId));
            return this;
        }

        public CustomerOrderBuilder paymentId(Long paymentId) {
            orders.add(5, MessageFormat.format("{0}", paymentId));
            return this;
        }

        public CustomerOrderBuilder productId(Long productId) {
            orders.add(6, MessageFormat.format("{0}", productId));
            return this;
        }

        public CustomerOrder build() {
            return new CustomerOrder(this);
        }
    }
}
