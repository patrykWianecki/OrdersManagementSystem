package com.app.repository.product;

import com.app.model.Product;
import com.app.repository.generic.GenericRepository;

import java.util.Optional;

public interface ProductRepository extends GenericRepository<Product> {

    Optional<Product> findByName(String name);

    boolean checkIfProductWithNameCategoryAndProducerExists(String name, Long categoryId, Long producerId);
}
