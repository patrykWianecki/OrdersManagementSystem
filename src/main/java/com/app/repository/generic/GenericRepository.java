package com.app.repository.generic;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {

    void addOrUpdate(T t);

    void delete(Long id);

    Optional<T> findOne(Long id);

    List<T> findAll();
}
