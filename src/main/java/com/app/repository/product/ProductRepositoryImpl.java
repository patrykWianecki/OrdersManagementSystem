package com.app.repository.product;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Product;
import com.app.repository.generic.AbstractGenericRepository;

public class ProductRepositoryImpl extends AbstractGenericRepository<Product> implements ProductRepository {

    @Override
    public Optional<Product> findByName(String name) {
        Optional<Product> o;

        EntityManager entityManager;
        EntityTransaction transaction;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select p from Product p where p.name = :name");
            query.setParameter("name", name);
            o = Optional.of((Product) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find product by name");
        }

        return o;
    }

    @Override
    public boolean checkIfProductWithNameCategoryAndProducerExists(final String name, final Long categoryId, final Long producerId) {
        Product p;

        EntityManager entityManager;
        EntityTransaction transaction;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery(
                "select p from Product p where p.name = :name and p.category.id = :categoryId and p.producer.id = :producerId");
            query.setParameter("name", name);
            query.setParameter("categoryId", categoryId);
            query.setParameter("producerId", producerId);
            p = (Product) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find product by name, categoryId and producerId");
        }

        return p != null;
    }
}
