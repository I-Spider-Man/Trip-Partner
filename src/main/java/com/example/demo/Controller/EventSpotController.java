package com.example.demo.Controller;

import com.example.demo.Model.Event;
import com.example.demo.Model.EventStatus;
import com.example.demo.Model.TouristSpot;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Repository.TouristSpotRepository;
import com.example.demo.Service.Event.EventService;
import com.example.demo.Service.TouristSpot.TouristSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EventSpotController {
    @Autowired
    private EventService eventService;

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

    @GetMapping("/Spots")
    public List<TouristSpot> getAllSpots(){
        return spotService.getAllSpots();
    }
    @GetMapping("/Spots/{id}")
    public TouristSpot getSpotById(@PathVariable Integer id){
        return spotService.getSpotById(id);
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
