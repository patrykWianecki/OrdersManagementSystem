package com.app.validator;

import java.util.Scanner;

public class CountryValidator {

    private Scanner scanner = new Scanner(System.in);

    public String validateName(String name) {
        while (!name.matches("([A-Z]+)|")) {
            System.out.println("Incorrect country name!\nEnter again:");
            name = scanner.nextLine();
        }
        return name;
    }
}
