package com.netcracker.application.service;

import com.netcracker.application.service.model.entity.Product;
import com.netcracker.application.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductService {
    private final Map<BigInteger, Product> products = new HashMap<>();
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private void fill() {
        if (products.isEmpty()) {
            for (Product product : productRepository.findAll()) {
                products.put(product.getId(), product);
            }
        }
    }

    public List<Product> getAll() {
        fill();
        return new ArrayList<>(products.values());
    }

    public Product getById(BigInteger id) {
        fill();
        return products.get(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
        products.clear();
    }

    public void deleteProduct(BigInteger id) {
        productRepository.delete(products.get(id));
        products.remove(id);
    }

    public void updateProduct(BigInteger id, Product product) {
        productRepository.delete(products.get(id));
        productRepository.save(product);
        products.clear();
    }
}
