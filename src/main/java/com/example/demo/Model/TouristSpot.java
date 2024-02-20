package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
public class TouristSpot {
    @Id
    @GeneratedValue
    private Integer spotId;
    private String spotName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "addressId")
    private SpotAddress location;
    private String description;
    private Integer peopleCount=0;
    private String spotPicture;
    private String spotFeedback;

    public String getSpotFeedback() {
        return spotFeedback;
    }

    public void setSpotFeedback(String spotFeedback) {
        this.spotFeedback = spotFeedback;
    }

    public String getSpotPicture() {
        return spotPicture;
    }

    public void setSpotPicture(String spotPicture) {
        this.spotPicture = spotPicture;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void increasePeopleCount(Integer peopleCount) {
        this.peopleCount += peopleCount;
    }

    public void decreasePeopleCount(Integer peopleCount) {
        this.peopleCount -= peopleCount;
    }

    public TouristSpot() {
    }

    public Integer getSpotId() {
        return spotId;
    }

    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
    }

    public String getSpotName() {
        return spotName;
    }

    @Override
    public String toString() {
        return "TouristSpot{" +
                "spotId=" + spotId +
                ", spotName='" + spotName + '\'' +
                ", location=" + location +
                ", description='" + description + '\'' +
                ", peopleCount=" + peopleCount +
                ", spotPicture='" + spotPicture + '\'' +
                ", spotFeedback='" + spotFeedback + '\'' +
                '}';
    }

    public SpotAddress getLocation() {
        return location;
    }

    public void setLocation(SpotAddress location) {
        this.location = location;
    }

    public TouristSpot(Integer spotId, String spotName, SpotAddress location, String description, Integer peopleCount, String spotPicture, String spotFeedback) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.location = location;
        this.description = description;
        this.peopleCount = peopleCount;
        this.spotPicture = spotPicture;
        this.spotFeedback = spotFeedback;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
