package com.netcracker.application.controller;

import com.netcracker.application.controller.form.SearchForm;
import com.netcracker.application.service.SearchService;
import com.netcracker.application.service.model.entity.Product;
import com.netcracker.application.service.model.parser.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/catalogue/search")
public class ProductSearchController {
    private final SearchService searchService;

    @Autowired
    public ProductSearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public String beforeSearch(Model model) {
        SearchForm searchForm = new SearchForm();
        searchForm.setMinPrice(SearchForm.MIN_PRICE);
        searchForm.setMaxPrice(SearchForm.MAX_PRICE);
        model.addAttribute("searchForm", searchForm);
        return "search/before";
    }

    @PostMapping
    public String beforeSearch(@ModelAttribute("searchForm") SearchForm searchForm, Model model) {
        List<Product> resultOfSearch = searchService.getResult(searchForm);
        if (resultOfSearch.size() == 0) {
            model.addAttribute("nothing", true);
        } else {
            List<String> json = JsonParser.parse(resultOfSearch);
            model.addAttribute("resultSearchList", json);
        }
        return "search/after";
    }
}
