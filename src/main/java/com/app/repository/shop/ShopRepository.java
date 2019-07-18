package com.app.repository.shop;

import com.app.model.Shop;
import com.app.repository.generic.GenericRepository;

import java.util.Optional;

public interface ShopRepository extends GenericRepository<Shop> {

    Optional<Shop> findByName(String name);

    boolean checkIfShopWithNameAndCountryExists(String name, Long countryId);
}
