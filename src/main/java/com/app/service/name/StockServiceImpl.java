package com.app.service.name;

import java.util.Scanner;

public class StockServiceImpl implements StockService {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public int setQuantity(String quantity) {
        while (Integer.valueOf(quantity) < 1) {
            System.out.println("Wrong quantity!" + "\n" + "Enter again:");
            quantity = scanner.nextLine();
        }
        return Integer.parseInt(quantity);
    }
}
