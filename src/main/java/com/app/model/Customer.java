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
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private Integer age;
    private String name;
    private String surname;

    // COUNTRY
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "country_id")
    private Country country;

    // ORDER
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
            Objects.equals(age, customer.age) &&
            Objects.equals(name, customer.name) &&
            Objects.equals(surname, customer.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name, surname);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} {1} {2}", age, name, surname);
    }
}
