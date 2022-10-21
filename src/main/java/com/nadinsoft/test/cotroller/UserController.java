package com.nadinsoft.test.cotroller;

import com.nadinsoft.test.dto.UserNotificationDto;
import com.nadinsoft.test.model.UserNotification;
import com.nadinsoft.test.service.NotificationServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    final NotificationServiceImpl notificationService;

    final ModelMapper modelMapper;

    @Autowired
    public UserController(NotificationServiceImpl notificationService, ModelMapper modelMapper) {
        this.notificationService = notificationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/users/{userId}/notifications")
    private ResponseEntity<UserNotificationDto> sendNotificationForUser(
            @PathVariable long userId,
            @RequestParam long notificationId) {

        UserNotification userNotification = notificationService.sendNotificationForUser(userId, notificationId);

        return new ResponseEntity<>(modelMapper.map(userNotification, UserNotificationDto.class), HttpStatus.OK);
    }


    @GetMapping("/users/{userId}/notifications")
    private ResponseEntity<List<UserNotificationDto>> getUserNotifications(
            @PathVariable long userId,
            @RequestParam int pageNo,
            @RequestParam int pageSize) {


        List<UserNotification> notifications = notificationService.getUserNotifications(userId, pageNo, pageSize);

        List<UserNotificationDto> userNotificationDtos
                = modelMapper.map(notifications, new TypeToken<List<UserNotificationDto>>() {
        }.getType());

        return new ResponseEntity<>(userNotificationDtos, HttpStatus.OK);

    }


    @GetMapping("/users/{userId}/notifications/{notificationId}")
    private ResponseEntity<UserNotificationDto> getUserNotification(
            @PathVariable long userId,
            @PathVariable long notificationId) {

        UserNotification notification = notificationService.getUserNotification(userId, notificationId);

        return new ResponseEntity<>(modelMapper.map(notification, UserNotificationDto.class), HttpStatus.OK);
    }


}
