package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * CustomerOrder is a class which entity for CustomerOrder table in database.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private BigDecimal discount;
    private Integer quantity;

    // customerId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customerId")
    private Customer customer;

    // paymentId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "paymentId")
    private Payment payment;

    // productId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "productId")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder that = (CustomerOrder) o;
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
        return "CustomerOrder{" +
                "id=" + id +
                ", date=" + date +
                ", discount=" + discount +
                ", quantity=" + quantity +
                '}';
    }
}
