package com.app.service.name;

import com.app.model.Errors;
import com.app.model.Producer;
import com.app.repository.ProducerRepository;
import com.app.repository.ProducerRepositoryImpl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProducerServiceImpl implements ProducerService {
    private static Scanner scanner = new Scanner(System.in);
    private static ProducerRepository producerRepository = new ProducerRepositoryImpl();

    @Override
    public String setProducerName(String name) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Wrong product name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public void printAllProducers() {
        producerRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Producer::getId))
                .forEach(s -> System.out.println(s.getId() + ". " + s.getName()));
    }

    @Override
    public Long OneProducerNameAndTradeFromOneCountry(String producerName, Long tradeId, Long countryId) throws Errors {
        List<Producer> producers
                = producerRepository
                .findAll()
                .stream()
                .filter(producer -> producer.getName().equals(producerName)
                        && producer.getTrade().getId().equals(tradeId)
                        && producer.getCountry().getId().equals(countryId))
                .collect(Collectors.toList());
        if (producers.size() != 0) {
            throw new Errors("PRODUCER WITH GIVEN TRADE AND COUNTRY ALREADY EXISTS ", LocalDate.now());
        }
        return countryId;
    }
}
