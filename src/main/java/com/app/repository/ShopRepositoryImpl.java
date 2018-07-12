package com.app.repository;

import com.app.model.EMessage;
import com.app.model.Errors;
import com.app.model.Shop;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Optional;

public class ShopRepositoryImpl extends AbstractGenericRepository<Shop> implements ShopRepository {
    @Override
    public Optional<Shop> findByName(String name) {
        Optional<Shop> o = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("select s from Shop s where s.name = :name");
            query.setParameter("name", name);
            o = Optional.of((Shop) query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            throw new Errors(ShopRepository.class + " " + EMessage.FIND_BY_NAME + " " + e.getMessage(), LocalDate.now());
        }

        return o;
    }
}
