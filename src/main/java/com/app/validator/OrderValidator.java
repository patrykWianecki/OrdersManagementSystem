package com.app.validator;

import java.math.BigDecimal;
import java.util.Scanner;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.repository.stock.StockRepository;
import com.app.repository.stock.StockRepositoryImpl;

public class OrderValidator {

    private Scanner scanner = new Scanner(System.in);
    private StockRepository stockRepository = new StockRepositoryImpl();

    public Integer validateQuantity(String quantity, Long productId) {
        int stockQuantity
            = stockRepository
            .findAll()
            .stream()
            .filter(x -> x.getProduct().getId().equals(productId))
            .findFirst()
            .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))
            .getQuantity();

        while (!quantity.matches("[0-9]+")
            || Integer.valueOf(quantity) < 1
            || Integer.valueOf(quantity) > 100
            || Integer.valueOf(quantity) > stockQuantity) {
            if (Integer.valueOf(quantity) > stockQuantity) {
                System.out.println("Not enough products in stock!");
            } else {
                System.out.println("Wrong quantity!");
            }
            System.out.println("Enter again:");
            quantity = scanner.nextLine();
        }

        return Integer.valueOf(quantity);
    }

    public BigDecimal validateDiscount(String discount) {
        while (!discount.matches("([0-9])|([1-9]+[0-9])|([1]+[0]+[0])")
            || (BigDecimal.valueOf(Integer.valueOf(discount)).compareTo(BigDecimal.valueOf(100)) > 0)
        ) {
            System.out.println("Wrong order discount!\nEnter again:");
            discount = scanner.nextLine();
        }
        return BigDecimal.valueOf(Integer.valueOf(discount));
    }
}
