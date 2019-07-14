package com.app.model;

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
