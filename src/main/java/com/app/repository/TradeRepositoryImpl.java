package com.app.repository;

import com.app.model.EMessage;
import com.app.model.Errors;
import com.app.model.Trade;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Optional;

public class TradeRepositoryImpl extends AbstractGenericRepository<Trade> implements TradeRepository {
    @Override
    public Optional<Trade> findByName(String name) {
        Optional<Trade> o = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select t from Trade t where t.name = :name");
            query.setParameter("name", name);
            o = Optional.of((Trade) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new Errors(TradeRepository.class + " " + EMessage.FIND_BY_NAME + e.getMessage(), LocalDate.now());
        }

        return o;
    }
}
