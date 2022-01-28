package com.netcracker.application.service;

import com.netcracker.application.service.model.entity.Category;
import com.netcracker.application.service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.*;

@Component
public class CategoryService {
    private final Map<BigInteger, Category> categories = new HashMap<>();
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
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

    public boolean isStillInUse(BigInteger id) {
        return productService.getAll()
                .stream()
                .filter(p -> !p.getIsDeleted())
                .anyMatch(p -> p.getCategories()
                        .stream()
                        .anyMatch(c -> c.getId().equals(id)));
    }

    public void decreaseByOne(Set<Category> categories) {
        categories.forEach(c -> c.setProductsAmount(c.getProductsAmount() - 1));
    }
}
