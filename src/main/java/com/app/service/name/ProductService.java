package com.app.service.name;

import java.math.BigDecimal;

public interface ProductService {
    String setProductName(String name);

    BigDecimal setProductPrice(String price);

    void printAllProducts();
}