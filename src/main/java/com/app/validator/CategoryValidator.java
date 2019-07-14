package com.app.validator;

import java.util.Scanner;

public class CategoryValidator {

    private Scanner scanner = new Scanner(System.in);

    public String validateCategoryName(String name) {
        while (!name.matches("([A-Z]+)|")) {
            System.out.println("Incorrect category name!\nEnter again:");
            name = scanner.nextLine();
        }
        return name;
    }
}
