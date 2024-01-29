package com.example.demo.Service.Event;

import com.example.demo.Model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    List<Event> getAllActiveEvents();
    List<Event> getAllPopularEvents();
    ResponseEntity<String> addEvent(Event newEvent,MultipartFile eventImage);
    String addAllEvents(List<Event> events);
    String deleteEventById(Integer eventId);
    Event getEventById(Integer eventId);
    ResponseEntity<Event> getEventByEventName(String eventName);
    ResponseEntity<?> uploadEventPicture(Integer EventId, MultipartFile file);
    ResponseEntity<?> viewEventPicture(Integer EventId);
}
