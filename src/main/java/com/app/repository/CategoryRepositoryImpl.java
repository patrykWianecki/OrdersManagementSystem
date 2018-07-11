package com.app.repository;

import com.app.model.Category;
import com.app.model.EMessage;
import com.app.model.Errors;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Optional;

public class CategoryRepositoryImpl extends AbstractGenericRepository<Category> implements CategoryRepository {
    @Override
    public Optional<Category> findByName(String name) {
        Optional<Category> o = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select c from Category c where c.name = :name");
            query.setParameter("name", name);
            o = Optional.of((Category) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new Errors(CategoryRepository.class + " " + EMessage.FIND_BY_NAME + " " + e.getMessage(), LocalDate.now());
        }

        return o;
    }
}
