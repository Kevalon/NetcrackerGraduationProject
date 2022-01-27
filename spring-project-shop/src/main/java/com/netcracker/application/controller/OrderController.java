package com.netcracker.application.controller;

import com.netcracker.application.controller.form.ProfileEditForm;
import com.netcracker.application.service.CartService;
import com.netcracker.application.service.OrderService;
import com.netcracker.application.service.UserServiceImpl;
import com.netcracker.application.service.model.entity.User;
import com.netcracker.application.service.model.parser.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
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

    @GetMapping("/customer")
    public String showCustomerOrders(Model model) {
        List<String> jsonList = JsonParser.parse(orderService.getAllForOneUser(userService.getCurrentUser()));
        model.addAttribute("nothing", jsonList.size() < 1);
        model.addAttribute("jsonList", jsonList);
        return "order/customer";
    }

    @GetMapping("/admin")
    public String showAllOrders(Model model) {
        List<String> jsonList = JsonParser.parse(orderService.getAll());
        model.addAttribute("jsonList", jsonList);
        return "order/admin";
    }
}
