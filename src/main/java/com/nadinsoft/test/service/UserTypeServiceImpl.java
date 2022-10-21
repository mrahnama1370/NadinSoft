package com.nadinsoft.test.service;

import com.nadinsoft.test.model.Notification;
import com.nadinsoft.test.model.User;
import com.nadinsoft.test.model.UserNotification;
import com.nadinsoft.test.model.UserType;
import com.nadinsoft.test.repository.UserTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    final UserTypeRepository userTypeRepository;
    final UserService userService;
    final NotificationService notificationService;

    public UserTypeServiceImpl(UserTypeRepository userTypeRepository, UserService userService, NotificationService notificationService) {
        this.userTypeRepository = userTypeRepository;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @Override
    public List<UserType> getUserTypes() {
        return userTypeRepository.findAll();
    }

    @Override
    public List<Long> sendNotificationForUserType(long userTypeId, long notificationId) {

        Notification notification = notificationService.getNotification(notificationId);
        List<Long> listOfUserIdsThatSentNotif = new ArrayList<>();
        UserNotification userNotification;

        for (User user : userService.getUsersOfUserType(userTypeId)) {
            try {
                userNotification = notificationService.sendNotificationForUser(user.getId(), notification);
                listOfUserIdsThatSentNotif.add(user.getId());
            } catch (Exception e) {

            }

        }

        return listOfUserIdsThatSentNotif;
    }


}
