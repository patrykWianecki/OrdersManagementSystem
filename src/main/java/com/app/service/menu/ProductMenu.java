package com.app.service.menu;

import java.math.BigDecimal;
import java.util.Scanner;

import com.app.model.dto.CategoryDto;
import com.app.model.dto.ProducerDto;
import com.app.model.dto.ProductDto;
import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.State;
import com.app.repository.category.CategoryRepository;
import com.app.repository.category.CategoryRepositoryImpl;
import com.app.repository.producer.ProducerRepository;
import com.app.repository.producer.ProducerRepositoryImpl;
import com.app.service.CategoryService;
import com.app.service.MyService;
import com.app.service.ProducerService;
import com.app.service.ProductService;
import com.app.validator.ProductValidator;
import com.app.validator.ToolsValidator;

class ProductMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static State state;

    private MyService myService = new MyService();
    private CategoryService categoryService = new CategoryService();
    private ProductService productService = new ProductService();
    private ProducerService producerService = new ProducerService();
    private ProductValidator productValidator = new ProductValidator();
    private ToolsValidator toolsValidator = new ToolsValidator();
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private ProducerRepository producerRepository = new ProducerRepositoryImpl();

    State printProduct() {
        System.out.println("1 - add new Product");
        System.out.println("0 - exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                state = addProduct();
                break;
            case 0:
                state = State.INIT;
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.PRODUCT;
                break;
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
        long producerId = myService.oneProductNameAndCategoryFromOneProducer(name, categoryId, toolsValidator.chooseId(scanner.nextLine()));

        productService.addProduct(ProductDto
            .builder()
            .name(name)
            .price(price)
            .categoryDto(CategoryDto
                .builder()
                .name(categoryRepository
                    .findOne(categoryId)
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))
                    .getName())
                .build())
            .producerDto(ProducerDto
                .builder()
                .name(producerRepository
                    .findOne(producerId)
                    .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "TODO"))
                    .getName())
                .build())
            .build());
        state = State.PRODUCT;
        return state;
    }
}
