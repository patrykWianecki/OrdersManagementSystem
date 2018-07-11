package com.app.service.name;

import com.app.model.Errors;
import com.app.model.Stock;
import com.app.repository.StockRepository;
import com.app.repository.StockRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class CustomerOrdersServiceImpl implements CustomerOrdersService {
    private static Scanner scanner = new Scanner(System.in);
    private final static LocalDate MAX_DATE = LocalDate.of(2020, 12, 31);
    private final static LocalDate ACTUAL_DATE = LocalDate.now();
    private static StockRepository stockRepository = new StockRepositoryImpl();

    @Override
    public Integer setOrderQuantity(String quantity, Long productId) {
        Integer stockQuantity
                = stockRepository
                .findOne(productId)
                .orElseThrow(() -> new Errors("ORDERS SERVICE, PRODUCT NOT FOUND ", LocalDate.now()))
                .getQuantity();

        while (!quantity.matches("[0-9]+")
                || Integer.valueOf(quantity) < 1
                || Integer.valueOf(quantity) > 100
                || Integer.valueOf(quantity) > stockQuantity) {
            if (Integer.valueOf(quantity) > stockQuantity) System.out.println("Not enough products in stock!");
            else System.out.println("Wrong quantity!");
            System.out.println("Enter again:");
            quantity = scanner.nextLine();
        }

        stockRepository.addOrUpdate(Stock
                .builder()
                .quantity(stockQuantity - Integer.valueOf(quantity))
                .build());
        return Integer.valueOf(quantity);
    }

    @Override
    public BigDecimal setOrderDiscount(String discount) {
        while (!discount.matches("([0-9])|([1-9]+[0-9])|([1]+[0]+[0])")
                || (BigDecimal.valueOf(Integer.valueOf(discount)).compareTo(BigDecimal.valueOf(100)) > 0)
                ) {
            System.out.println("Wrong order discount!" + "\n" + "Enter again:");
            discount = scanner.nextLine();
        }
        return BigDecimal.valueOf(Integer.valueOf(discount));
    }

    @Override
    public LocalDate setOrderDate() {
        String dash = "-";
        String year = String.valueOf(setCorrectYear(ACTUAL_DATE, MAX_DATE));
        String month = String.valueOf(setCorrectMonth(ACTUAL_DATE, year));
        String day = String.valueOf(setCorrectDay(ACTUAL_DATE, month));
        return LocalDate.parse(String.valueOf(new StringBuilder()
                .append(year).append(dash).append(month).append(dash).append(day)));
    }

    private static String setCorrectDay(LocalDate actualDate, String month) {
        System.out.println("Enter day:");
        String day = scanner.nextLine();
        if (Integer.valueOf(month).equals(actualDate.getMonthValue())) {
            while (!day.matches("[0-9]{2}")
                    || Integer.valueOf(day) < 1
                    || Integer.valueOf(day) > 31
                    || Integer.valueOf(day) < actualDate.getDayOfMonth()) {
                System.out.println("Wrong day!" + "\n" + "Enter again:");
                day = scanner.nextLine();
            }
        } else {
            while (!day.matches("[0-9]{2}")
                    || Integer.valueOf(day) < 1
                    || Integer.valueOf(day) > 31) {
                System.out.println("Wrong day!" + "\n" + "Enter again:");
                day = scanner.nextLine();
            }
        }
        return day;
    }

    private static String setCorrectMonth(LocalDate actualDate, String year) {
        System.out.println("Enter month:");
        String month = scanner.nextLine();
        if (Integer.valueOf(year).equals(actualDate.getYear())) {
            while (!month.matches("[0-9]{2}")
                    || Integer.valueOf(month) < 1
                    || Integer.valueOf(month) > 12
                    || Integer.valueOf(month) < actualDate.getMonthValue()) {
                System.out.println("Wrong month!" + "\n" + "Enter again:");
                month = scanner.nextLine();
            }
        } else {
            while (!month.matches("[0-9]{2}")
                    || Integer.valueOf(month) < 1
                    || Integer.valueOf(month) > 12) {
                System.out.println("Wrong month!" + "\n" + "Enter again:");
                month = scanner.nextLine();
            }
        }
        return month;
    }

    private static String setCorrectYear(LocalDate actualDate, LocalDate maxDate) {
        System.out.println("Enter year:");
        String year = scanner.nextLine();
        while (!year.matches("[0-9]{4}")
                || Integer.valueOf(year) < actualDate.getYear()
                || Integer.valueOf(year) > maxDate.getYear()) {
            System.out.println("Wrong year!" + "\n" + "Enter again:");
            year = scanner.nextLine();
        }
        return year;
    }
}
