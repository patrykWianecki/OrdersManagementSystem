package com.app.service.menu;

import java.util.Scanner;

import com.app.model.dto.CountryDto;
import com.app.model.dto.ProducerDto;
import com.app.model.dto.TradeDto;
import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.State;
import com.app.repository.country.CountryRepository;
import com.app.repository.country.CountryRepositoryImpl;
import com.app.repository.trade.TradeRepository;
import com.app.repository.trade.TradeRepositoryImpl;
import com.app.service.CountryService;
import com.app.service.MyService;
import com.app.service.ProducerService;
import com.app.validator.ProducerValidator;
import com.app.validator.ToolsValidator;

public class ProducerMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static State state;

    private MyService myService = new MyService();
    private ProducerService producerService = new ProducerService();
    private CountryService countryService = new CountryService();
    private ProducerValidator producerValidator = new ProducerValidator();
    private ToolsValidator toolsValidator = new ToolsValidator();
    private CountryRepository countryRepository = new CountryRepositoryImpl();
    private TradeRepository tradeRepository = new TradeRepositoryImpl();

    State printProducer() {
        System.out.println("1 - add new Producer");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                state = addProducer();
                break;
            case 0:
                state = State.INIT;
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.PRODUCER;
                break;
        }
        return state;
    }

    private State addProducer() {
        System.out.println("Enter producer name");
        String name = producerValidator.validateName(scanner.nextLine());

        System.out.println("Chose trade from list:");
        // TODO
        //        myService.printAllTrades();
        long tradeId = toolsValidator.chooseId(scanner.nextLine());

        System.out.println("Chose country from list:");
        countryService.printAllCountries();
        long countryId = myService.oneProducerNameAndTradeFromOneCountry(name, tradeId, toolsValidator.chooseId(scanner.nextLine()));

        producerService.addProducer(ProducerDto
            .builder()
            .name(name)
            .countryDto(CountryDto
                .builder()
                .name(countryRepository
                    .findOne(countryId)
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))
                    .getName()
                )
                .build())
            .tradeDto(TradeDto
                .builder()
                .name(tradeRepository
                    .findOne(tradeId)
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))
                    .getName()
                )
                .build())
            .build()
        );

        return State.PRODUCER;
    }
}
