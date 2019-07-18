package com.app.service;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Product;
import com.app.model.Shop;
import com.app.model.Stock;
import com.app.model.dto.StockDto;
import com.app.repository.product.ProductRepository;
import com.app.repository.product.ProductRepositoryImpl;
import com.app.repository.shop.ShopRepository;
import com.app.repository.shop.ShopRepositoryImpl;
import com.app.repository.stock.StockRepository;
import com.app.repository.stock.StockRepositoryImpl;

import static com.app.model.dto.MyMapper.*;

class StockService {

    private ProductRepository productRepository = new ProductRepositoryImpl();
    private ShopRepository shopRepository = new ShopRepositoryImpl();
    private StockRepository stockRepository = new StockRepositoryImpl();

    void addStock(StockDto stockDto) {
        try {
            String productName = stockDto.getProductDto().getName();
            String shopName = stockDto.getShopDto().getName();

            Product product = productRepository.findByName(productName)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing product with name " + productName));

            Shop shop = shopRepository.findByName(stockDto.getShopDto().getName())
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Missing shop with name " + shopName));

            Stock stock = fromStockDtoToStock(stockDto);
            stock.setProduct(product);
            stock.setShop(shop);

            stockRepository.addOrUpdate(stock);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "Add stock exception");
        }
    }
}
