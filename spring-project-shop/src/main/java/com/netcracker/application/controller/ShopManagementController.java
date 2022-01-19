package com.netcracker.application.controller;

import com.netcracker.application.service.MakerService;
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

    @Autowired
    public ShopManagementController(MakerService makerService) {
        this.makerService = makerService;
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
    public String edit(@PathVariable BigInteger id, ModelMap modelMap) {
        Maker maker = makerService.getById(id);
        modelMap.addAttribute("maker", maker);
        return "management/maker/add";
    }

    @GetMapping("maker/{id}/delete")
    public String delete(@PathVariable BigInteger id) {
        makerService.delete(id);
        return "redirect:/management/maker";
    }

}
