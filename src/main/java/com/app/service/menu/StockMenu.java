package com.app.service.menu;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.app.model.dto.ProductDto;
import com.app.model.dto.ShopDto;
import com.app.model.dto.StockDto;
import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.State;
import com.app.model.Stock;
import com.app.repository.product.ProductRepository;
import com.app.repository.product.ProductRepositoryImpl;
import com.app.repository.shop.ShopRepository;
import com.app.repository.shop.ShopRepositoryImpl;
import com.app.repository.stock.StockRepository;
import com.app.repository.stock.StockRepositoryImpl;
import com.app.service.ProductService;
import com.app.service.ShopService;
import com.app.service.StockService;
import com.app.validator.StockValidator;
import com.app.validator.ToolsValidator;

class StockMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static State state;

    private StockService stockService = new StockService();
    private ShopService shopService = new ShopService();
    private ProductService productService = new ProductService();
    private StockValidator stockValidator = new StockValidator();
    private ToolsValidator toolsValidator = new ToolsValidator();
    private StockRepository stockRepository = new StockRepositoryImpl();
    private ShopRepository shopRepository = new ShopRepositoryImpl();
    private ProductRepository productRepository = new ProductRepositoryImpl();

    State printStock() {
        System.out.println("1 - add new Stock");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                state = addStock();
                break;
            case 0:
                state = State.INIT;
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.STOCK;
                break;
        }
        return state;
    }

    private State addStock() {
        System.out.println("Enter quantity");
        int quantity = stockValidator.validateQuantity(scanner.nextLine());

        System.out.println("Chose shop from list:");
        shopService.printAllShops();

        long shopId = toolsValidator.chooseId(scanner.nextLine());
        System.out.println("Chose product from list:");
        productService.printAllProducts();

        long productId = toolsValidator.chooseId(scanner.nextLine());
        oneProductFromOneShopInStock(shopId, productId, quantity);

        return State.STOCK;
    }

    // TODO uporządkować
    private void oneProductFromOneShopInStock(Long shopId, Long productId, Integer stockQuantity) {
        List<Stock> stocks
            = stockRepository
            .findAll()
            .stream()
            .filter(stock -> stock.getShop().getId().equals(shopId)
                && stock.getProduct().getId().equals(productId))
            .collect(Collectors.toList());

        if (stocks.size() != 0) {
            int updatedQuantity = stocks.get(0).getQuantity() + stockQuantity;
            long upId = stocks.get(0).getId();
            stockRepository.delete(upId);
            nazwaTODO(updatedQuantity, shopId, productId);
        } else {
            nazwaTODO(stockQuantity, shopId, productId);
        }
    }

    private void nazwaTODO(Integer quantity, Long shopId, Long productId) {
        stockService.addStock(
            StockDto
                .builder()
                .quantity(quantity)
                .shopDto(ShopDto
                    .builder()
                    .name(shopRepository
                        .findOne(shopId)
                        .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))
                        .getName())
                    .build())
                .productDto(ProductDto
                    .builder()
                    .name(productRepository
                        .findOne(productId)
                        .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))
                        .getName())
                    .build())
                .build()
        );
    }
}
