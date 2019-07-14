package com.app.validator;

import java.math.BigDecimal;
import java.util.Scanner;

public class ProductValidator {

    private Scanner scanner = new Scanner(System.in);

    public String validateName(String name) {
        while (!name.matches("(([A-Z])+)")) {
            System.out.println("Wrong product name!\nEnter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    public BigDecimal validatePrice(String price) {
        while (BigDecimal.valueOf(Long.parseLong(price)).compareTo(BigDecimal.ZERO) < 1) {
            System.out.println("Wrong product price!\nEnter again:");
            price = scanner.nextLine();
        }
        return BigDecimal.valueOf(Long.parseLong(price));
    }
}
