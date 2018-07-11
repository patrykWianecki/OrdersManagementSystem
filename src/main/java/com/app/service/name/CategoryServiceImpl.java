package com.app.service.name;

import com.app.model.Category;
import com.app.repository.CategoryRepository;
import com.app.repository.CategoryRepositoryImpl;

import java.util.Comparator;

public class CategoryServiceImpl implements CategoryService {
    private static CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    @Override
    public void printAllCategories() {
        categoryRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Category::getId))
                .forEach(s -> System.out.println(s.getId() + ". " + s.getName()));
    }
}
