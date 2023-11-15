package com.finalproject.notification.notification.controller;

import com.finalproject.notification.notification.dto.CostumerNotificationRequestDTO;
import com.finalproject.notification.notification.service.NotificationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public void sendSmsToCostumerRequest(HttpServletResponse servletResponse, @RequestBody CostumerNotificationRequestDTO costumerNotificationRequestDTO){
        try {
            System.out.println(costumerNotificationRequestDTO.toString());
            servletResponse.setStatus(HttpServletResponse.SC_CREATED);
            notificationService.createNewRequest(costumerNotificationRequestDTO);
        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
