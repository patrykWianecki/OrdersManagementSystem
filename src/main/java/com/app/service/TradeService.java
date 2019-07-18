package com.app.service;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.app.model.Trade;
import com.app.model.dto.TradeDto;
import com.app.repository.trade.TradeRepository;
import com.app.repository.trade.TradeRepositoryImpl;

import static com.app.model.dto.MyMapper.*;

public class TradeService {

    private TradeRepository tradeRepository = new TradeRepositoryImpl();

    public void addTrade(TradeDto tradeDto) {
        Trade trade = fromTradeDtoToTrade(tradeDto);
        tradeRepository.addOrUpdate(trade);
    }

    public String printAllTrades() {
        AtomicInteger counter = new AtomicInteger(1);
        return tradeRepository.findAll()
            .stream()
            .sorted(Comparator.comparing(Trade::getId))
            .map(Trade::toString)
            .map(tradeName -> counter.getAndIncrement() + ". " + tradeName)
            .collect(Collectors.joining("\n"));
    }
}
