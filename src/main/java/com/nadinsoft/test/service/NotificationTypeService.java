package com.nadinsoft.test.service;

import com.nadinsoft.test.model.NotificationType;
import com.nadinsoft.test.model.UserType;

import java.util.List;

public interface NotificationTypeService {

    NotificationType getNotificationType(long notificationTypeId);

    List<NotificationType> getNotificationTypes();

}
