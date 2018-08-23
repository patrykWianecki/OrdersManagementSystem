package com.app.builder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Trade {
    private List<String> trades = new ArrayList<>();

    private Trade(TradeBuilder builder) {
        this.trades = builder.trades;
    }

    public static TradeBuilder builder() {
        return new TradeBuilder();
    }

    public String trade() {
        StringBuilder sb = new StringBuilder(trades.get(0) + "( ");
        sb.append(trades.subList(1, trades.size()).stream().collect(Collectors.joining((", "))));
        sb.append(" )");
        return sb.toString().toLowerCase();
    }

    public static class TradeBuilder {
        private List<String> trades = new ArrayList<>();

        public TradeBuilder id(Long id) {
            trades.add(0, MessageFormat.format("{0}", id));
            return this;
        }

        public TradeBuilder name(Long name) {
            trades.add(1, MessageFormat.format("{0}", name));
            return this;
        }

        public Trade build() {
            return new Trade(this);
        }
    }
}
