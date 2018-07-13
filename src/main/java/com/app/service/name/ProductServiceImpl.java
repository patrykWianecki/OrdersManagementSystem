package com.app.service.name;

import com.app.model.Errors;
import com.app.model.Product;
import com.app.repository.ProductRepository;
import com.app.repository.ProductRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public String setProductName(String name) {
        while (!name.matches("(([A-Z])+)")) {
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
    public Long OneProductNameAndCategoryFromOneProducer(String productName, Long categoryId, Long producerId) throws Errors {
        List<Product> products
                = productRepository
                .findAll()
                .stream()
                .filter(product -> product.getName().equals(productName)
                        && product.getCategory().getId().equals(categoryId)
                        && product.getProducer().getId().equals(producerId))
                .collect(Collectors.toList());
        if (products.size() != 0) {
            throw new Errors("PRODUCT WITH GIVEN CATEGORY AND PRODUCER ALREADY EXISTS ", LocalDate.now());
        }
        return producerId;
    }
}
