package com.netcracker.application.service;

import com.netcracker.application.service.model.entity.Category;
import com.netcracker.application.service.model.entity.Maker;
import com.netcracker.application.service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InstanceAlreadyExistsException;
import java.math.BigInteger;
import java.util.*;

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

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public void add(Category category) throws InstanceAlreadyExistsException {
        if (Objects.isNull(category.getId())) {
            if (Objects.nonNull(categoryRepository.findByName(category.getName()))) {
                throw new InstanceAlreadyExistsException();
            }
        }
        categoryRepository.save(category);
        categories.clear();
    }

    public void update(Category category) {
        fill();
        Category categoryForUpdate = categories.get(category.getId());
        categoryForUpdate.setProductsAmount(category.getProductsAmount());
        categoryRepository.save(categoryForUpdate);
        categories.clear();
    }

    public void delete(BigInteger id) {
        categoryRepository.delete(categories.get(id));
        categories.remove(id);
    }

    public void deleteOneProduct(Category category) {
        category.setProductsAmount(category.getProductsAmount() - 1);
        update(category);
    }
}
