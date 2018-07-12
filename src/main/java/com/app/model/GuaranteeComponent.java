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
public class GuaranteeComponent {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private EGuarantee guaranteeComponent;

    // productId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "productId")
    private Product product;

    @ElementCollection
    @CollectionTable(name = "guaranteeComponent", joinColumns = @JoinColumn(name = "eguarantee"))
    @Column(name = "guaranteeComponent")
    @Enumerated(EnumType.STRING)
    private Set<EGuarantee> eGuarantees = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuaranteeComponent that = (GuaranteeComponent) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(guaranteeComponent, that.guaranteeComponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, guaranteeComponent);
    }

    @Override
    public String toString() {
        return "GuaranteeComponent{" +
                "id=" + id +
                ", guaranteeComponent=" + guaranteeComponent +
                '}';
    }
}
