package com.app.service.name;

public interface ShopService {
    String setShopName(String name);

    void printAllShops();

    Long OneShopFromOneCountry(String shopName, Long countryId);
}
