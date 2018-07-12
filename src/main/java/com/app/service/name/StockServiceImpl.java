package com.app.service.name;

import com.app.model.Errors;
import com.app.model.Stock;
import com.app.repository.StockRepository;
import com.app.repository.StockRepositoryImpl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Scanner;

public class StockServiceImpl implements StockService {
    private static Scanner scanner = new Scanner(System.in);
    private static StockRepository stockRepository = new StockRepositoryImpl();

    @Override
    public int setQuantity(String quantity) {
        while (Integer.valueOf(quantity) < 1) {
            System.out.println("Wrong quantity!" + "\n" + "Enter again:");
            quantity = scanner.nextLine();
        }
        return Integer.parseInt(quantity);
    }

    @Override
    public Long OneProductFromOneShop(Long productId, Long shopId) {
        long stockId = 0;
        boolean flag = false;
        for (Stock s : stockRepository.findAll()) {
            if (s.getProduct().getId().equals(productId) && s.getShop().getId().equals(shopId)) {
                stockId = s.getId();
                flag = true;
                break;
            }
        }
        return flag ? stockId : stockRepository
                .findAll()
                .stream()
                .max(Comparator.comparing(Stock::getId))
                .orElseThrow(() -> new Errors("SERVICE, MAX STOCK ID NOT FOUND ", LocalDate.now()))
                .getId();
    }
}
