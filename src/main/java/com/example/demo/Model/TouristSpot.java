package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TouristSpot {
    @Id
    @GeneratedValue
    private Integer spotId;
    private String spotName;
    private String location;
    private String description;
    private Integer peopleCount=0;

    @Override
    public String toString() {
        return "TouristSpot{" +
                "spotId=" + spotId +
                ", spotName='" + spotName + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", peopleCount=" + peopleCount +
                '}';
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


    public TouristSpot(Integer spotId, String spotName, String location, String description, Integer peopleCount) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.location = location;
        this.description = description;

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

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
