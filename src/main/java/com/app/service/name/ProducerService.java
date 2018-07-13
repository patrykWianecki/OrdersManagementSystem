package com.app.service.name;

public interface ProducerService {
    String setProducerName(String name);

    void printAllProducers();

    Long OneProducerNameAndTradeFromOneCountry(String producerName, Long tradeId, Long countryId);
}
