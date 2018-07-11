package com.app.service.name;

import com.app.model.Producer;
import com.app.repository.ProducerRepository;
import com.app.repository.ProducerRepositoryImpl;

import java.util.Comparator;
import java.util.Scanner;

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
}
