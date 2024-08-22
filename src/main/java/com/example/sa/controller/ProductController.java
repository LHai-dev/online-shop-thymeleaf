package com.example.sa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }

    @GetMapping("/create")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "products/form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product, @RequestParam("images") MultipartFile[] files) {
        productService.save(product);
        // Save each image
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String filePath = "path/to/your/upload/directory/" + fileName;
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image image = new Image();
            image.setFilePath(filePath);
            product.addImage(image);
        }

        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "products/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }



}
