package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "ePayments", joinColumns = @JoinColumn(name = "payment_id"))
    @Column(name = "ePayments")
    @Enumerated(EnumType.STRING)
    private Set<EPayment> ePayments = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "payment", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment1 = (Payment) o;
        return Objects.equals(id, payment1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
