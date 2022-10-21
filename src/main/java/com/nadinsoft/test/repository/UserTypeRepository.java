package com.nadinsoft.test.repository;

import com.nadinsoft.test.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {

}
