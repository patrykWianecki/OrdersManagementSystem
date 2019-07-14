package com.app.service.menu;

import java.util.Scanner;

import com.app.model.dto.CategoryDto;
import com.app.model.State;
import com.app.service.CategoryService;
import com.app.validator.CategoryValidator;

public class CategoryMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static State state;

    private CategoryService categoryService = new CategoryService();
    private CategoryValidator categoryValidator = new CategoryValidator();

    State printCategory() {
        System.out.println("0 - exit");
        System.out.println("1 - add new Category");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0:
                state = State.INIT;
                break;
            case 1:
                state = addCategory();
                break;
            default:
                System.out.println("Wrong choice!");
                state = State.CATEGORY;
                break;
        }
        return state;
    }

    private State addCategory() {
        System.out.println("Enter Category name:");
        String name = categoryValidator.validateCategoryName(scanner.nextLine());

        categoryService.addCategory(CategoryDto
            .builder()
            .name(name)
            .build()
        );

        return State.CATEGORY;
    }
}
