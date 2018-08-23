package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Producer is a class which entity for Producer table in database.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "producers")
public class Producer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    // countryId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "country_id")
    private Country country;

    // tradeId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "trade_id")
    private Trade trade;

    // PRODUCT
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "producer", fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(id, producer.id) &&
                Objects.equals(name, producer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}", name);
    }
}
