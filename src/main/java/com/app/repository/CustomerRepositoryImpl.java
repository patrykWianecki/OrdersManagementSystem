package com.app.repository;

import com.app.model.Customer;
import com.app.model.EMessage;
import com.app.model.Errors;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Optional;

public class CustomerRepositoryImpl extends AbstractGenericRepository<Customer> implements CustomerRepository {
    @Override
    public Optional<Customer> findByNameAndSurname(String name, String surname) {
        Optional<Customer> o = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
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
            throw new Errors(CustomerRepository.class + " " + EMessage.FIND_BY_NAME_AND_SURNAME + e.getMessage(), LocalDate.now());
        }

        return o;
    }
}
