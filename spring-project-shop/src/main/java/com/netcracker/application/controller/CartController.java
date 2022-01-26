package com.netcracker.application.controller;

import com.netcracker.application.service.CartService;
import com.netcracker.application.service.UserServiceImpl;
import com.netcracker.application.service.model.entity.Product;
import com.netcracker.application.service.model.parser.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
public class CartController {
    private final CartService cartService;
    private final UserServiceImpl userService;

    @Autowired
    public CartController(CartService cartService, UserServiceImpl userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping
    public String displayCart(Model model) {
        List<Product> cart = userService.getCurrentUser().getCart();
        if (cart.size() < 1) {
            model.addAttribute("nothing", true);
        } else {
            Map<BigInteger, String> jsonMap = JsonParser.parseToMap(cart);
            model.addAttribute("nothing", false);
            model.addAttribute("jsonMap", jsonMap);
            model.addAttribute(
                    "amountOfProducts",
                    "Amount of Products in the cart: " + cart.size());
            model.addAttribute("totalSum", "Subtotal: " + cartService.getTotalCost(cart));
        }
        return "catalogue/cart";
    }

    @GetMapping("/delete/{productId}")
    public String deleteFromCart(@PathVariable BigInteger productId) {
        cartService.deleteFromCart(productId, userService.getCurrentUser());
        return "redirect:/cart";
    }
}
