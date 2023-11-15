package com.finalproject.product.dto;

import java.util.UUID;

public class ProductsCreateCmdDTO {

    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private Boolean active;
    private UUID category_id;

    public ProductsCreateCmdDTO() {
    }

    public ProductsCreateCmdDTO(String name, String description, Integer stock, Double price, Boolean active, UUID category_id) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.active = active;
        this.category_id = category_id;
    }

    public Boolean getActive() {return active;}
    public void setActive(Boolean active) {this.active = active;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {return price;}

    public void setPrice(Double price) {this.price = price;}

    public UUID getCategory_id() {
        return category_id;
    }

    public void setCategory_id(UUID category_id) {
        this.category_id = category_id;
    }
}
