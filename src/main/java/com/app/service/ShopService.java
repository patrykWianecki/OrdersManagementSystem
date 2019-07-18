package com.app.service;

import java.util.Comparator;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Country;
import com.app.model.Shop;
import com.app.model.dto.ShopDto;
import com.app.repository.country.CountryRepository;
import com.app.repository.country.CountryRepositoryImpl;
import com.app.repository.shop.ShopRepository;
import com.app.repository.shop.ShopRepositoryImpl;

import static com.app.model.dto.MyMapper.*;

public class ShopService {

    private CountryRepository countryRepository = new CountryRepositoryImpl();
    private ShopRepository shopRepository = new ShopRepositoryImpl();

    public void addShop(ShopDto shopDto) {
        try {
            String countryName = shopDto.getCountryDto().getName();

            Country country = countryRepository.findByName(shopDto.getCountryDto().getName())
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing country with name " + countryName));

            Shop shop = fromShopDtoToShop(shopDto);

            shop.setCountry(country);
            shopRepository.addOrUpdate(shop);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "Add shop exception");
        }
    }

    public void printAllShops() {
        shopRepository.findAll()
            .stream()
            .sorted(Comparator.comparing(Shop::getId))
            .forEach(shop -> System.out.println(shop.getId() + ". " + shop.getName()));
    }
}
