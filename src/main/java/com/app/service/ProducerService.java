package com.app.service;

import java.util.Comparator;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Country;
import com.app.model.Producer;
import com.app.model.Trade;
import com.app.model.dto.ProducerDto;
import com.app.repository.country.CountryRepository;
import com.app.repository.country.CountryRepositoryImpl;
import com.app.repository.producer.ProducerRepository;
import com.app.repository.producer.ProducerRepositoryImpl;
import com.app.repository.trade.TradeRepository;
import com.app.repository.trade.TradeRepositoryImpl;

import static com.app.model.dto.MyMapper.*;

public class ProducerService {

    private CountryRepository countryRepository = new CountryRepositoryImpl();
    private TradeRepository tradeRepository = new TradeRepositoryImpl();
    private ProducerRepository producerRepository = new ProducerRepositoryImpl();

    public void addProducer(ProducerDto producerDto) {
        try {
            String countryName = producerDto.getCountryDto().getName();
            String tradeName = producerDto.getTradeDto().getName();

            Country country = countryRepository.findByName(countryName)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing country with name " + countryName));

            Trade trade = tradeRepository.findByName(tradeName)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, " Missing country with name " + tradeName));

            Producer producer = fromProducerDtoToProducer(producerDto);
            producer.setCountry(country);
            producer.setTrade(trade);

            producerRepository.addOrUpdate(producer);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, " Country " + producerDto.getCountryDto().getName());
        }
    }

    public void printAllProducers() {
        producerRepository.findAll()
            .stream()
            .sorted(Comparator.comparing(Producer::getId))
            .forEach(producer -> System.out.println(producer.getId() + ". " + producer.getName()));
    }
}
