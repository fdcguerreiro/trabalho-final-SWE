package com.finalproject.notification.notification.entity;

import com.finalproject.notification.notification.dto.CostumerNotificationRequestDTO;
import com.finalproject.notification.notification.dto.CostumerNotificationResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document("notifications")
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    private String id;
    @Field(name="request_payload")
    private CostumerNotificationRequestDTO request_payload;
    @Field(name="response_payload")
    private CostumerNotificationResponseDTO response_payload;
    @Field(name="send_date_time")
    private Date send_date_time;
    @Field(name="sms_provider_reference")
    private String sms_provider_reference;
    @Field(name="process_success")
    private Boolean process_success;
}
