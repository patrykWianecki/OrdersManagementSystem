package com.app.repository;

import com.app.model.EMessage;
import com.app.model.Errors;
import com.app.model.Producer;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Optional;

public class ProducerRepositoryImpl extends AbstractGenericRepository<Producer> implements ProducerRepository {
    @Override
    public Optional<Producer> findByName(String name) {
        Optional<Producer> o = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select p from Producer p where p.name = :name");
            query.setParameter("name", name);
            o = Optional.of((Producer) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new Errors(ProducerRepository.class + " " + EMessage.FIND_BY_NAME + e.getMessage(), LocalDate.now());
        }

        return o;
    }
}
