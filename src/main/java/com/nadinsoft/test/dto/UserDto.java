package com.nadinsoft.test.dto;

import com.nadinsoft.test.model.Notification;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_table")
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_Notification",
            joinColumns = {
                    @JoinColumn(name = "User_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "Notification_ID")})
    private Set<Notification> notifications;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }
}
