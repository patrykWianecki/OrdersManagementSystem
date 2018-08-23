package com.app.parsers.implementations;

import com.app.model.Country;
import com.app.parsers.interfaces.Parser;
import com.app.parsers.interfaces.RegularExpressions;

public class CountryParser implements Parser<Country> {
    @Override
    public Country parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.COUNTRY_REGEX)) {
            return null;
        }
        String[] country = line.split(";");
        return Country.builder()
                .id(Long.parseLong(country[0]))
                .name(country[1])
                .build();
    }
}
