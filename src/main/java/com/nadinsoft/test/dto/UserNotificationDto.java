package com.nadinsoft.test.dto;

import java.util.Date;

public class UserNotificationDto {

    private NotificationDto notification;
    private Date seenDate;

    public NotificationDto getNotification() {
        return notification;
    }

    public void setNotification(NotificationDto notification) {
        this.notification = notification;
    }

    public Date getSeenDate() {
        return seenDate;
    }

    public void setSeenDate(Date seenDate) {
        this.seenDate = seenDate;
    }
}
