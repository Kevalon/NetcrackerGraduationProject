package com.netcracker.application.controller;

import com.netcracker.application.controller.form.ProfileEditForm;
import com.netcracker.application.service.CartService;
import com.netcracker.application.service.OrderService;
import com.netcracker.application.service.UserServiceImpl;
import com.netcracker.application.service.model.entity.Order;
import com.netcracker.application.service.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final CartService cartService;
    private final UserServiceImpl userService;
    private final OrderService orderService;

    @Autowired
    public OrderController(CartService cartService, UserServiceImpl userService, OrderService orderService) {
        this.cartService = cartService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/confirm")
    public String confirmOrder (Model model) {
        User user = userService.getCurrentUser();
        if (!cartService.areProductsStillAvailable(user)) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
            model.addAttribute(
                    "profileInfoForm",
                    userService.getProfileEditForm(userService.getCurrentUser()));
            model.addAttribute(
                    "subTotal",
                    "Subtotal: " + cartService.getTotalCost(user.getCart()));
        }
        return "order/confirm";
    }

    @PostMapping("/confirm")
    public String confirmOrder (@ModelAttribute("profileInfoForm") ProfileEditForm profileEditForm, Model model) {
        boolean check;
        try {
            check = orderService.isValid(profileEditForm);
        } catch (IllegalAccessException e) {
            check = false;
            e.printStackTrace();
        }

        if (!check) {
            model.addAttribute("error", false);
            model.addAttribute("profileDataError", true);
            model.addAttribute("profileInfoForm", profileEditForm);
            model.addAttribute(
                    "subTotal",
                    "Subtotal: " + cartService.getTotalCost(userService.getCurrentUser().getCart()));
            return "/order/confirm";
        }
        orderService.formOrder(userService.getCurrentUser(), profileEditForm);
        return "order/success";
    }
}
