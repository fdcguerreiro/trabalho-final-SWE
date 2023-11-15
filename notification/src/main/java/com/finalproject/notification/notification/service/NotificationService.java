package com.finalproject.notification.notification.service;

import com.finalproject.notification.notification.dao.NotificationRepository;
import com.finalproject.notification.notification.dto.CostumerNotificationRequestDTO;
import com.finalproject.notification.notification.dto.CostumerNotificationResponseDTO;
import com.finalproject.notification.notification.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Date;


@Service
public class NotificationService {

    //private String smsToBearerToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGg6ODA4MC9hcGkvdjEvdXNlcnMvYXBpL2tleS9nZW5lcmF0ZSIsImlhdCI6MTY4MzIwNTI0NCwibmJmIjoxNjgzMjA1MjQ0LCJqdGkiOiJnbGd5OGt0Y3AyT1ptSDgyIiwic3ViIjo0MTk2NTQsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.cDhUIM0YoXlgv-nD6_8FQEa75NIGhtdQYIf8qfBVP5Q";

    @Value("${smsto.bearer.token}")
    private String smsToBearerToken;
    @Autowired
    private NotificationRepository notificationRepository;

    public void createNewRequest(CostumerNotificationRequestDTO costumerNotificationRequestDTO){

        Notification notification = new Notification();

        notification.setRequest_payload(costumerNotificationRequestDTO);
        notification.setSend_date_time(new Date());

        CostumerNotificationResponseDTO costumerNotificationResponseDTO = sendSmsToCostumer(costumerNotificationRequestDTO);

        notification.setResponse_payload(costumerNotificationResponseDTO);
        notification.setSms_provider_reference(costumerNotificationResponseDTO.getMessage_id());
        notification.setProcess_success(costumerNotificationResponseDTO.getSuccess());
        notificationRepository.save(notification);
    }

    public CostumerNotificationResponseDTO sendSmsToCostumer ( CostumerNotificationRequestDTO costumerNotificationRequestDTO){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(smsToBearerToken);

        HttpEntity<CostumerNotificationRequestDTO> requestEntity = new HttpEntity<>(costumerNotificationRequestDTO, headers);

        try {
            ResponseEntity<CostumerNotificationResponseDTO> response = restTemplate.postForEntity("https://api.sms.to/sms/send",requestEntity, CostumerNotificationResponseDTO.class);
            return response.getBody();
        }catch (HttpClientErrorException e){
            return e.getResponseBodyAs(CostumerNotificationResponseDTO.class);
        }
    }

}
