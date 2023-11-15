package com.finalproject.order.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostumerNotificationRequestDTO {

    private String message;
    private String to;
    private String sender_id;
}
