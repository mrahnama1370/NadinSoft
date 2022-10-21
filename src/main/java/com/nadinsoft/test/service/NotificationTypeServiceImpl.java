package com.nadinsoft.test.service;

import com.nadinsoft.test.model.NotificationType;
import com.nadinsoft.test.repository.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NotificationTypeServiceImpl implements NotificationTypeService {

    final NotificationTypeRepository notificationTypeRepository;

    public NotificationTypeServiceImpl(NotificationTypeRepository notificationTypeRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
    }

    @Override
    public NotificationType getNotificationType(long notificationTypeId) {
        return notificationTypeRepository
                .findById(notificationTypeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification type " + notificationTypeId + " not found!"));

    }

    @Override
    public List<NotificationType> getNotificationTypes() {

        return notificationTypeRepository.findAll();

    }
}
