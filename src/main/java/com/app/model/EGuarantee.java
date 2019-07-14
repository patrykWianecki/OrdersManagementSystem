package com.app.model;

public enum EGuarantee {
    HELP_DESK, MONEY_BACK, SERVICE, EXCHANGE;

    public static EGuarantee returnGuarantee(int number) {
        switch (number) {
            case 1:
                return HELP_DESK;
            case 2:
                return MONEY_BACK;
            case 3:
                return SERVICE;
            case 4:
                return EXCHANGE;
            default:
                return null;
        }
    }
}
