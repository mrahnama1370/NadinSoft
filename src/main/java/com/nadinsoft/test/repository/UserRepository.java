package com.nadinsoft.test.repository;

import com.nadinsoft.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUserType_Id(long userTypeId);
}
