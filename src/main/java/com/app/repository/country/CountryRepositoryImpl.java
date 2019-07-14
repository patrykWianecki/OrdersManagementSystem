package com.app.repository.country;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Country;
import com.app.repository.generic.AbstractGenericRepository;

public class CountryRepositoryImpl extends AbstractGenericRepository<Country> implements CountryRepository {

    @Override
    public Optional<Country> findByName(String name) {
        Optional<Country> o;

        EntityManager entityManager;
        EntityTransaction transaction;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select c from Country c where c.name = :name");
            query.setParameter("name", name);
            o = Optional.of((Country) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find country by name");
        }

        return o;
    }
}
