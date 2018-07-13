package com.app.service.name;

import com.app.dto.MyMapper;
import com.app.dto.ProductDto;
import com.app.dto.ShopDto;
import com.app.dto.StockDto;
import com.app.model.Errors;
import com.app.model.Stock;
import com.app.repository.*;
import com.app.service.MyService;
import com.app.service.MyServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StockServiceImpl implements StockService {
    private static Scanner scanner = new Scanner(System.in);
    private static MyService myService = new MyServiceImpl();
    private static MyMapper myMapper = new MyMapper();
    private static ProductRepository productRepository = new ProductRepositoryImpl();
    private static ShopRepository shopRepository = new ShopRepositoryImpl();
    private static StockRepository stockRepository = new StockRepositoryImpl();

    @Override
    public int setQuantity(String quantity) {
        while (Integer.valueOf(quantity) < 1) {
            System.out.println("Wrong quantity!" + "\n" + "Enter again:");
            quantity = scanner.nextLine();
        }
        return Integer.parseInt(quantity);
    }

    @Override
    public void OneProductFromOneShop(Long shopId, Long productId, Integer stockQuantity) throws Errors {
        List<Stock> stocks
                = stockRepository
                .findAll()
                .stream()
                .filter(stock -> stock.getShop().getId().equals(shopId)
                        && stock.getProduct().getId().equals(productId))
                .collect(Collectors.toList());

        if (stocks.size() != 0) {
            int updatedQuantity = stocks.get(0).getQuantity() + stockQuantity;

        } else {
            myService.addStock(
                    StockDto
                            .builder()
                            .quantity(stockQuantity)
                            .shopDto(ShopDto
                                    .builder()
                                    .name(shopRepository
                                            .findOne(shopId)
                                            .orElseThrow(() -> new Errors("MENU ADD_STOCK, SHOP NOT FOUND", LocalDate.now()))
                                            .getName())
                                    .build())
                            .productDto(ProductDto
                                    .builder()
                                    .name(productRepository
                                            .findOne(productId)
                                            .orElseThrow(() -> new Errors("MENU - ADD_STOCK, PRODUCT NOT FOUND", LocalDate.now()))
                                            .getName())
                                    .build())
                            .build()
            );
        }
    }
}
