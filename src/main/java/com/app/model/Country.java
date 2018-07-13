package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Country is a class which entity for Country table in database.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
