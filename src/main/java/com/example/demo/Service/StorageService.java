package com.example.demo.Service;

import com.example.demo.Model.EventPicture;
import com.example.demo.Model.SpotPicture;
import com.example.demo.Model.UserImageResponse;
import com.example.demo.Model.UserImages;
import com.example.demo.Repository.EventImageRepository;
import com.example.demo.Repository.SpotImageRepository;
import com.example.demo.Repository.UserImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
@Service
public class StorageService {
    @Autowired
    private EventImageRepository eventImageRepository;
    @Autowired
    private SpotImageRepository spotImageRepository;
    @Autowired
    private UserImageRepository userImageRepository;
    public String deletePost(int userId, byte[] post) {

        Optional<UserImages> userImages = userImageRepository.findByUserId(userId);

        if (userImages.isPresent() && userImages.get().getPostsList() != null) {
            userImages.get().getPostsList().removeIf(p -> Arrays.equals(p.getPost(), post));
            userImageRepository.save(userImages.get());
            return "post deleted";
        } else {

            return ("UserImages not found or postsList is null");
        }
    }
    public ResponseEntity<String> addUserPosts(Integer userId, String description, MultipartFile post ) throws IOException{

        Optional<UserImages> userImages=userImageRepository.findByUserId(userId);
        if(userImages.isPresent()){
            UserImages.Posts posts=new UserImages.Posts();
            posts.setDescription(description);
            posts.setPost(post.getBytes());
            userImages.get().getPostsList().add(posts);
            userImageRepository.save(userImages.get());
            return new ResponseEntity<>("post uploaded successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Problem on uploading. try again...",HttpStatus.CONFLICT);
        }
    }
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
            eventPicture1.setEventId(eventId);
            eventPicture1.setEventPictures(eventPictures);
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

    public void addUserProfile(Integer userId,MultipartFile file) throws IOException {
        byte[] userProfile= file.getBytes();
        Optional<UserImages> userImages=userImageRepository.findByUserId(userId);
        if(userImages.isPresent()){
            userImages.get().setProfile(userProfile);
            userImageRepository.save(userImages.get());
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
    public String getUserProfile(Integer userId){
        Optional<UserImages> userImages=userImageRepository.findByUserId(userId);
        return userImages.map(images -> Base64.getEncoder().encodeToString(images.getProfile())).orElse(null);
    }
    public List<UserImageResponse> getUserPosts(Integer userId){
        Optional<UserImages> userImages=userImageRepository.findByUserId(userId);
        List<UserImageResponse> userImageResponses=new ArrayList<>();
        userImages.ifPresent(images -> images.getPostsList().forEach(posts ->
        {
            UserImageResponse userImageResponse = new UserImageResponse();
            userImageResponse.setPost(Base64.getEncoder().encodeToString(posts.getPost()));
            userImageResponse.setDescription(posts.getDescription());
            userImageResponses.add(userImageResponse);
        }));
        return userImageResponses;
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
