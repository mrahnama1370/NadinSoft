package com.nadinsoft.test.service;

import com.nadinsoft.test.model.Notification;
import com.nadinsoft.test.model.UserNotification;

import java.util.List;

public interface NotificationService {

    UserNotification sendNotificationForUser(long userId, long notification);

    UserNotification sendNotificationForUser(long userId, Notification notification);

    List<UserNotification> getUserNotifications(long userId, int pageNo, int pageSize);

    Notification getNotification(long notificationId);

    UserNotification getUserNotification(long userId, long notificationId);

    Notification addNewNotification(Long notificationTypeId, Notification notification);
}
