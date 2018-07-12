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
 * Payment is a class which entity for Payment table in database.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

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
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private EPayment payment;

    // EPayment
    @ElementCollection
    @CollectionTable(name = "epayment", joinColumns = @JoinColumn(name = "paymentId"))
    @Column(name = "epayment")
    @Enumerated(EnumType.STRING)
    private Set<EPayment> ePayments = new HashSet<>();

    // CUSTOMER_ORDER
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "payment", fetch = FetchType.EAGER)
    private Set<CustomerOrder> customerOrders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment1 = (Payment) o;
        return Objects.equals(id, payment1.id) &&
                Objects.equals(payment, payment1.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payment);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", payment=" + payment +
                '}';
    }
}
