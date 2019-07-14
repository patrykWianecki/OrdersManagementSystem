package com.app.repository.generic;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;

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
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to add or update");
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
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to delete");
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
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find one");
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
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find all");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return items;
    }
}
