package com.app.service;

import java.util.Comparator;

import com.app.model.dto.CategoryDto;
import com.app.model.dto.MyMapper;
import com.app.model.Category;
import com.app.repository.category.CategoryRepository;
import com.app.repository.category.CategoryRepositoryImpl;

public class CategoryService {

    private MyMapper mapper = new MyMapper();
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    public void addCategory(CategoryDto categoryDto) {
        Category category = mapper.fromCategoryDtoToCategory(categoryDto);
        categoryRepository.addOrUpdate(category);
    }

    public void printAllCategories() {
        categoryRepository
            .findAll()
            .stream()
            .sorted(Comparator.comparing(Category::getId))
            .forEach(category -> System.out.println(category.getId() + ". " + category.getName()));
    }
}
