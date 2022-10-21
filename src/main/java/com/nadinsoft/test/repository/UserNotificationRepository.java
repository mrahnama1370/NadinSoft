package com.nadinsoft.test.repository;

import com.nadinsoft.test.model.UserNotification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {

    @Query("select un from UserNotification un where un.notification.id = :notificationId and un.user.id = :userId")
    UserNotification findUserNotification(long userId, long notificationId);

    @Query("select un from UserNotification un where un.user.id = :userId")
    List<UserNotification> findNotificationsByUserId(long userId, Pageable pageable);

}
