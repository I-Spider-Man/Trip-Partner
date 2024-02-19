package com.example.demo.Model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Document("organizerRating")
public class OrganizerRating {
    @Id
    private String id;
    private Integer organizerId;
    private List<Ratings> ratingsList=new ArrayList<>();
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

    public OrganizerRating() {
    }

    @Override
    public String toString() {
        return "OrganizerRating{" +
                "id='" + id + '\'' +
                ", organizerId=" + organizerId +
                ", ratingsList=" + ratingsList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public List<Ratings> getRatingsList() {
        return ratingsList;
    }
    public void setRatingList(Ratings ratings){
        this.ratingsList.add(ratings);
        updateRating();
    }
    public void setRatingsList(List<Ratings> ratingsList) {
        this.ratingsList = ratingsList;
        updateRating();
    }

    public OrganizerRating(String id, Integer organizerId, List<Ratings> ratingsList) {
        this.id = id;
        this.organizerId = organizerId;
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
