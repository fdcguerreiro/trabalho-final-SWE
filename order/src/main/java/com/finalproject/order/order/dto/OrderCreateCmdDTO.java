package com.finalproject.order.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateCmdDTO {

    private UUID product_id;

    private Integer quantity;

    private String name;

    private String number;

}
