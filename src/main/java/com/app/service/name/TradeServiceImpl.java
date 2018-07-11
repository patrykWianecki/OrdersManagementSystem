package com.app.service.name;

import com.app.model.Trade;
import com.app.repository.TradeRepository;
import com.app.repository.TradeRepositoryImpl;

import java.util.Comparator;
import java.util.Scanner;

public class TradeServiceImpl implements TradeService {
    private static Scanner scanner = new Scanner(System.in);
    private static TradeRepository tradeRepository = new TradeRepositoryImpl();

    @Override
    public String setTradeName(String name) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Wrong trade name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public void printAllTrades() {
        tradeRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Trade::getId))
                .forEach(s -> System.out.println(s.getId() + ". " + s.getName()));
    }
}
