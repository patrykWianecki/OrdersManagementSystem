package com.app.parser.implementation;

import com.app.model.Payment;
import com.app.parser.interfaces.Parser;
import com.app.parser.interfaces.RegularExpressions;

public class PaymentParser implements Parser<Payment> {

    @Override
    public Payment parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.PAYMENT_REGEX)) {
            return null;
        }
        String[] payment = line.split(";");
        return Payment.builder()
            .id(Long.valueOf(payment[0]))
            .build();
    }
}
