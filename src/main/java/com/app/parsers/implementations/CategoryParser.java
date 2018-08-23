package com.app.parsers.implementations;

import com.app.model.Category;
import com.app.parsers.interfaces.Parser;
import com.app.parsers.interfaces.RegularExpressions;

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
