package com.finalproject.notification.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data

public class CostumerNotificationResponseDTO {

    private String message;
    private Boolean success;
    private String message_id;

    public CostumerNotificationResponseDTO() {
    }

    public CostumerNotificationResponseDTO(@JsonProperty("message")String message, @JsonProperty("success")Boolean success, @JsonProperty("message_id")String message_id) {
        this.message = message;
        this.success = success;
        this.message_id = message_id;
    }
}
