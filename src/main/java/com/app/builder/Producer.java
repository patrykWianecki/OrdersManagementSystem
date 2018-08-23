package com.app.builder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Producer {
    private List<String> producers = new ArrayList<>();

    private Producer(ProducerBuilder builder) {
        this.producers = builder.producers;
    }

    public static ProducerBuilder builder() {
        return new ProducerBuilder();
    }

    public String producer() {
        StringBuilder sb = new StringBuilder(producers.get(0) + "( ");
        sb.append(producers.subList(1, producers.size()).stream().collect(Collectors.joining((", "))));
        sb.append(" )");
        return sb.toString().toLowerCase();
    }

    public static class ProducerBuilder {
        private List<String> producers = new ArrayList<>();

        public ProducerBuilder id(Long id) {
            producers.add(0, MessageFormat.format("{0}", id));
            return this;
        }

        public ProducerBuilder name(Long name) {
            producers.add(1, MessageFormat.format("{0}", name));
            return this;
        }

        public ProducerBuilder countryId(Long countryId) {
            producers.add(2, MessageFormat.format("{0}", countryId));
            return this;
        }

        public ProducerBuilder tradeId(Long tradeId) {
            producers.add(3, MessageFormat.format("{0}", tradeId));
            return this;
        }

        public Producer build() {
            return new Producer(this);
        }
    }
}
