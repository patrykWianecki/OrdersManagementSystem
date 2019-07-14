package com.app.service.menu;

import java.util.Scanner;

import com.app.model.dto.TradeDto;
import com.app.model.State;
import com.app.service.TradeService;
import com.app.validator.TradeValidator;

class TradeMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static State state;

    private TradeService tradeService = new TradeService();
    private TradeValidator tradeValidator = new TradeValidator();

    State printTrade() {
        System.out.println("0 - exit");
        System.out.println("1 - add new Trade");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0:
                state = State.INIT;
                break;
            case 1:
                state = addTrade();
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.TRADE;
                break;
        }
        return state;
    }

    private State addTrade() {
        System.out.println("Enter trade name:");
        String name = tradeValidator.validateName(scanner.nextLine());

        tradeService.addTrade(TradeDto
            .builder()
            .name(name)
            .build()
        );

        return State.TRADE;
    }
}
