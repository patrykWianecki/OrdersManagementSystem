package com.app.service.menu;

import java.math.BigDecimal;
import java.util.Scanner;

import com.app.model.State;
import com.app.model.dto.CategoryDto;
import com.app.model.dto.ProducerDto;
import com.app.model.dto.ProductDto;
import com.app.service.CategoryService;
import com.app.service.MyService;
import com.app.service.ProducerService;
import com.app.service.ProductService;
import com.app.validator.ProductValidator;
import com.app.validator.ToolsValidator;

import static com.app.model.State.*;

class ProductMenu {

    private static Scanner scanner = new Scanner(System.in);

    private MyService myService = new MyService();
    private CategoryService categoryService = new CategoryService();
    private ProductService productService = new ProductService();
    private ProducerService producerService = new ProducerService();
    private ProductValidator productValidator = new ProductValidator();
    private ToolsValidator toolsValidator = new ToolsValidator();

    State printProduct() {
        System.out.println("1 - add new Product");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        State state;
        switch (choice) {
            case 1: {
                state = addProduct();
                break;
            }
            case 0: {
                state = INIT;
                break;
            }
            default: {
                System.out.println("Wrong choice!");
                state = PRODUCT;
                break;
            }
        }

        return state;
    }

    private State addProduct() {
        System.out.println("Enter product name");
        String name = productValidator.validateName(scanner.nextLine());

        System.out.println("Enter product price");
        BigDecimal price = productValidator.validatePrice(scanner.nextLine());

        System.out.println("Chose category from list:");
        categoryService.printAllCategories();
        long categoryId = toolsValidator.chooseId(scanner.nextLine());

        System.out.println("Chose producer from list:");
        producerService.printAllProducers();
        long producerId = myService.findAvailableProductName(name, categoryId, toolsValidator.chooseId(scanner.nextLine()));

        productService.addProduct(ProductDto.builder()
            .name(name)
            .price(price)
            .categoryDto(CategoryDto.builder()
                .name(myService.findCategoryByIdWithErrorCheck(categoryId).getName())
                .build())
            .producerDto(ProducerDto.builder()
                .name(myService.findProducerByIdWithErrorCheck(producerId).getName())
                .build())
            .build());

        return PRODUCT;
    }
}
