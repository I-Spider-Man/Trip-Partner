package com.example.demo.Service.Event;

import com.example.demo.Model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    List<Event> getAllActiveEvents();
    List<Event> getAllPopularEvents();
    ResponseEntity<String> addEvent(Event newEvent,MultipartFile eventImage) throws MalformedURLException;
    String addAllEvents(List<Event> events);
    String deleteEventById(Integer eventId);
    Event getEventById(Integer eventId);
    ResponseEntity<Event> getEventByEventName(String eventName);
}
