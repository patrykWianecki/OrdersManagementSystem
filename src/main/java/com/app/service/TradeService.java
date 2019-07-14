package com.app.service;

import java.util.Comparator;

import com.app.model.dto.MyMapper;
import com.app.model.dto.TradeDto;
import com.app.model.Trade;
import com.app.repository.trade.TradeRepository;
import com.app.repository.trade.TradeRepositoryImpl;

public class TradeService {

    private MyMapper mapper = new MyMapper();
    private TradeRepository tradeRepository = new TradeRepositoryImpl();

    public void addTrade(TradeDto tradeDto) {
        Trade trade = mapper.fromTradeDtoToTrade(tradeDto);
        tradeRepository.addOrUpdate(trade);
    }

    public void printAllTrades() {
        tradeRepository
            .findAll()
            .stream()
            .sorted(Comparator.comparing(Trade::getId))
            .forEach(trade -> System.out.println(trade.getId() + ". " + trade.getName()));
    }
}
