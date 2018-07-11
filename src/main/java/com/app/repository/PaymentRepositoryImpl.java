package com.app.repository;

import com.app.model.EMessage;
import com.app.model.Errors;
import com.app.model.Payment;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Optional;

public class PaymentRepositoryImpl extends AbstractGenericRepository<Payment> implements PaymentRepository {
    @Override
    public Optional<Payment> findByName(Payment payment) {
        Optional<Payment> o = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select p from Payment p where p.payment = :payment");
            query.setParameter("payment", payment);
            o = Optional.of((Payment) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new Errors(PaymentRepository.class + " " + EMessage.FIND_BY_NAME + e.getMessage(), LocalDate.now());
        }

        return o;
    }
}
