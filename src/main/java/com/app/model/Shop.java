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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    // COUNTRY
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "country_id")
    private Country country;

    // STOCK
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "shop", fetch = FetchType.EAGER)
    private Set<Stock> stocks = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(id, shop.id) &&
            Objects.equals(name, shop.name);
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
