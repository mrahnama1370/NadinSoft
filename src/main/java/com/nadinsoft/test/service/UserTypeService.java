package com.nadinsoft.test.service;

import com.nadinsoft.test.model.UserType;

import java.util.List;

public interface UserTypeService {

    List<UserType> getUserTypes();

    List<Long> sendNotificationForUserType(long userTypeId, long notificationId);

}
