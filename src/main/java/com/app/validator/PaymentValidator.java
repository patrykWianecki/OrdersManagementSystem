package com.app.validator;

import java.util.Scanner;

public class PaymentValidator {

    private Scanner scanner = new Scanner(System.in);

    public Integer setPaymentType(String number) {
        while (!number.matches("[1-3]")
            || Integer.valueOf(number) < 1
            || Integer.valueOf(number) > 3) {
            System.out.println("Wrong number!\nEnter again");
            number = scanner.nextLine();
        }
        return Integer.valueOf(number);
    }
}
