package com.app.parsers.implementations;

import com.app.model.Trade;
import com.app.parsers.interfaces.Parser;
import com.app.parsers.interfaces.RegularExpressions;

public class TradeParser implements Parser<Trade> {
    @Override
    public Trade parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.TRADE_REGEX)) {
            return null;
        }
        String[] trade = line.split(";");
        return Trade.builder()
                .id(Long.parseLong(trade[0]))
                .name(trade[1])
                .build();
    }
}
