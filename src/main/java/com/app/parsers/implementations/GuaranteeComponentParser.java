package com.app.parsers.implementations;

import com.app.model.EGuarantee;
import com.app.model.GuaranteeComponent;
import com.app.model.Product;
import com.app.parsers.interfaces.Parser;
import com.app.parsers.interfaces.RegularExpressions;

public class GuaranteeComponentParser implements Parser<GuaranteeComponent> {
    @Override
    public GuaranteeComponent parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.GUARANTEE_COMPONENT_REGEX)) {
            return null;
        }
        String[] component = line.split(";");
        return GuaranteeComponent.builder()
                .product(Product.builder().name(component[0]).build())
                .guaranteeComponent(EGuarantee.returnGuarantee(Integer.parseInt(component[1])))
                .build();
    }
}
