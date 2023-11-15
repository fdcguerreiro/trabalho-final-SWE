package com.finalproject.notification.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CostumerNotificationRequestDTO {

    private String message;
    private String to;
    private String sender_id;

    public CostumerNotificationRequestDTO() {
    }

    public CostumerNotificationRequestDTO(@JsonProperty("message")String message, @JsonProperty("to")String to, @JsonProperty("sender_id")String sender_id) {
        this.message = message;
        this.to = to;
        this.sender_id = sender_id;
    }
}
