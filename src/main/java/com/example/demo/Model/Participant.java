package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
public class Participant {
    @Id
    @GeneratedValue
    private Integer participantId;
    @Column(name = "user_id", unique = true)
    private Integer userId;
    private Integer groupId;
    private Integer participationCount = 0;
    private UserStatus participantStatus =UserStatus.Busy;
    private Role role=Role.Participant_Role;
    private String participantRating;

    @Override
    public String toString() {
        return "Participant{" +
                "participantId=" + participantId +
                ", userId=" + userId +
                ", groupId=" + groupId +
                ", participationCount=" + participationCount +
                ", participantStatus=" + participantStatus +
                ", role=" + role +
                ", participantRating='" + participantRating + '\'' +
                '}';
    }

    public String getParticipantRating() {
        return participantRating;
    }

    public void setParticipantRating(String participantRating) {
        this.participantRating = participantRating;
    }

    public Participant(Integer participantId, Integer userId, Integer groupId, Integer participationCount, UserStatus participantStatus, Role role, String participantRating) {
        this.participantId = participantId;
        this.userId = userId;
        this.groupId = groupId;
        this.participationCount = participationCount;
        this.participantStatus = participantStatus;
        this.role = role;
        this.participantRating = participantRating;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getParticipationCount() {
        return participationCount;
    }

    public void setParticipationCount(Integer participationCount) {
        this.participationCount = participationCount;
    }
    public void decreaseParticipationCount(Integer participationCount){this.participationCount = participationCount-1;}
    public void increaseParticipationCount() {
        this.participationCount = this.participationCount + 1;
    }

    public UserStatus getParticipantStatus() {
        return participantStatus;
    }

    public void setParticipantStatus(UserStatus participantStatus) {
        this.participantStatus = participantStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Participant() {
    }

}
