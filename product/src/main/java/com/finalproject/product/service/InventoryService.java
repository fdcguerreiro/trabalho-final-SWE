package com.finalproject.product.service;

import com.finalproject.product.dao.ProductRepository;
import com.finalproject.product.dto.InventoryCheckDTO;
import com.finalproject.product.dto.OrderProductResult;
import com.finalproject.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    public OrderProductResult createInventoryRequest(InventoryCheckDTO inventoryCheckDTO) {

        try {
            Product product = productRepository.findById(inventoryCheckDTO.getId()).get();

            int productNewStock = product.getStock() - inventoryCheckDTO.getStock();

            OrderProductResult orderProductResult = new OrderProductResult();

            orderProductResult.setId(inventoryCheckDTO.getId());
            orderProductResult.setQuantity(inventoryCheckDTO.getStock());
            orderProductResult.setSuccess(false);

            if (productNewStock >= 0) {
                product.setStock(productNewStock);
                productRepository.save(product);
                orderProductResult.setSuccess(true);

            }

            return orderProductResult;

        } catch (Exception e) {
            System.out.println("Service log: " + e.getMessage());

            return null;
        }
    }
}
