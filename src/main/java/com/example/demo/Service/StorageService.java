package com.example.demo.Service;

import com.example.demo.Model.EventPicture;
import com.example.demo.Model.SpotPicture;
import com.example.demo.Model.UserImages;
import com.example.demo.Repository.EventImageRepository;
import com.example.demo.Repository.SpotImageRepository;
import com.example.demo.Repository.UserImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class StorageService {
    @Autowired
    private EventImageRepository eventImageRepository;
    @Autowired
    private SpotImageRepository spotImageRepository;
    @Autowired
    private UserImageRepository userImageRepository;


    public String addEventImage(Integer eventId, MultipartFile file) throws IOException {
        byte[] eventImage=file.getBytes();
        Optional<EventPicture> eventPicture=eventImageRepository.findByEventId(eventId);
        if(eventPicture.isPresent()){
            EventPicture.EventPictures eventPictures=new EventPicture.EventPictures(eventImage);
            eventPicture.get().setEventPictures(eventPictures);
            eventImageRepository.save(eventPicture.get());
            return eventPicture.get().getId();
        }
        else{
            EventPicture eventPicture1=new EventPicture();
            EventPicture.EventPictures eventPictures=new EventPicture.EventPictures(eventImage);
            List<EventPicture.EventPictures> eventPicturesList=new ArrayList<>();
            eventPicturesList.add(eventPictures);
            eventPicture1.setEventId(eventId);
            eventPicture1.setEventPictures(eventPicturesList);
            eventImageRepository.save(eventPicture1);
            return eventPicture1.getId();
        }
    }
    public String addSpotImage(Integer spotId,MultipartFile file) throws IOException{
        byte[] spotImage=file.getBytes();
        Optional<SpotPicture> spotPicture=spotImageRepository.findBySpotId(spotId);
        if(spotPicture.isPresent()){
            SpotPicture.SpotPictures spotPictures=new SpotPicture.SpotPictures(spotImage);
            spotPicture.get().setSpotId(spotId);
            spotPicture.get().setSpotPicturesList(spotPictures);
            spotImageRepository.save(spotPicture.get());
            return spotPicture.get().getId();
        }
        else{
            SpotPicture spotPicture1=new SpotPicture();
            SpotPicture.SpotPictures spotPictures=new SpotPicture.SpotPictures(spotImage);
            List<SpotPicture.SpotPictures> spotPicturesList=new ArrayList<>();
            spotPicturesList.add(spotPictures);
            spotPicture1.setSpotId(spotId);
            spotPicture1.setSpotPicturesList(spotPicturesList);
            spotImageRepository.save(spotPicture1);
            return spotPicture1.getId();
        }
    }

    public String addUserProfile(Integer userId,MultipartFile file) throws IOException {
        byte[] userProfile= file.getBytes();
        byte[] empty=new byte[0];
        Optional<UserImages> userImages=userImageRepository.findByUserId(userId);
        if(userImages.isPresent()){
            userImages.get().getUserImages().setProfileImage(userProfile);
            userImageRepository.save(userImages.get());
            return userImages.get().getId();
        }else{
            UserImages newUserImages=new UserImages();
            UserImages.Images.Posts posts=new UserImages.Images.Posts(empty);
            List<UserImages.Images.Posts> userPost=new ArrayList<>();
            userPost.add(posts);
            UserImages.Images images=new UserImages.Images();
            images.setPostsList(userPost);
            newUserImages.setUserImages(images);
            newUserImages.getUserImages().setProfileImage(userProfile);
            userImageRepository.save(newUserImages);
            return newUserImages.getId();
        }
    }

    public List<String> getEventImage(Integer eventId){
        Optional<EventPicture> eventPicture=eventImageRepository.findByEventId(eventId);
        return eventPicture.map(picture -> picture.getEventPictures()
                .stream()
                .map(eventPictures -> Base64.getEncoder().encodeToString(eventPictures.getEventPicture()))
                .toList()).orElse(null);
    }
    public List<String> getSpotImage(Integer spotId){
        Optional<SpotPicture> spotPicture=spotImageRepository.findBySpotId(spotId);
        return spotPicture.map(picture -> picture.getSpotPicturesList()
                .stream()
                .map(spotPictures -> Base64.getEncoder().encodeToString(spotPictures.getSpotPicture()))
                .toList()).orElse(null);
    }
    public void deleteSpotImage(Integer spotId){
        Optional<SpotPicture> spotPicture=spotImageRepository.findBySpotId(spotId);
        spotPicture.ifPresent(picture -> spotImageRepository.delete(picture));
    }
    public void deleteEventImage(Integer eventId){
        Optional<EventPicture> eventPicture=eventImageRepository.findByEventId(eventId);
        eventPicture.ifPresent(picture->eventImageRepository.delete(picture));
    }
}
