package com.nadinsoft.test.service;

import com.nadinsoft.test.model.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UserService {

    User getUser(long userId) throws ChangeSetPersister.NotFoundException;

    User saveUser(User user);

    List<User> getUsersOfUserType(long userTypeId);

}
