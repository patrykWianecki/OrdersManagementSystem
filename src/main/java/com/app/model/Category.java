package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;

    // PRODUCT
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    public void setName(String name) {
        if (!name.equals(name.toUpperCase())) {
            this.name = null;
        } else {
            this.name = name;
        }
    }
}
