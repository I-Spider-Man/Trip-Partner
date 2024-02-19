package com.example.demo.Model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("participantRating")
public class ParticipantRating {
    @Id
    private String id;
    private Integer participantId;
    private List<Ratings> ratingsList= new ArrayList<>(1);
    private double rating;
    public static class Ratings{
        private Integer userId;
        private double rating;

        public Ratings() {
        }

        @Override
        public String toString() {
            return "Ratings{" +
                    "userId=" + userId +
                    ", rating=" + rating +
                    '}';
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public Ratings(Integer userId, double rating) {
            this.userId = userId;
            this.rating = rating;
        }
    }

    public ParticipantRating() {
    }

    @Override
    public String toString() {
        return "ParticipantRating{" +
                "id='" + id + '\'' +
                ", participantId=" + participantId +
                ", ratingsList=" + ratingsList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public List<Ratings> getRatingsList() {
        return ratingsList;
    }
    public void setRatingsList(Ratings ratings){
        this.ratingsList.add(ratings);
        updateRating();
    }

    public void setRatingsList(List<Ratings> ratingsList) {
        this.ratingsList = ratingsList;
        updateRating();
    }

    public ParticipantRating(String id, Integer participantId, List<Ratings> ratingsList) {
        this.id = id;
        this.participantId = participantId;
        this.ratingsList = ratingsList;
    }
    private void updateRating() {
        if (ratingsList.isEmpty()) {
            rating = 0.0; // Set rating to 0 if no ratings available
        } else {
            double totalRating = 0.0;
            for (Ratings r : ratingsList) {
                totalRating += r.getRating();
            }
            rating = totalRating / ratingsList.size(); // Calculate average rating
        }
    }
}
