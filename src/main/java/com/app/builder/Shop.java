package com.app.builder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Shop {
    private List<String> shops = new ArrayList<>();

    private Shop(ShopBuilder builder) {
        this.shops = builder.shops;
    }

    public static ShopBuilder builder() {
        return new ShopBuilder();
    }

    public String shop() {
        StringBuilder sb = new StringBuilder(shops.get(0) + "( ");
        sb.append(shops.subList(1, shops.size()).stream().collect(Collectors.joining((", "))));
        sb.append(" )");
        return sb.toString().toLowerCase();
    }

    public static class ShopBuilder {
        private List<String> shops = new ArrayList<>();

        public ShopBuilder id(Long id) {
            shops.add(0, MessageFormat.format("{0}", id));
            return this;
        }

        public ShopBuilder name(String name) {
            shops.add(1, MessageFormat.format("{0}", name));
            return this;
        }

        public ShopBuilder countryId(Long countryId) {
            shops.add(2, MessageFormat.format("{0}", countryId));
            return this;
        }

        public Shop build() {
            return new Shop(this);
        }
    }
}
