package com.finalproject.order.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostumerNotificationResponseDTO {

    private String message;
    private Boolean success;
    private String message_id;
}
