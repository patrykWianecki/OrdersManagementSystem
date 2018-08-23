package com.app.parsers.implementations;

import com.app.model.Product;
import com.app.model.Shop;
import com.app.model.Stock;
import com.app.parsers.interfaces.Parser;
import com.app.parsers.interfaces.RegularExpressions;

public class StockParser implements Parser<Stock> {
    @Override
    public Stock parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.STOCK_REGEX)) {
            return null;
        }
        String[] stock = line.split(";");
        return Stock.builder()
                .id(Long.valueOf(stock[0]))
                .quantity(Integer.valueOf(stock[1]))
                .product(Product.builder().name(stock[2]).build())
                .shop(Shop.builder().name(stock[3]).build())
                .build();
    }
}
