package com.app.repository.category;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Category;
import com.app.repository.generic.AbstractGenericRepository;

public class CategoryRepositoryImpl extends AbstractGenericRepository<Category> implements CategoryRepository {

    @Override
    public Optional<Category> findByName(String name) {
        Optional<Category> o;

        EntityManager entityManager;
        EntityTransaction transaction;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select c from Category c where c.name = :name");
            query.setParameter("name", name);
            o = Optional.of((Category) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find category by name");
        }

        return o;
    }
}
