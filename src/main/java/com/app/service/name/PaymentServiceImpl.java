package com.app.service.name;

import com.app.model.Payment;
import com.app.repository.PaymentRepository;
import com.app.repository.PaymentRepositoryImpl;

import java.util.Comparator;

public class PaymentServiceImpl implements PaymentService {
    private static PaymentRepository paymentRepository = new PaymentRepositoryImpl();

    @Override
    public void printAllPayments() {
        paymentRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Payment::getId))
                .forEach(x -> System.out.println(x.getId() + ". " + x.getPayment()));
    }
}
