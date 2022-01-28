package com.netcracker.application.service;

import com.netcracker.application.controller.form.ProductDisplayForm;
import com.netcracker.application.service.model.entity.Maker;
import com.netcracker.application.service.model.entity.Product;
import com.netcracker.application.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductService {
    private final Map<BigInteger, Product> products = new HashMap<>();
    private final ProductRepository productRepository;
    private final MakerService makerService;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            MakerService makerService,
            CategoryService categoryService) {
        this.productRepository = productRepository;
        this.makerService = makerService;
        this.categoryService = categoryService;
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
        if (Objects.isNull(product.getId())) {
            Maker maker = makerService.getById(product.getMakerId());
            maker.setProductsAmount(maker.getProductsAmount() + 1);
            makerService.update(maker);
        }

        productRepository.save(product);
        products.clear();
    }

    public void deleteProduct(BigInteger id) {
        Product product = getById(id);

        Maker maker = makerService.getById(product.getMakerId());
        maker.setProductsAmount(maker.getProductsAmount() - 1);
        makerService.update(maker);

        categoryService.decreaseByOne(product.getCategories());

        product.setIsDeleted(true);
        productRepository.save(product);
        products.clear();
    }

    public void buyOne(BigInteger productId) {
        Product product = getById(productId);
        product.setAmountInShop(product.getAmountInShop() - 1);
        productRepository.save(product);
        products.clear();
    }

    public ProductDisplayForm convertProductToProductDisplayForm(Product product) {
        ProductDisplayForm productDisplayForm = new ProductDisplayForm();
        productDisplayForm.setName(product.getName());
        productDisplayForm.setProductId(product.getId());
        productDisplayForm.setAmount(product.getAmountInShop());
        productDisplayForm.setCategories(product.getCategories());
        productDisplayForm.setDescription(product.getDescription());
        productDisplayForm.setMakerName(makerService.getById(product.getMakerId()).getName());
        productDisplayForm.setPriceWithDiscount(product.getPrice()
                * Optional.ofNullable(product.getDiscount()).orElse(1.0));

        return productDisplayForm;
    }

    public List<ProductDisplayForm> getListOfProductDisplayForm(List<Product> products) {
        return products.stream()
                .filter(p -> !p.getIsDeleted())
                .map(this::convertProductToProductDisplayForm)
                .collect(Collectors.toList());
    }
}
