package com.app.repository.shop;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Shop;
import com.app.repository.generic.AbstractGenericRepository;

public class ShopRepositoryImpl extends AbstractGenericRepository<Shop> implements ShopRepository {

    @Override
    public Optional<Shop> findByName(String name) {
        Optional<Shop> o;

        EntityManager entityManager;
        EntityTransaction transaction;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select s from Shop s where s.name = :name");
            query.setParameter("name", name);
            o = Optional.of((Shop) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find shop by name");
        }

        return o;
    }

    @Override
    public boolean checkIfShopWithNameAndCountryExists(final String name, final Long countryId) {
        Shop s;

        EntityManager entityManager;
        EntityTransaction transaction;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select s from Shop s where s.name = :name and s.country.id = :countryId");
            query.setParameter("name", name);
            query.setParameter("countryId", countryId);
            s = (Shop) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.REPOSITORY, "Failed to find shop by name and countryId");
        }

        return s != null;
    }
}
