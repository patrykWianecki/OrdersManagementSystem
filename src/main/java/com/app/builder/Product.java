package com.app.builder;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Product {
    private List<String> products = new ArrayList<>();

    private Product(ProductBuilder builder) {
        this.products = builder.products;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public String product() {
        StringBuilder sb = new StringBuilder(products.get(0) + "( ");
        sb.append(products.subList(1, products.size()).stream().collect(Collectors.joining((", "))));
        sb.append(" )");
        return sb.toString().toLowerCase();
    }

    public static class ProductBuilder {
        private List<String> products = new ArrayList<>();

        public ProductBuilder id(Long id) {
            products.add(0, MessageFormat.format("{0}", id));
            return this;
        }

        public ProductBuilder name(String name) {
            products.add(1, MessageFormat.format("{0}", name));
            return this;
        }

        public ProductBuilder price(BigDecimal price) {
            products.add(2, MessageFormat.format("{0}", price));
            return this;
        }

        public ProductBuilder categoryId(Long categoryId) {
            products.add(3, MessageFormat.format("{0}", categoryId));
            return this;
        }

        public ProductBuilder producerId(Long producerId) {
            products.add(4, MessageFormat.format("{0}", producerId));
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
