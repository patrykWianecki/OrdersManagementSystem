package com.app.service.menu;

import java.util.Scanner;

import com.app.model.State;
import com.app.model.dto.CountryDto;
import com.app.model.dto.ProducerDto;
import com.app.model.dto.TradeDto;
import com.app.service.CountryService;
import com.app.service.MyService;
import com.app.service.ProducerService;
import com.app.service.TradeService;
import com.app.validator.ProducerValidator;
import com.app.validator.ToolsValidator;

import static com.app.model.State.*;

class ProducerMenu {

    private static Scanner scanner = new Scanner(System.in);

    private MyService myService = new MyService();
    private CountryService countryService = new CountryService();
    private ProducerService producerService = new ProducerService();
    private ProducerValidator producerValidator = new ProducerValidator();
    private ToolsValidator toolsValidator = new ToolsValidator();
    private TradeService tradeService = new TradeService();

    State printProducer() {
        System.out.println("1 - add new Producer");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        State state;
        switch (choice) {
            case 1: {
                state = addProducer();
                break;
            }
            case 0: {
                state = INIT;
                break;
            }
            default: {
                System.out.println("Wrong choice!");
                state = PRODUCER;
                break;
            }
        }

        return state;
    }

    private State addProducer() {
        System.out.println("Enter producer name");
        String name = producerValidator.validateName(scanner.nextLine());

        System.out.println("Chose trade from list:");
        System.out.println(tradeService.printAllTrades());
        long tradeId = toolsValidator.chooseId(scanner.nextLine());

        System.out.println("Chose country from list:");
        countryService.printAllCountries();
        long countryId = myService.findAvailableProducerName(name, tradeId, toolsValidator.chooseId(scanner.nextLine()));

        producerService.addProducer(ProducerDto.builder()
            .name(name)
            .countryDto(CountryDto.builder()
                .name(myService.findCountryByIdWithErrorCheck(countryId).getName())
                .build())
            .tradeDto(TradeDto.builder()
                .name(myService.findTradeByIdWithErrorCheck(tradeId).getName())
                .build())
            .build()
        );

        return PRODUCER;
    }
}
