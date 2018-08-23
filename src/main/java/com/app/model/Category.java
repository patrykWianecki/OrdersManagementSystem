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
 * Category is a class which entity for Category table in database.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

    // PRODUCT
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name);
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
