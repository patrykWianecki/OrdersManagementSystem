package com.app.model;

/**
 * EGuarantee is an enum with possible Guarantee values.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

public enum EGuarantee {
    HELP_DESK, MONEY_BACK, SERVICE, EXCHANGE;

    public EGuarantee returnGuarantee(int number) {
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
