package com.app.validator;

import java.util.Scanner;

public class CustomerValidator {

    private Scanner scanner = new Scanner(System.in);

    public String validateNameOrSurname(String name, String value) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Incorrect customer " + value + "!\nEnter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    public Integer validateAge(String age) {
        while (!age.matches("([1-9]+[0-9])" + "|" + "([1]+[0-2]+[0-9])") ||
            Integer.valueOf(age) < 18 ||
            Integer.valueOf(age) > 120) {
            System.out.println("Incorrect age!\nEnter again:");
            age = scanner.nextLine();
        }
        return Integer.valueOf(age);
    }
}
