package com.app.repository.payment;

import com.app.model.Payment;
import com.app.repository.generic.GenericRepository;

import java.util.Optional;

public interface PaymentRepository extends GenericRepository<Payment> {
    Optional<Payment> findByName(Payment payment);
}
