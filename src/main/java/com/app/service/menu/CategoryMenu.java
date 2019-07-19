package com.app.service.menu;

import java.util.Scanner;

import com.app.model.dto.CategoryDto;
import com.app.model.State;
import com.app.service.CategoryService;
import com.app.validator.CategoryValidator;

import static com.app.model.State.*;

class CategoryMenu {

    private static Scanner scanner = new Scanner(System.in);

    private CategoryService categoryService = new CategoryService();
    private CategoryValidator categoryValidator = new CategoryValidator();

    State printCategory() {
        System.out.println("0 - exit");
        System.out.println("1 - add new Category");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0: {
                return INIT;
            }
            case 1: {
                return addCategory();
            }
            default: {
                System.out.println("Wrong choice!");
                return CATEGORY;
            }
        }
    }

    private State addCategory() {
        System.out.println("Enter Category name:");
        String name = categoryValidator.validateCategoryName(scanner.nextLine());

        categoryService.addCategory(CategoryDto.builder().name(name).build());

        return CATEGORY;
    }
}
