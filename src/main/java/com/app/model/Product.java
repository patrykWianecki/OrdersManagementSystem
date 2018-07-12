package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Product is a class which entity for Product table in database.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;

    // categoryId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "categoryId")
    private Category category;

    // producerId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "producerId")
    private Producer producer;

    // CUSTOMER_ORDER
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "product", fetch = FetchType.EAGER)
    private Set<CustomerOrder> customerOrders = new HashSet<>();

    // STOCK
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "product", fetch = FetchType.EAGER)
    private Set<Stock> stocks = new HashSet<>();

    // GUARANTEE_COMPONENTS
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "product", fetch = FetchType.EAGER)
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
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
