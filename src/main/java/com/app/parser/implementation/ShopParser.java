package com.app.parser.implementation;

import com.app.model.Country;
import com.app.model.Shop;
import com.app.parser.interfaces.Parser;
import com.app.parser.interfaces.RegularExpressions;

public class ShopParser implements Parser<Shop> {

    @Override
    public Shop parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.SHOP_REGEX)) {
            return null;
        }
        String[] shop = line.split(";");
        return Shop.builder()
            .id(Long.valueOf(shop[0]))
            .name(shop[1])
            .country(Country.builder().name(shop[2]).build())
            .build();
    }
}
