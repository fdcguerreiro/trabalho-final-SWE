package com.finalproject.product.service;

import com.finalproject.product.dao.CategoryRepository;
import com.finalproject.product.dto.CategoryCreateCmdDTO;
import com.finalproject.product.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public void createNewCategory (CategoryCreateCmdDTO ccCmdDto){

        Category category = new Category();

        category.setName(ccCmdDto.getName());

        categoryRepository.save(category);
    }

    public void updateCategory (UUID id, Category category){

        Category existingCategory = categoryRepository.findById(id).get();

        if(category.getName()!=null){
            existingCategory.setName(category.getName());
        }
        categoryRepository.save(existingCategory);
    }

    public void deleteCategory (UUID id){

        categoryRepository.deleteById(id);
    }

    public Category findCategoryById(UUID id) {

        return categoryRepository.findById(id).get();
    }
}
