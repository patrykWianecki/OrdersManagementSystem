package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * GuaranteeComponent is a class which entity for GuaranteeComponent table in database.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "guaranteeComponents")
public class GuaranteeComponent implements Serializable {
    // productId
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    @Id
    @GeneratedValue
    private Product product;
    private EGuarantee guaranteeComponent;

    @ElementCollection
    @CollectionTable(name = "eGuarantees", joinColumns = @JoinColumn(name = "guarantee_component"))
    @Column(name = "eGuarantees")
    @Enumerated(EnumType.STRING)
    private Set<EGuarantee> eGuarantees = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuaranteeComponent that = (GuaranteeComponent) o;
        return Objects.equals(product, that.product) &&
                guaranteeComponent == that.guaranteeComponent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, guaranteeComponent);
    }

    @Override
    public String toString() {
        return "GuaranteeComponent{" +
                "product=" + product +
                ", guaranteeComponent=" + guaranteeComponent +
                '}';
    }
}
