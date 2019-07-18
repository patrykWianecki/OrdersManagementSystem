package com.app.repository.trade;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Trade;
import com.app.repository.generic.AbstractGenericRepository;

public class TradeRepositoryImpl extends AbstractGenericRepository<Trade> implements TradeRepository {

    @Override
    public Optional<Trade> findByName(String name) {
        Optional<Trade> o;

        EntityManager entityManager;
        EntityTransaction transaction;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select t from Trade t where t.name = :name");
            query.setParameter("name", name);
            o = Optional.of((Trade) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find trade by name");
        }

        return o;
    }
}
