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
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

    // CUSTOMER
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "country", fetch = FetchType.EAGER)
    private Set<Customer> customers = new HashSet<>();

    // PRODUCER
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "country", fetch = FetchType.EAGER)
    private Set<Producer> producers = new HashSet<>();

    // SHOP
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "country", fetch = FetchType.EAGER)
    private Set<Shop> shops = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id) &&
            Objects.equals(name, country.name);
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
