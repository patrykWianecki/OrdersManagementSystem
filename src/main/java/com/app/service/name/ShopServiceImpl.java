package com.app.service.name;

import com.app.model.Shop;
import com.app.repository.ShopRepository;
import com.app.repository.ShopRepositoryImpl;

import java.util.Comparator;
import java.util.Scanner;

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
}
