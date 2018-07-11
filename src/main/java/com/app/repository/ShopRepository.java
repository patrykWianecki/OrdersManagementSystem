package com.app.repository;

import com.app.model.Shop;
import com.app.repository.generic.GenericRepository;

import java.util.Optional;

public interface ShopRepository extends GenericRepository<Shop> {
    Optional<Shop> findByName(String name);
}
