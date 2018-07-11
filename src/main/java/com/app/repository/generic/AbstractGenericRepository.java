package com.app.repository.generic;

import com.app.model.EMessage;
import com.app.model.Errors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AbstractGenericRepository<T> implements GenericRepository<T> {

    private EntityManagerFactory entityManagerFactory = DbConnection.getInstance().getEntityManagerFactory();
    private Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    @Override
    public void addOrUpdate(T t) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(t);
            transaction.commit();
        } catch (Exception e) {
            throw new Errors(EMessage.ADD_OR_UPDATE + " " + e.getMessage(), LocalDate.now());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            T t = entityManager.find(entityClass, id);
            entityManager.remove(t);
            transaction.commit();
        } catch (Exception e) {
            throw new Errors(EMessage.DELETE + " " + e.getMessage(), LocalDate.now());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Optional<T> findOne(Long id) {
        Optional<T> o = Optional.empty();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            o = Optional.of(entityManager.find(entityClass, id));
            transaction.commit();
        } catch (Exception e) {
            throw new Errors(EMessage.FIND_ONE + " " + e.getMessage(), LocalDate.now());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return o;
    }

    @Override
    public List<T> findAll() {
        List<T> items = null;
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select t from " + entityClass.getCanonicalName() + " t");
            items = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            throw new Errors(EMessage.FIND_ALL + " " + e.getMessage(), LocalDate.now());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return items;
    }
}
