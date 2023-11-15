package com.finalproject.order.order.controller;

import com.finalproject.order.order.dto.OrderCreateCmdDTO;
import com.finalproject.order.order.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders-stock")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderCreateCmdDTO createNewOrder(HttpServletResponse servletResponse, @RequestBody OrderCreateCmdDTO orderCreateCmdDTO){
        try {
            return orderService.createNewOrder(orderCreateCmdDTO);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}
