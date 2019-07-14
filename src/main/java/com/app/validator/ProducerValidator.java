package com.app.validator;

import java.util.Scanner;

public class ProducerValidator {

    private Scanner scanner = new Scanner(System.in);

    public String validateName(String name) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Wrong product name!\nEnter again:");
            name = scanner.nextLine();
        }
        return name;
    }
}
