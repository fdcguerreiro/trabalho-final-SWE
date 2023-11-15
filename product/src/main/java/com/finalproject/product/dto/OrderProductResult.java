package com.finalproject.product.dto;

import java.util.UUID;


public class OrderProductResult {

    private UUID id;
    private Integer quantity;
    private Boolean success;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public OrderProductResult() {
    }
}
