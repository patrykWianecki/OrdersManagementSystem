package com.app.parser.implementation;

import com.app.model.Country;
import com.app.model.Producer;
import com.app.model.Trade;
import com.app.parser.interfaces.Parser;
import com.app.parser.interfaces.RegularExpressions;

public class ProducerParser implements Parser<Producer> {

    @Override
    public Producer parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.PRODUCER_REGEX)) {
            return null;
        }
        String[] producer = line.split(";");
        return Producer.builder()
            .id(Long.valueOf(producer[0]))
            .name(producer[1])
            .country(Country.builder().name(producer[2]).build())
            .trade(Trade.builder().name(producer[3]).build())
            .build();
    }
}
