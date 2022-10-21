package com.nadinsoft.test.cotroller;

import com.nadinsoft.test.dto.UserTypeDto;
import com.nadinsoft.test.model.UserType;
import com.nadinsoft.test.service.UserTypeServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserTypeController {

    final UserTypeServiceImpl userTypeService;

    final ModelMapper modelMapper;

    @Autowired
    public UserTypeController(UserTypeServiceImpl userTypeService, ModelMapper modelMapper) {
        this.userTypeService = userTypeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/usertypes")
    private ResponseEntity<List<UserTypeDto>> getUserTypes() {

        List<UserType> userTypes = userTypeService.getUserTypes();

        List<UserTypeDto> userTypeDtos
                = modelMapper.map(userTypes, new TypeToken<List<UserTypeDto>>() {
        }.getType());

        return new ResponseEntity<>(userTypeDtos, HttpStatus.OK);

    }

    @PostMapping("/usertypes/{userTypeId}/notifications")
    private ResponseEntity<List<Long>> sendNotificationForUserType(
            @PathVariable long userTypeId,
            @RequestParam long notificationId) {

        List<Long> listOfUserIdsThatSentNotif = userTypeService.sendNotificationForUserType(userTypeId, notificationId);

        return new ResponseEntity<>(listOfUserIdsThatSentNotif, HttpStatus.OK);
    }
}
