package com.netcracker.application.controller;

import com.netcracker.application.controller.form.SearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalogue/search")
public class ProductSearchController {

    @GetMapping
    public String beforeSearch(Model model) {
        model.addAttribute("searchForm", new SearchForm());
        return "search/before";
    }

    @PostMapping
    public String beforeSearch(@ModelAttribute("searchForm") SearchForm searchForm) {

    }
}
