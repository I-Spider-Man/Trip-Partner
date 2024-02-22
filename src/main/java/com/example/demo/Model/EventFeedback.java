package com.example.demo.Model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document("EventFeedBack")
public class EventFeedback {
    @Id
    private String id;
    private Integer eventId;
    private List<Feedback> feedbackList=new ArrayList<>(1);
    public static class Feedback{
        private Integer userId;
        private String feedback;
        private Integer ratings;
        private LocalDate submissionDate;

        public Feedback() {
        }

        @Override
        public String toString() {
            return "Feedback{" +
                    "userId=" + userId +
                    ", feedback='" + feedback + '\'' +
                    ", ratings=" + ratings +
                    ", submissionDate=" + submissionDate +
                    '}';
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public Integer getRatings() {
            return ratings;
        }

        public void setRatings(Integer ratings) {
            this.ratings = ratings;
        }

        public LocalDate getSubmissionDate() {
            return submissionDate;
        }

        public void setSubmissionDate() {
            this.submissionDate = LocalDate.now();
        }

        public Feedback(Integer userId, String feedback, Integer ratings, LocalDate submissionDate) {
            this.userId = userId;
            this.feedback = feedback;
            this.ratings = ratings;
            this.submissionDate = submissionDate;
        }
    }

    public EventFeedback() {
    }

    @Override
    public String toString() {
        return "EventFeedback{" +
                "id='" + id + '\'' +
                ", eventId=" + eventId +
                ", feedbackList=" + feedbackList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
    public void setFeedbackList(Feedback feedback){
        this.feedbackList.add(feedback);
    }
    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public EventFeedback(String id, Integer eventId, List<Feedback> feedbackList) {
        this.id = id;
        this.eventId = eventId;
        this.feedbackList = feedbackList;
    }
}
