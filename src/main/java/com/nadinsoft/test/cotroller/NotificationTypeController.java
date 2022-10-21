package com.nadinsoft.test.cotroller;

import com.nadinsoft.test.dto.NotificationDto;
import com.nadinsoft.test.dto.NotificationTypeDto;
import com.nadinsoft.test.model.Notification;
import com.nadinsoft.test.model.NotificationType;
import com.nadinsoft.test.service.NotificationServiceImpl;
import com.nadinsoft.test.service.NotificationTypeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationTypeController {

    final NotificationServiceImpl notificationService;
    final NotificationTypeService notificationTypeService;

    final ModelMapper modelMapper;

    @Autowired
    public NotificationTypeController(NotificationServiceImpl notificationService, ModelMapper modelMapper, NotificationTypeService notificationTypeService) {
        this.notificationService = notificationService;
        this.modelMapper = modelMapper;
        this.notificationTypeService = notificationTypeService;
    }

    @PostMapping("/notificationtype/{notificationTypeId}/notifications")
    private ResponseEntity<NotificationDto> addNewNotification(
            @PathVariable Long notificationTypeId,
            @RequestBody NotificationDto notificationDto) {

        Notification notification = modelMapper.map(notificationDto, Notification.class);

        notification = notificationService.addNewNotification(notificationTypeId, notification);

        return new ResponseEntity<>(modelMapper.map(notification, NotificationDto.class), HttpStatus.OK);
    }

    @GetMapping("/notificationtypes")
    private ResponseEntity<List<NotificationTypeDto>> getNotificationTypes() {

        List<NotificationType> notificationTypes = notificationTypeService.getNotificationTypes();

        List<NotificationTypeDto> notificationTypeDtos
                = modelMapper.map(notificationTypes, new TypeToken<List<NotificationTypeDto>>() {
        }.getType());

        return new ResponseEntity<>(notificationTypeDtos, HttpStatus.OK);

    }

}
