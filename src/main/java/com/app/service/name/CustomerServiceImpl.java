package com.app.service.name;

import java.util.Scanner;

public class CustomerServiceImpl implements CustomerService {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public String setCustomerName(String name) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Wrong customer name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public String setCustomerSurname(String surname) {
        while (!surname.matches("([A-Z]+)")) {
            System.out.println("Wrong customer surname!" + "\n" + "Enter again:");
            surname = scanner.nextLine();
        }
        return surname;
    }

    @Override
    public Integer setCustomerAge(String age) {
        while (!age.matches("([1-9]+[0-9])" + "|" + "([1]+[0-2]+[0-9])")
                || Integer.valueOf(age) < 18
                || Integer.valueOf(age) > 120) {
            System.out.println("Wrong age format or age is to small!" + "\n" + "Enter again:");
            age = scanner.nextLine();
        }
        return Integer.valueOf(age);
    }
}
