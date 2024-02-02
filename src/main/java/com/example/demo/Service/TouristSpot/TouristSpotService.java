package com.example.demo.Service.TouristSpot;

import com.example.demo.Model.TouristSpot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TouristSpotService {
    List<TouristSpot> getAllSpots();
    TouristSpot getSpotById(Integer spotId);
    ResponseEntity<?> addSpot(TouristSpot newSpot, MultipartFile spotPicture);
    ResponseEntity<TouristSpot> getSpotBySpotName(String spotName);
    String addAllSpots(List<TouristSpot> spots);
    ResponseEntity<String> removeSpotById(Integer spotId);
    List<TouristSpot> getAllPopularTouristSpot();

}
