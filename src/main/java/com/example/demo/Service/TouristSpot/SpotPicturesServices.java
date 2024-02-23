package com.example.demo.Service.TouristSpot;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface SpotPicturesServices {
    void addSpotPictures(Integer spotId, MultipartFile file) throws IOException;
    List<String> getSpotPictureById(Integer spotId);
}
