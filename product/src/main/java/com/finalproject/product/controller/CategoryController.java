package com.finalproject.product.controller;

import com.finalproject.product.dto.CategoryCreateCmdDTO;
import com.finalproject.product.entity.Category;
import com.finalproject.product.service.CategoryService;
import com.finalproject.product.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> categoriesList(HttpServletResponse servletResponse){
        try {
            return categoryService.getAllCategories();
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
    @PostMapping
    public void createNewCategory(HttpServletResponse servletResponse,@RequestBody CategoryCreateCmdDTO categoryCreateCmdDTO){
        try {
            categoryService.createNewCategory(categoryCreateCmdDTO);
            servletResponse.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public Category getCategoryById(HttpServletResponse servletResponse,@PathVariable UUID id){
        try {
            return categoryService.findCategoryById(id);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }
    @PatchMapping("/{id}")
    public void updateCategory(HttpServletResponse servletResponse,@PathVariable UUID id,@RequestBody Category category){
        try {
            categoryService.updateCategory(id, category);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(HttpServletResponse servletResponse,@PathVariable  UUID id){
        try {
            categoryService.deleteCategory(id);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
