package com.app.validator;

import java.util.Scanner;

public class ToolsValidator {

    private Scanner scanner = new Scanner(System.in);

    public Long chooseId(String id) {
        while (!id.matches("[0-9]+") || Long.valueOf(id) < 1) {
            System.out.println("Wrong value!\nEnter again:");
            id = scanner.nextLine();
        }
        return Long.valueOf(id);
    }
}
