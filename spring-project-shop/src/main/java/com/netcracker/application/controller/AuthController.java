package com.netcracker.application.controller;

import com.netcracker.application.controller.form.UserRegistrationForm;
import com.netcracker.application.service.UserServiceImpl;
import com.netcracker.application.service.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/registration")
public class AuthController {
    private final UserServiceImpl userService;

    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String registration(Model model) {
        model.addAttribute("user", new UserRegistrationForm());
        return "auth/registration";
    }

    @PostMapping()
    public String registration(
            @ModelAttribute("userForm") UserRegistrationForm userRegistrationForm,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            model.addAttribute("errors", errorMessages);
            return "auth/registration";
        }

        userService.createUser(conversionService.convert(userRegistrationForm, User.class));
        return "redirect:/";
    }
}
