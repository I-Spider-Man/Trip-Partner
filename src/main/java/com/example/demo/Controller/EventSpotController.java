package com.example.demo.Controller;

import com.example.demo.Model.Event;
import com.example.demo.Model.EventStatus;
import com.example.demo.Model.Group;
import com.example.demo.Model.TouristSpot;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Repository.TouristSpotRepository;
import com.example.demo.Service.Event.EventService;
import com.example.demo.Service.GroupServices.GroupService;
import com.example.demo.Service.StorageService;
import com.example.demo.Service.TouristSpot.TouristSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class EventSpotController {
    @Autowired
    private EventService eventService;
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

    @GetMapping("/activeEvents/{id}")
    public Event getEventById(@PathVariable Integer id){
        return eventService.getEventById(id);
    }
    @GetMapping("/event/group/{eventName}")
    public List<Group> getGrpByEventName(@PathVariable String eventName){
        return groupService.getAllGroupByEventName(eventName);
    }
    @GetMapping("/eventPicture/{fileName}")
    public ResponseEntity<?> viewEventPhoto(@PathVariable String fileName){
        byte [] data=storageService.viewFile(fileName);
        ByteArrayResource byteArrayResource=new ByteArrayResource(data);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(data);
    }
    @GetMapping("/spotPicture/{fileName}")
    public ResponseEntity<?> viewSpotPhoto(@PathVariable String fileName){
        byte [] data=storageService.viewFile(fileName);
        ByteArrayResource byteArrayResource=new ByteArrayResource(data);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(data);
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
