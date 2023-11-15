package com.finalproject.product.controller;

import com.finalproject.product.dto.InventoryCheckDTO;
import com.finalproject.product.dto.OrderProductResult;
import com.finalproject.product.service.InventoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders-stock")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping
    public OrderProductResult createInventoryRequest(HttpServletResponse servletResponse, @RequestBody InventoryCheckDTO inventoryCheckDTO){

        try {
            servletResponse.setStatus(HttpServletResponse.SC_CREATED);
            return inventoryService.createInventoryRequest(inventoryCheckDTO);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

}
