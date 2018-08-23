package com.app.builder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Country {
    private List<String> countries = new ArrayList<>();

    private Country(CountryBuilder builder) {
        this.countries = builder.countries;
    }

    public static CountryBuilder builder() {
        return new Country.CountryBuilder();
    }

    public String country() {
        StringBuilder sb = new StringBuilder(countries.get(0) + "( ");
        sb.append(countries.subList(1, countries.size()).stream().collect(Collectors.joining((", "))));
        sb.append(" )");
        return sb.toString().toLowerCase();
    }

    public static class CountryBuilder {
        private List<String> countries = new ArrayList<>();

        public CountryBuilder id(Long id) {
            countries.add(0, MessageFormat.format("{0}", id));
            return this;
        }

        public CountryBuilder name(String name) {
            countries.add(1, MessageFormat.format("{0}", name));
            return this;
        }

        public Country build() {
            return new Country(this);
        }
    }
}
