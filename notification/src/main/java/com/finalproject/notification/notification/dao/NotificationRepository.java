package com.finalproject.notification.notification.dao;

import com.finalproject.notification.notification.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface NotificationRepository extends MongoRepository<Notification, UUID> {
}
