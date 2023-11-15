package com.finalproject.product.dao;

import com.finalproject.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("FROM Product p WHERE (p.name = :search_term OR p.description = :search_term) AND p.active = true")
    List<Product> findBySearchTerm(@Param("search_term") String searchTerm);

    @Query("FROM Product p WHERE (p.name = :search_term OR p.description = :search_term) AND p.category.id = :category_id")
    List<Product> findByCategorySearchTerm(@Param("search_term") String searchTerm,@Param("category_id") UUID categoryId);

    @Query("FROM Product p WHERE p.category.id = :category_id AND p.active = true")
    List<Product> findByCategory(@Param("category_id") UUID categoryId);

    @Query("FROM Product p WHERE p.active = true")
    List<Product> findActive();

}
