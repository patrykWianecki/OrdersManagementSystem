package com.app.parsers.implementations;

import com.app.model.EPayment;
import com.app.model.Payment;
import com.app.parsers.interfaces.Parser;
import com.app.parsers.interfaces.RegularExpressions;

public class PaymentParser implements Parser<Payment> {
    @Override
    public Payment parse(String line) {
        if (!Parser.isLineCorrect(line, RegularExpressions.PAYMENT_REGEX)) {
            return null;
        }
        String[] payment = line.split(";");
        return Payment.builder()
                .id(Long.valueOf(payment[0]))
                .payment(EPayment.returnPayment(Integer.parseInt(payment[1])))
                .build();
    }
}
