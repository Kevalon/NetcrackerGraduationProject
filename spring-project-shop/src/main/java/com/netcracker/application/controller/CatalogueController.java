package com.netcracker.application.controller;

import com.netcracker.application.service.ProductService;
import com.netcracker.application.service.model.entity.Product;
import com.netcracker.application.service.model.parser.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/catalogue")
public class CatalogueController {
    private final ProductService productService;

    @Autowired
    public CatalogueController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String products(ModelMap model) {
        List<Product> products = productService.getAll();
        List<String> json = JsonParser.parse(products);
        model.addAttribute("json", json);
        return "catalogue/list";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getProduct(@PathVariable BigInteger id, ModelMap model) {
        Product product = productService.getById(id);
        String json = JsonParser.parse(product);
        model.addAttribute("json", json);
        return "catalogue/one";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProduct(ModelMap model) {
        model.addAttribute("product", new Product());
        return "catalogue/add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProduct(Product product, String maker) {
        productService.addProduct(product);
        return "redirect:/catalogue";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String edit(@PathVariable BigInteger id, ModelMap modelMap) {
        Product product = productService.getById(id);
        modelMap.addAttribute("product", product);
        return "catalogue/add";
    }

    @GetMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable BigInteger id) {
        productService.deleteProduct(id);
        return "redirect:/catalogue";
    }
}
