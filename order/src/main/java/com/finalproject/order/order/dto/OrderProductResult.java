package com.finalproject.order.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductResult {

    private UUID id;
    private Integer quantity;
    private Boolean success;

}
