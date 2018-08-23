package com.app.builder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Category {
    private List<String> categories = new ArrayList<>();

    private Category(CategoryBuilder builder) {
        this.categories = builder.categories;
    }

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }

    public String category() {
        StringBuilder sb = new StringBuilder(categories.get(0) + "( ");
        sb.append(categories.subList(1, categories.size()).stream().collect(Collectors.joining((", "))));
        sb.append(" )");
        return sb.toString().toLowerCase();
    }

    public static class CategoryBuilder {
        private List<String> categories = new ArrayList<>();

        public CategoryBuilder id(Long id) {
            categories.add(0, MessageFormat.format("{0}", id));
            return this;
        }

        public CategoryBuilder name(String name) {
            categories.add(1, MessageFormat.format("{0}", name));
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
