package com.app.service.name;

import com.app.model.Product;
import com.app.repository.ProductRepository;
import com.app.repository.ProductRepositoryImpl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Scanner;

public class ProductServiceImpl implements ProductService {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public String setProductName(String name) {
        while (!name.matches("([A-Z]+)")) {
            System.out.println("Wrong product name!" + "\n" + "Enter again:");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public BigDecimal setProductPrice(String price) {
        while (BigDecimal.valueOf(Long.parseLong(price)).compareTo(BigDecimal.ZERO) < 1) {
            System.out.println("Wrong product price!" + "\n" + "Enter again:");
            price = scanner.nextLine();
            scanner.nextLine();
        }
        return BigDecimal.valueOf(Long.parseLong(price));
    }

    @Override
    public void printAllProducts() {
        productRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Product::getId))
                .forEach(x -> System.out.println(x.getId() + ". " + x.getName()));
    }

    @Override
    public Long OneProductNameAndCategoryFromOneProducer(String productName, String categoryName, Long producerId) {


        return null;
    }
}
