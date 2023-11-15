package com.finalproject.product.service;

import com.finalproject.product.dao.CategoryRepository;
import com.finalproject.product.dao.ProductRepository;
import com.finalproject.product.dto.ProductsCreateCmdDTO;
import com.finalproject.product.entity.Category;
import com.finalproject.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(UUID id){
        /*Optional<Product> product = productRepository.findById(id);
        product.isPresent();*/
        return productRepository.findById(id).get();
    }

    public List<Product> find(String term, UUID categoryId){

        if(term == null && categoryId == null){
            return productRepository.findActive();
        }
        else if(term != null && categoryId == null){
            return productRepository.findBySearchTerm(term);
        }
        else if(term == null && categoryId != null){
            return productRepository.findByCategory(categoryId);
        }
        else return  productRepository.findByCategorySearchTerm(term, categoryId);
    }

    public void createNewProduct(ProductsCreateCmdDTO pcCmdDTO){

        Product product = new Product();
        Category category = categoryRepository.findById(pcCmdDTO.getCategory_id()).get();

        product.setName(pcCmdDTO.getName());
        product.setDescription(pcCmdDTO.getDescription());
        product.setStock(pcCmdDTO.getStock());
        product.setPrice(pcCmdDTO.getPrice());
        product.setActive(pcCmdDTO.getActive());
        product.setCategory(category);

        productRepository.save(product);

    }

    public void createNewProduct(Product productDTO){

        Product product = new Product();
        Category category = categoryRepository.findById(productDTO.getCategories().getId()).get();

        product.setCategories(category);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setPrice(productDTO.getPrice());
        product.setActive(productDTO.getActive());
        productRepository.save(product);
    }

    public void updateProduct (UUID id, Product product){

        Product existingProduct = productRepository.findById(id).get();

        Category existingCategory = categoryRepository.findById(product.getCategory().getId()).get();

        if(product.getName() != null){
            existingProduct.setName(product.getName());
        }
        if(product.getDescription()!=null){
            existingProduct.setDescription(product.getDescription());
        }
        if(product.getStock() != null){
            existingProduct.setStock(product.getStock());
        }
        if(product.getPrice()!= null){
            existingProduct.setPrice(product.getPrice());
        }
        if(product.getActive()!=null){
            existingProduct.setActive(product.getActive());
        }
        if(product.getCategory()!=null){
            existingProduct.setCategory(existingCategory);
        }
        productRepository.save(existingProduct);
    }
    public List<Product> findProductsByCategoryId(UUID id) {
        return productRepository.findByCategory(id);
    }
    public void deleteProduct (UUID id){

        Product existingProduct = productRepository.findById(id).get();

        existingProduct.setActive(false);
        productRepository.save(existingProduct);
    }

}
