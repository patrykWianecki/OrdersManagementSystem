package com.app.model;

/**
 * EMessage is an enum with possible Error values.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

public enum EMessage {
    ADD_OR_UPDATE,
    DELETE,
    FIND_ONE,
    FIND_ALL,
    FIND_BY_NAME,
    FIND_BY_NAME_AND_SURNAME,
    FAILED_TO_GET,
    SERVICE_ADD;

    public EMessage returnMessage(int number) {
        switch (number) {
            case 1:
                return ADD_OR_UPDATE;
            case 2:
                return DELETE;
            case 3:
                return FIND_ONE;
            case 4:
                return FIND_ALL;
            case 5:
                return FIND_BY_NAME;
            case 6:
                return FIND_BY_NAME_AND_SURNAME;
            case 7:
                return FAILED_TO_GET;
            case 8:
                return SERVICE_ADD;
            default:
                return null;
        }
    }
}
