package com.app.repository.payment;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Payment;
import com.app.repository.generic.AbstractGenericRepository;

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
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find payment by name");
        }

        return o;
    }
}
