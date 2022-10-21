package com.nadinsoft.test.service;

import com.nadinsoft.test.model.Notification;
import com.nadinsoft.test.model.UserNotification;
import com.nadinsoft.test.repository.NotificationRepository;
import com.nadinsoft.test.repository.UserNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    final NotificationRepository notificationRepository;
    final UserNotificationRepository userNotificationRepository;
    final UserServiceImpl userService;
    final NotificationTypeServiceImpl notificationTypeService;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, UserServiceImpl userService, UserNotificationRepository userNotificationRepository, NotificationTypeServiceImpl notificationTypeService) {
        this.notificationRepository = notificationRepository;
        this.userService = userService;
        this.userNotificationRepository = userNotificationRepository;
        this.notificationTypeService = notificationTypeService;
    }


    @Override
    public UserNotification sendNotificationForUser(long userId, long notificationId) {

        UserNotification userNotification = new UserNotification();
        userNotification.setUser(userService.getUser(userId));
        userNotification.setNotification(getNotification(notificationId));

        return userNotificationRepository.save(userNotification);
    }

    @Override
    public UserNotification sendNotificationForUser(long userId, Notification notification) {

        UserNotification userNotification = new UserNotification();
        userNotification.setUser(userService.getUser(userId));
        userNotification.setNotification(notification);

        return userNotificationRepository.save(userNotification);
    }

    @Override
    public List<UserNotification> getUserNotifications(long userId, int pageNo, int pageSize) {

        return userNotificationRepository.findNotificationsByUserId(userId, PageRequest.of(pageNo, pageSize));

    }

    @Override
    public Notification getNotification(long notificationId) {

        return notificationRepository
                .findById(notificationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification " + notificationId + " not found!"));

    }

    @Override
    public UserNotification getUserNotification(long userId, long notificationId) {

        UserNotification userNotification = Optional.ofNullable
                (userNotificationRepository.findUserNotification(userId, notificationId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification " + notificationId + " not found!"));

        if (userNotification.getSeenDate() == null) {
            userNotification.setSeenDate(new Date());
            return userNotificationRepository.save(userNotification);
        }

        return userNotification;

    }

    @Override
    public Notification addNewNotification(Long notificationTypeId, Notification notification) {

        notification.setNotificationType(notificationTypeService.getNotificationType(notificationTypeId));

        return notificationRepository.save(notification);

    }
}
