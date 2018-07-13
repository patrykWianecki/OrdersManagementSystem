package com.app.service.name;

public interface StockService {
    int setQuantity(String quantity);

    void OneProductFromOneShop(Long shopId, Long productId, Integer stockQuantity);
}
