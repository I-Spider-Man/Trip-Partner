package com.example.demo.Model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.util.List;

@Document("spotPicture")
public class SpotPicture {
    @Id
    private String id;
    private Integer spotId;
    private List<SpotPictures> spotPicturesList;

    public SpotPicture(String id, Integer spotId, List<SpotPictures> spotPicturesList) {
        this.id = id;
        this.spotId = spotId;
        this.spotPicturesList = spotPicturesList;
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

    public List<SpotPictures> getSpotPicturesList() {
        return spotPicturesList;
    }
    public void setSpotPicturesList(SpotPictures spotPicturesList) {
        this.spotPicturesList.add(spotPicturesList);
    }
    public void setSpotPicturesList(List<SpotPictures> spotPicturesList) {
        this.spotPicturesList = spotPicturesList;
    }

    @Override
    public String toString() {
        return "SpotPicture{" +
                "id='" + id + '\'' +
                ", spotId=" + spotId +
                ", spotPicturesList=" + spotPicturesList +
                '}';
    }

    public SpotPicture() {
    }

    public static class SpotPictures{
        @Id
        private String id;
        private URL spotPicture;

        public SpotPictures(String id, URL spotPicture) {
            this.id = id;
            this.spotPicture = spotPicture;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public URL getSpotPicture() {
            return spotPicture;
        }

        public void setSpotPicture(URL spotPicture) {
            this.spotPicture = spotPicture;
        }

        public SpotPictures() {
        }

        @Override
        public String toString() {
            return "SpotPictures{" +
                    "id='" + id + '\'' +
                    ", spotPicture=" + spotPicture +
                    '}';
        }
    }
}
