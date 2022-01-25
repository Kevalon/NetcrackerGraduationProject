package com.netcracker.application.controller;

import com.netcracker.application.service.CartService;
import com.netcracker.application.service.ProductService;
import com.netcracker.application.service.UserServiceImpl;
import com.netcracker.application.service.model.entity.Product;
import com.netcracker.application.service.model.entity.User;
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
import java.util.Map;

@Controller
@RequestMapping("/catalogue")
public class CatalogueController {
    private final ProductService productService;
    private final CartService cartService;
    private final UserServiceImpl userService;

    @Autowired
    public CatalogueController(ProductService productService, CartService cartService, UserServiceImpl userService) {
        this.productService = productService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping
    public String products(ModelMap model) {
        List<Product> products = productService.getAll();
        Map<BigInteger, String> jsonMap = JsonParser.parseProducts(products);
        model.addAttribute("jsonMap", jsonMap);
        return "catalogue/list";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public String getProduct(@PathVariable BigInteger id, ModelMap model) {
        Product product = productService.getById(id);
        String json = JsonParser.parse(product);
        model.addAttribute("json", json);
        model.addAttribute("productId", id);
        return "catalogue/one";
    }

    @PostMapping("/{productId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public String addToCart(@PathVariable BigInteger productId, ModelMap model) {
        User curUser = userService.getCurrentUser();
        Product addedProduct = productService.getById(productId);

        if (productService.getById(productId).getAmountInShop()
                - curUser.getProductAmount(addedProduct) == 0) {
            model.addAttribute("error", true);
            return getProduct(productId, model);
        }

        cartService.addToCart(addedProduct);
        model.addAttribute("success", true);
        return getProduct(productId, model);
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProduct(ModelMap model) {
        model.addAttribute("product", new Product());
        return "catalogue/add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProduct(Product product) {
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
