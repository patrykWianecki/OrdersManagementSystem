package com.app.service;

import java.util.Comparator;

import com.app.model.Payment;
import com.app.repository.payment.PaymentRepository;
import com.app.repository.payment.PaymentRepositoryImpl;

public class PaymentService {

    private PaymentRepository paymentRepository = new PaymentRepositoryImpl();

    public void printAllPayments() {
        paymentRepository.findAll()
            .stream()
            .sorted(Comparator.comparing(Payment::getId))
            .forEach(payment -> System.out.println(payment.getId() + ". " + payment.getEPayments()));
    }
}
