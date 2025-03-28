package com.tms.controller;

import com.tms.model.Product;
import com.tms.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/create")
    public String getProductCreatePage() {
        return "createProduct";
    }

    @GetMapping("/update-page/{id}")
    public String getProductUpdatePage(@PathVariable("id") Long productId, Model model, HttpServletResponse response) {
        Optional<Product> product = productService.getProductById(productId);
        if (product.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            model.addAttribute("message", "Product not found: id=" + productId);
            return "innerError";
        }
        model.addAttribute("product", product.get());
        return "editProduct";
    }

    // Create
    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, HttpServletResponse response, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "createProduct";
        }

        Optional<Product> createdProduct = productService.createProduct(product);
        if (createdProduct.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Product not created");
            return "innerError";
        }
        model.addAttribute("product", createdProduct.get());
        return "product";
    }

    //Read
    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model, HttpServletResponse response) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            model.addAttribute("message", "Product not found: id=" + id);
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK); //200
        model.addAttribute("product", product.get());
        return "product";
    }

    // Update
    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "editProduct";
        }

        Optional<Product> productUpdated = productService.updateProduct(product);
        if (productUpdated.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Product not updated.");
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("product", productUpdated.get());
        return "product";
    }

    //Delete
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId, Model model, HttpServletResponse response) {
        boolean isDeleted = productService.deleteProduct(productId);

        if (!isDeleted) {
            response.setStatus(HttpServletResponse.SC_CONFLICT); // 409
            model.addAttribute("message", "Product not deleted.");
            return "innerError";
        }

        response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204
        return null;
    }

}








