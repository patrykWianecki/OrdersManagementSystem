package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "product", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "product", fetch = FetchType.EAGER)
    private Set<Stock> stocks = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "product", fetch = FetchType.EAGER)
    private Set<GuaranteeComponent> guaranteeComponents = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
            Objects.equals(name, product.name) &&
            Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} {1}", name, price);
    }
}

