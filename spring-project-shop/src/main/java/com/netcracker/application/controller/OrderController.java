package com.netcracker.application.controller;

import com.netcracker.application.service.CartService;
import com.netcracker.application.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final CartService cartService;
    private final UserServiceImpl userService;

    @Autowired
    public OrderController(CartService cartService, UserServiceImpl userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/confirm")
    public String confirmOrder (Model model) {
        if (!cartService.areProductsStillAvailable(userService.getCurrentUser())) {
            model.addAttribute("error", true);
            return "order/confirm";
        }
    }
}
