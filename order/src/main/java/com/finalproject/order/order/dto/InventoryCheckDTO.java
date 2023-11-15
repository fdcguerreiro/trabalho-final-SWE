package com.finalproject.order.order.dto;
import java.util.UUID;

public class InventoryCheckDTO {

    private UUID id;
    private Integer stock;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public InventoryCheckDTO() {
    }

    @Override
    public String toString() {
        return "InventoryCheckDTO{" +
                "id=" + id +
                ", stock=" + stock +
                '}';
    }
}
