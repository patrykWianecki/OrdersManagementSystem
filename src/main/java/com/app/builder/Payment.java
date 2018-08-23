package com.app.builder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


// DODAC BUIILDERY! ------------------------------------------------------------------------------------------------------------------------------------


public class Payment {
    private List<String> payments = new ArrayList<>();

    private Payment(PaymentBuilder builder) {
        this.payments = builder.payments;
    }

    public static PaymentBuilder builder() {
        return new PaymentBuilder();
    }

    public String payment() {
        StringBuilder sb = new StringBuilder(payments.get(0) + "( ");
        sb.append(payments.subList(1, payments.size()).stream().collect(Collectors.joining((", "))));
        sb.append(" )");
        return sb.toString().toLowerCase();
    }

    public static class PaymentBuilder {
        private List<String> payments = new ArrayList<>();

        public PaymentBuilder addTable(String tableName) {
            if (!payments.isEmpty()) {
                return this;
            }
            payments.add(0, MessageFormat.format("create tabel if not exists {0}", tableName));
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
