package com.app.repository;

import com.app.model.EMessage;
import com.app.model.Errors;
import com.app.model.Product;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Optional;

public class ProductRepositoryImpl extends AbstractGenericRepository<Product> implements ProductRepository {
    @Override
    public Optional<Product> findByName(String name) {
        Optional<Product> o = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select p from Product p where p.name = :name");
            query.setParameter("name", name);
            o = Optional.of((Product) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new Errors(ProductRepository.class + " " + EMessage.FIND_BY_NAME + e.getMessage(), LocalDate.now());
        }

        return o;
    }
}
