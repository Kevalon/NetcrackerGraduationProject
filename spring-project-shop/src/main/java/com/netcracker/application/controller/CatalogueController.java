package com.netcracker.application.controller;

import com.netcracker.application.service.ProductService;
import com.netcracker.application.service.model.entity.Product;
import com.netcracker.application.service.model.parser.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getProduct(@PathVariable BigInteger id, ModelMap model) {
        Product product = productService.getById(id);
        String json = JsonParser.parse(product);
        model.addAttribute("json", json);
        return "catalogue/one";
    }

    @GetMapping("/add")
    public String addProduct(ModelMap model) {
        model.addAttribute("product", new Product());
        return "catalogue/add";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable BigInteger id, ModelMap modelMap) {
        Product product = productService.getById(id);
        modelMap.addAttribute("product", product);
        return "catalogue/edit";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/catalogue";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable BigInteger id) {
        productService.deleteProduct(id);
        return "redirect:/catalogue";
    }
}
