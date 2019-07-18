package com.app.repository.producer;

import com.app.model.Producer;
import com.app.repository.generic.GenericRepository;

import java.util.Optional;

public interface ProducerRepository extends GenericRepository<Producer> {

    Optional<Producer> findByName(String name);

    boolean checkIfProducerWithNameTradeAndCountryExists(String name, Long tradeId, Long countryId);
}
