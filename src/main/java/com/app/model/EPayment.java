package com.app.model;

/**
 * EPayment is an enum with possible Payment values.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

public enum EPayment {
    CASH, CARD, MONEY_TRANSFER;

    public static EPayment returnPayment(int number) {
        switch (number) {
            case 1:
                return CASH;
            case 2:
                return CARD;
            case 3:
                return MONEY_TRANSFER;
            default:
                return null;
        }
    }
}
