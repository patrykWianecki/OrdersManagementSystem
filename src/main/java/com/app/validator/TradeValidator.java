package com.app.validator;

import java.util.Scanner;

public class TradeValidator {

    private Scanner scanner = new Scanner(System.in);

    public String validateName(String name) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Wrong trade name!\nEnter again:");
            name = scanner.nextLine();
        }
        return name;
    }
}
