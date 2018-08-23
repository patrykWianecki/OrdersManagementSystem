package com.app.builder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stock {
    private List<String> stocks = new ArrayList<>();

    private Stock(StockBuilder builder) {
        this.stocks = builder.stocks;
    }

    public static StockBuilder builder() {
        return new StockBuilder();
    }

    public String stock() {
        StringBuilder sb = new StringBuilder(stocks.get(0) + "( ");
        sb.append(stocks.subList(1, stocks.size()).stream().collect(Collectors.joining((", "))));
        sb.append(" )");
        return sb.toString().toLowerCase();
    }

    public static class StockBuilder {
        private List<String> stocks = new ArrayList<>();

        public StockBuilder id(Long id) {
            stocks.add(0, MessageFormat.format("{0}", id));
            return this;
        }

        public StockBuilder quantity(Integer quantity) {
            stocks.add(1, MessageFormat.format("{0}", quantity));
            return this;
        }

        public StockBuilder productId(Long productId) {
            stocks.add(2, MessageFormat.format("{0}", productId));
            return this;
        }

        public StockBuilder shopId(Long shopId) {
            stocks.add(3, MessageFormat.format("{0}", shopId));
            return this;
        }

        public Stock build() {
            return new Stock(this);
        }
    }
}
