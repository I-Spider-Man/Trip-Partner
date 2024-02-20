package com.example.demo.Controller;


import com.example.demo.Model.*;
import com.example.demo.Service.Event.EventService;
import com.example.demo.Service.Event.EventServiceImpl;
import com.example.demo.Service.GroupServices.GroupService;
import com.example.demo.Service.StorageService;
import com.example.demo.Service.Suggestions.SuggestionService;
import com.example.demo.Service.TouristSpot.TouristSpotService;
import com.example.demo.Service.TouristSpot.TouristSpotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
public class EventSpotController {
    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private SuggestionService suggestionService;
    @Autowired
    private GroupService groupService;

    @Autowired
    private TouristSpotServiceImpl spotService;



    @GetMapping("/activeEvents")
    public List<Event> getAllActiveEvents(){
        return eventService.getAllActiveEvents();
    }
    @GetMapping("/activeEvent/{eventName}")
    public ResponseEntity<Event> getEventByName(@PathVariable String eventName){
        return eventService.getEventByEventName(eventName);
    }
    @PostMapping("/event/{eventId}/feedback")
    public ResponseEntity<String> submitEventFeedBack(@PathVariable Integer eventId, @RequestBody EventFeedback.Feedback feedback){
        return eventService.feedBackSubmission(eventId,feedback);
    }
    @GetMapping("/event/{eventId}/feedback")
    public ResponseEntity<List<EventFeedback.Feedback>> eventFeedBack(@PathVariable Integer eventId){
        return eventService.getAllFeedBackByEventId(eventId);
    }
    @GetMapping("/activeEvents/{id}")
    public Event getEventById(@PathVariable Integer id){
        return eventService.getEventById(id);
    }
    @GetMapping("/event/group/{eventName}")
    public List<Group> getGrpByEventName(@PathVariable String eventName){
        return groupService.getAllGroupByEventName(eventName);
    }
    @GetMapping("/spot/{spotId}/feedback")
    public ResponseEntity<List<TouristSpotFeedback.Feedback>> getAllFeedBackOfSpot(@PathVariable Integer spotId){
        return spotService.getAllFeedbackBySpotId(spotId);
    }
    @GetMapping("/event/pictureList/{eventId}")
    public List<String> getAllEventPictureById(@PathVariable Integer eventId){
        return eventService.getAllPicturesByEventId(eventId);
    }
//    @GetMapping("/Picture/{fileName}")
//    public ResponseEntity<?> viewEventPhoto(@PathVariable String fileName){
//        byte [] data=storageService.viewFile(fileName);
//        ByteArrayResource byteArrayResource=new ByteArrayResource(data);
//        return ResponseEntity.status(HttpStatus.OK).contentType(findMedia(fileName)).body(data);
//    }
//    private MediaType findMedia(String fileName){
//        if(fileName.toLowerCase().endsWith(".png")){
//            return MediaType.IMAGE_PNG;
//        } else if (fileName.toLowerCase().endsWith(".jpeg")) {
//            return MediaType.IMAGE_JPEG;
//        }
//        else{
//            return MediaType.IMAGE_PNG;
//        }
//    }
    @PostMapping("/updateEventPicture")
    public void updateEventPicture(@RequestParam(value = "eventId") Integer eventId, @RequestParam(value = "picture") MultipartFile eventPicture) throws IOException {
        eventService.addEventPicture(eventId,eventPicture);
    }
    @PostMapping("/spot/{spotId}/feedback")
    public ResponseEntity<String> getAllSpotFeedBack(@PathVariable Integer spotId, @RequestBody TouristSpotFeedback.Feedback feedback){
        return spotService.submitFeedback(spotId,feedback);
    }
    @PostMapping("/updateSpotPicture")
    public void updateSpotPicture(@RequestParam(value = "spotId") Integer spotId, @RequestParam(value = "picture") MultipartFile spotPicture) throws IOException {
        spotService.addSpotPictures(spotId,spotPicture);
    }
    @GetMapping("/spots")
    public List<TouristSpot> getAllSpots(){
        return spotService.getAllSpots();
    }
    @GetMapping("/spots/{id}")
    public TouristSpot getSpotById(@PathVariable Integer id){
        return spotService.getSpotById(id);
    }
    @GetMapping("/spot/{spotName}")
    public ResponseEntity<TouristSpot> getSpotBySpotName(@PathVariable String spotName){
        return spotService.getSpotBySpotName(spotName);
    }
    @GetMapping("/spot/pictureList/{spotId}")
    public List<String> getAllPicturesBySpotId(@PathVariable Integer spotId){
        return spotService.getSpotPictureById(spotId);
    }
    @GetMapping("/spot/group/{spotName}")
    public List<Group> getGrpBySpotName(@PathVariable String spotName){
        return groupService.getAllGroupBySpotName(spotName);
    }
    @GetMapping("/PopularSpots")
     public List<TouristSpot> getAllPopularSpots(){
        return spotService.getAllPopularTouristSpot();
    }
    @GetMapping("/PopularEvents")
    public List<Event> getAllPopularEvents(){
        return eventService.getAllPopularEvents();
    }
    @GetMapping("/nearByEventsForEvents/{eventId}")
    public List<Event> getAllNearByEventsForEvents(@PathVariable Integer eventId){
        return suggestionService.nearByEventsForEvents(eventId);
    }
    @GetMapping("/nearBySpotsForEvents/{eventId}")
    public List<TouristSpot> getAllNearBySpotsForEvents(@PathVariable Integer eventId){
        return suggestionService.nearByTouristSpotsForEvents(eventId);
    }
    @GetMapping("/nearByEventsForSpots/{spotId}")
    public List<Event> getAllNearByEventsForSpot(@PathVariable Integer spotId){
        return  suggestionService.nearByEventsForTouristSpot(spotId);
    }
    @GetMapping("/nearBySpotsForSpots/{spotId}")
    public List<TouristSpot> getAllNearBySpotsForSpots(@PathVariable Integer spotId){
        return suggestionService.nearByTouristSpotsForTouristSpot(spotId);
    }

}
