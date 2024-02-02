package com.example.demo.Controller;


import com.example.demo.Model.Event;
import com.example.demo.Model.EventPicture;
import com.example.demo.Model.Group;
import com.example.demo.Model.TouristSpot;
import com.example.demo.Service.Event.EventService;
import com.example.demo.Service.Event.EventServiceImpl;
import com.example.demo.Service.GroupServices.GroupService;
import com.example.demo.Service.StorageService;
import com.example.demo.Service.TouristSpot.TouristSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
public class EventSpotController {
    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private GroupService groupService;

    @Autowired
    private TouristSpotService spotService;



    @GetMapping("/activeEvents")
    public List<Event> getAllActiveEvents(){
        return eventService.getAllActiveEvents();
    }
    @GetMapping("/activeEvent/{eventName}")
    public ResponseEntity<Event> getEventByName(@PathVariable String eventName){
        return eventService.getEventByEventName(eventName);
    }
    @GetMapping("/activeEvents/{id}")
    public Event getEventById(@PathVariable Integer id){
        return eventService.getEventById(id);
    }
    @GetMapping("/event/group/{eventName}")
    public List<Group> getGrpByEventName(@PathVariable String eventName){
        return groupService.getAllGroupByEventName(eventName);
    }
    @GetMapping("/event/pictureList/{eventId}")
    public List<EventPicture.EventPictures> getAllEventPictureById(@PathVariable Integer eventId){
        return eventService.getAllPicturesByEventId(eventId);
    }
    @GetMapping("/Picture/{fileName}")
    public ResponseEntity<?> viewEventPhoto(@PathVariable String fileName){
        byte [] data=storageService.viewFile(fileName);
        ByteArrayResource byteArrayResource=new ByteArrayResource(data);
        return ResponseEntity.status(HttpStatus.OK).contentType(findMedia(fileName)).body(data);
    }
    private MediaType findMedia(String fileName){
        if(fileName.toLowerCase().endsWith(".png")){
            return MediaType.IMAGE_PNG;
        } else if (fileName.toLowerCase().endsWith(".jpeg")) {
            return MediaType.IMAGE_JPEG;
        }
        else{
            return MediaType.IMAGE_PNG;
        }
    }
    @PostMapping("/updateEventPicture")
    public ResponseEntity<?> updateEventPicture(@RequestParam(value = "eventId") Integer eventId, @RequestParam(value = "picture") MultipartFile eventPicture){
        return eventService.uploadEventPicture(eventId,eventPicture);
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

}
