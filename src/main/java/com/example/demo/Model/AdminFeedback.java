package com.example.demo.Model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(value = "AdminFeedBack")
public class AdminFeedback {
    @Id
    private String id;
    private Integer userId;
    private String feedBack;
    private LocalDate feedBackDate;
    private Boolean indicate;
    private String adminReply;

    public AdminFeedback() {
    }



    public String getAdminReply() {
        return adminReply;
    }

    public void setAdminReply(String adminReply) {
        this.adminReply = adminReply;
    }

    public AdminFeedback(String id, Integer userId, String feedBack, LocalDate feedBackDate, Boolean indicate, String adminReply) {
        this.id = id;
        this.userId = userId;
        this.feedBack = feedBack;
        this.feedBackDate = feedBackDate;
        this.indicate = indicate;
        this.adminReply = adminReply;
    }

    public Boolean getIndicate() {
        return indicate;
    }

    public void setIndicate(Boolean indicate) {
        this.indicate = indicate;
    }

     public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    @Override
    public String toString() {
        return "AdminFeedback{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", feedBack='" + feedBack + '\'' +
                ", feedBackDate=" + feedBackDate +
                ", indicate=" + indicate +
                ", adminReply='" + adminReply + '\'' +
                '}';
    }

    public LocalDate getFeedBackDate() {
        return feedBackDate;
    }

    public void setFeedBackDate() {
        this.feedBackDate = LocalDate.now();
    }
}
