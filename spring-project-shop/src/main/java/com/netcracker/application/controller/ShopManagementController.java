package com.netcracker.application.controller;

import com.netcracker.application.service.CategoryService;
import com.netcracker.application.service.MakerService;
import com.netcracker.application.service.model.entity.Category;
import com.netcracker.application.service.model.entity.Maker;
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
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/management")
public class ShopManagementController {
    private final MakerService makerService;
    private final CategoryService categoryService;

    @Autowired
    public ShopManagementController(MakerService makerService, CategoryService categoryService) {
        this.makerService = makerService;
        this.categoryService = categoryService;
    }

    @GetMapping("/maker")
    public String makers(ModelMap model) {
        List<Maker> makers = makerService.getAll();
        List<String> json = JsonParser.parse(makers);
        model.addAttribute("json", json);
        return "management/maker/list";
    }

    @GetMapping("maker/{id}")
    public String getMaker(@PathVariable BigInteger id, ModelMap model) {
        Maker maker = makerService.getById(id);
        String json = JsonParser.parse(maker);
        model.addAttribute("json", json);
        return "management/maker/one";
    }

    @GetMapping("maker/add")
    public String addMaker(ModelMap model) {
        model.addAttribute("maker", new Maker());
        return "management/maker/add";
    }

    @PostMapping("maker/add")
    public String addMaker(Maker maker) {
        makerService.add(maker);
        return "redirect:/management/maker";
    }

    @GetMapping("maker/{id}/edit")
    public String editMaker(@PathVariable BigInteger id, ModelMap modelMap) {
        Maker maker = makerService.getById(id);
        modelMap.addAttribute("maker", maker);
        return "management/maker/add";
    }

    @GetMapping("maker/{id}/delete")
    public String deleteMaker(@PathVariable BigInteger id) {
        makerService.delete(id);
        return "redirect:/management/maker";
    }

    @GetMapping("/category")
    public String categories(ModelMap model) {
        List<Category> categories = categoryService.getAll();
        List<String> json = JsonParser.parse(categories);
        model.addAttribute("json", json);
        return "management/category/list";
    }

    @GetMapping("category/{id}")
    public String getCategory(@PathVariable BigInteger id, ModelMap model) {
        Category category = categoryService.getById(id);
        String json = JsonParser.parse(category);
        model.addAttribute("json", json);
        return "management/category/one";
    }

    @GetMapping("category/add")
    public String addCategory(ModelMap model) {
        model.addAttribute("category", new Category());
        return "management/category/add";
    }

    @PostMapping("category/add")
    public String addCategory(Category category) {
        categoryService.add(category);
        return "redirect:/management/category";
    }

    @GetMapping("category/{id}/edit")
    public String editCategory(@PathVariable BigInteger id, ModelMap modelMap) {
        Category category = categoryService.getById(id);
        modelMap.addAttribute("category", category);
        return "management/category/add";
    }

    @GetMapping("category/{id}/delete")
    public String deleteCategory(@PathVariable BigInteger id) {
        categoryService.delete(id);
        return "redirect:/management/category";
    }

}
