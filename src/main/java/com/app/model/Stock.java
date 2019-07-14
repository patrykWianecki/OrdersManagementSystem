package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.text.MessageFormat;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue
    private Long id;
    private Integer quantity;

    // PRODUCT
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private Product product;

    // SHOP
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "shop_id")
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
        return MessageFormat.format("{0}", quantity);
    }
}
