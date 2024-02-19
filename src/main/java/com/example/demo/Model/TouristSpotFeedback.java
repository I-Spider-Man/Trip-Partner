package com.example.demo.Model;

import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TouristSpotFeedback {
    @Id
    private String id;
    private Integer spotId;
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

        public void setSubmissionDate(LocalDate submissionDate) {
            this.submissionDate = submissionDate;
        }

        public Feedback(Integer userId, String feedback, Integer ratings, LocalDate submissionDate) {
            this.userId = userId;
            this.feedback = feedback;
            this.ratings = ratings;
            this.submissionDate = submissionDate;
        }
    }

    public TouristSpotFeedback() {
    }

    @Override
    public String toString() {
        return "TouristSpotFeedback{" +
                "id='" + id + '\'' +
                ", spotId=" + spotId +
                ", feedbackList=" + feedbackList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSpotId() {
        return spotId;
    }

    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(Feedback feedbackList) {
        this.feedbackList.add(feedbackList);
    }

    public TouristSpotFeedback(String id, Integer spotId, List<Feedback> feedbackList) {
        this.id = id;
        this.spotId = spotId;
        this.feedbackList = feedbackList;
    }
}
