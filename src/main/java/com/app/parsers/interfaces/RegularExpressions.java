package com.app.parsers.interfaces;

public interface RegularExpressions {
    String CATEGORY_REGEX = "\\d+;[A-Z]+";
    String COUNTRY_REGEX = "\\d+;[A-Z]+";
    String CUSTOMER_REGEX = "\\d+;\\d+;[A-Z]+;[A-Z]+;[A-Z]";
    String CUSTOMER_ORDER_REGEX = "\\d+;\\d+/-/\\d+/-/\\d+;\\d+;\\d+;[A-Z]+;\\d+;[A-Z]+";
    String GUARANTEE_COMPONENT_REGEX = "[A-Z]+;\\d+";
    String PAYMENT_REGEX = "\\d+;\\d+";
    String PRODUCER_REGEX = "\\d+;[A-Z]+;[A-Z]+;[A-Z]+";
    String PRODUCT_REGEX = "\\d+;[A-Z]+;\\d+;[A-Z]+;[A-Z]+";
    String SHOP_REGEX = "\\d+;[A-Z]+;[A-Z]+";
    String STOCK_REGEX = "\\d+;\\d+;[A-Z]+;[A-Z]+";
    String TRADE_REGEX = "\\d+;[A-Z]+";
}
