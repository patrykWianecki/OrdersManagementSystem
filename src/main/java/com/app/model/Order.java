package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private BigDecimal discount;
    private Integer quantity;

    // CUSTOMER
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // PAYMENT
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    // PRODUCT
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(date, that.date) &&
            Objects.equals(discount, that.discount) &&
            Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, discount, quantity);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} {1} {2}", date, discount, quantity);
    }
}
