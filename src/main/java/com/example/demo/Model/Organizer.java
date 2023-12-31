package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
public class Organizer {
    @Id
    @GeneratedValue
    private Integer organizerId;
    @Column(name = "user_id", unique = true)
    private Integer userId;
    private UserStatus organizerStatus=UserStatus.Busy;
    private Integer organizedCount=0;
    private Role role=Role.Organizer_Role;

    @Override
    public String toString() {
        return "Organizer{" +
                "organizerId=" + organizerId +
                ", userId=" + userId +
                ", organizerStatus=" + organizerStatus +
                ", organizedCount=" + organizedCount +
                ", role=" + role +
                '}';
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Organizer() {
    }

    public void increseOrganizedCount(Integer organizedCount) {
        this.organizedCount = organizedCount + 1;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public UserStatus getOrganizerStatus() {
        return organizerStatus;
    }

    public void setOrganizerStatus(UserStatus organizerStatus) {
        this.organizerStatus = organizerStatus;
    }

    public Organizer(Integer organizerId, Integer userId, UserStatus organizerStatus, Integer organizedCount, Role role) {
        this.organizerId = organizerId;
        this.userId = userId;
        this.organizerStatus = organizerStatus;
        this.organizedCount = organizedCount;
        this.role = role;
    }

    public Integer getOrganizedCount() {
        return organizedCount;
    }

    public void setOrganizedCount(Integer organizedCount) {
        this.organizedCount = organizedCount;
    }


}
