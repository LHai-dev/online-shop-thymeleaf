package com.example.sa.controller;

import com.example.sa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomePageController {
    private final ProductService productService;

    @GetMapping
    public String homepage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "Homepage";
    }
}
