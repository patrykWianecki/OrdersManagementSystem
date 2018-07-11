package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Stock is a class which entity for Stock table in database.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    private Integer quantity;

    // prodcutId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "productId")
    private Product product;

    // shopId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "shopId")
    private Shop shop;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id) &&
                Objects.equals(quantity, stock.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
