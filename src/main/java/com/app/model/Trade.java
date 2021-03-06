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
@Table(name = "trades")
public class Trade {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

    // PRODUCER
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "trade", fetch = FetchType.EAGER)
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
        return MessageFormat.format("{0}", name);
    }
}
