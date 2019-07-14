package com.app.parser.implementation;

import com.app.model.Category;
import com.app.model.Producer;
import com.app.model.Product;
import com.app.parser.interfaces.Parser;
import com.app.parser.interfaces.RegularExpressions;

import java.math.BigDecimal;

public class ProductParser implements Parser<Product> {

    @Override
    public Product parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.PRODUCT_REGEX)) {
            return null;
        }
        String[] product = line.split(";");
        return Product.builder()
            .id(Long.valueOf(product[0]))
            .name(product[1])
            .price(new BigDecimal(product[2]))
            .category(Category.builder().name(product[2]).build())
            .producer(Producer.builder().name(product[3]).build())
            .build();
    }
}
