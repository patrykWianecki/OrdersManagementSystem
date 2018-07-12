package com.app.service.name;

public interface StockService {
    int setQuantity(String quantity);

    Long OneProductFromOneShop(Long productId, Long shopId);
}
