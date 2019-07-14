package com.app.repository.customer;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Customer;
import com.app.repository.generic.AbstractGenericRepository;

public class CustomerRepositoryImpl extends AbstractGenericRepository<Customer> implements CustomerRepository {

    @Override
    public Optional<Customer> findByNameAndSurname(String name, String surname) {
        Optional<Customer> o;

        EntityManager entityManager;
        EntityTransaction transaction;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select c from Customer c where c.name = :name and c.surname = :surname");
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            o = Optional.of((Customer) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find customer by name and surname");
        }

        return o;
    }

    @Override
    public Optional<Customer> findByNameAndSurnameAndCountry(String name, String surname, Long countryId) {
        Optional<Customer> o;

        EntityManager entityManager;
        EntityTransaction transaction;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager
                .createQuery("select c from Customer c where c.name = :name and c.surname = :surname and c.country.id = :countryId");
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            query.setParameter("country", countryId);
            o = Optional.of((Customer) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find customer by name, surname and country");
        }

        return o;
    }
}
