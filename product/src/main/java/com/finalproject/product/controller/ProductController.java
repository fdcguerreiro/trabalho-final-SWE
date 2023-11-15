package com.finalproject.product.controller;

import com.finalproject.product.dto.ProductsCreateCmdDTO;
import com.finalproject.product.entity.Product;
import com.finalproject.product.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(HttpServletResponse servletResponse,@PathVariable UUID id){
        try {
            return productService.getProductById(id);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @GetMapping
    public List<Product> findProducts(HttpServletResponse servletResponse,@RequestParam(required = false) String term, @RequestParam(required = false) UUID categoryId){
        try {
            return productService.find(term, categoryId);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping("/category/{id}")
    public List<Product> findProductsByCategoryId(HttpServletResponse servletResponse,@PathVariable UUID id){
        try {
            return productService.findProductsByCategoryId(id);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProduct(@RequestBody ProductsCreateCmdDTO pcCmdDTO){
        productService.createNewProduct(pcCmdDTO);
    }

    @PatchMapping("/{id}")
    public void updateProduct(HttpServletResponse servletResponse,@PathVariable UUID id, @RequestBody Product product){
        try {
            productService.updateProduct(id, product);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(HttpServletResponse servletResponse,@PathVariable UUID id){
        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
