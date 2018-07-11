package com.app.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Trade is a class which entity for Trade table in database.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Trade {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

    // PRODUCER
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "trade", fetch = FetchType.EAGER)
    private Set<Producer> producers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Objects.equals(id, trade.id) &&
                Objects.equals(name, trade.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
