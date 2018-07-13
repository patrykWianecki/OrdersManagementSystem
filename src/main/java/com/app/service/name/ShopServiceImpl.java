package com.app.service.name;

import com.app.model.Errors;
import com.app.model.Shop;
import com.app.repository.ShopRepository;
import com.app.repository.ShopRepositoryImpl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShopServiceImpl implements ShopService {
    private Scanner scanner = new Scanner(System.in);
    private static ShopRepository shopRepository = new ShopRepositoryImpl();

    @Override
    public String setShopName(String name) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Wrong shop name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public void printAllShops() {
        shopRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Shop::getId))
                .forEach(s -> System.out.println(s.getId() + ". " + s.getName()));
    }

    @Override
    public Long OneShopFromOneCountry(String shopName, Long countryId) throws Errors {
        List<Shop> shops
                = shopRepository
                .findAll()
                .stream()
                .filter(shop -> shop.getName().equals(shopName) && shop.getCountry().getId().equals(countryId))
                .collect(Collectors.toList());
        if (shops.size() != 0) {
            throw new Errors("SHOP FROM CHOSEN COUNTRY ALREADY EXISTS ", LocalDate.now());
        }
        return countryId;
    }
}
