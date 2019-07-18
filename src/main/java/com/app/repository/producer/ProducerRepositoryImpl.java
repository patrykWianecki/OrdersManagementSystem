package com.app.repository.producer;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Producer;
import com.app.repository.generic.AbstractGenericRepository;

public class ProducerRepositoryImpl extends AbstractGenericRepository<Producer> implements ProducerRepository {

    @Override
    public Optional<Producer> findByName(String name) {
        Optional<Producer> o;

        EntityManager entityManager;
        EntityTransaction transaction;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select p from Producer p where p.name = :name");
            query.setParameter("name", name);
            o = Optional.of((Producer) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find producer by name");
        }

        return o;
    }

    @Override
    public boolean checkIfProducerWithNameTradeAndCountryExists(final String name, final Long tradeId, final Long countryId) {
        Producer p;

        EntityManager entityManager;
        EntityTransaction transaction;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager
                .createQuery("select p from Producer p where p.name = :name and p.trade.id = :tradeId and p.country.id = :countryId");
            query.setParameter("name", name);
            query.setParameter("tradeId", tradeId);
            query.setParameter("countryId", countryId);
            p = (Producer) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find producer by name, tradeId and countryId");
        }

        return p != null;
    }
}
