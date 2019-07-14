package com.app.parser.implementation;

import com.app.model.Category;
import com.app.parser.interfaces.Parser;
import com.app.parser.interfaces.RegularExpressions;

public class CategoryParser implements Parser<Category> {

    @Override
    public Category parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.CATEGORY_REGEX)) {
            return null;
        }
        String[] category = line.split(";");
        return Category.builder()
            .id(Long.valueOf(category[0]))
            .name(category[1])
            .build();
    }
}
