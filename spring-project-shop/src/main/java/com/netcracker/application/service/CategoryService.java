package com.netcracker.application.service;

import com.netcracker.application.service.model.entity.Category;
import com.netcracker.application.service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CategoryService {
    private final Map<BigInteger, Category> categories = new HashMap<>();
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private void fill() {
        if (categories.isEmpty()) {
            for (Category category : categoryRepository.findAll()) {
                categories.put(category.getId(), category);
            }
        }
    }

    public List<Category> getAll() {
        fill();
        return new ArrayList<>(categories.values());
    }

    public Category getById(BigInteger id) {
        fill();
        return categories.get(id);
    }

    public void add(Category category) {
        categoryRepository.save(category);
        categories.clear();
    }

    public void delete(BigInteger id) {
        categoryRepository.delete(categories.get(id));
        categories.remove(id);
    }
}
