package com.app.validator;

import java.util.Scanner;

public class StockValidator {

    private Scanner scanner = new Scanner(System.in);

    public int validateQuantity(String quantity) {
        while (!quantity.matches("[0-9]+") || Integer.valueOf(quantity) < 1) {
            System.out.println("Wrong quantity!\nEnter again:");
            quantity = scanner.nextLine();
        }
        return Integer.parseInt(quantity);
    }
}
