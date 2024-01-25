package com.example.demo.Service.TouristSpot;

import com.example.demo.Model.TouristSpot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TouristSpotService {
    List<TouristSpot> getAllSpots();
    TouristSpot getSpotById(Integer spotId);
    String addSpot(TouristSpot newSpot);
    String addAllSpots(List<TouristSpot> spots);
    String removeSpotById(Integer spotId);
    List<TouristSpot> getAllPopularTouristSpot();
    public ResponseEntity<?> uploadSpotPicture(Integer SpotId, MultipartFile file);

}
