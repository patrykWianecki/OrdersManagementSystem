package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "guaranteeComponents")
public class GuaranteeComponent implements Serializable {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    @Id
    @GeneratedValue
    private Product product;

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
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}", product);
    }
}
