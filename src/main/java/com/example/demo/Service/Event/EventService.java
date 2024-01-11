package com.example.demo.Service.Event;

import com.example.demo.Model.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    List<Event> getAllActiveEvents();
    List<Event> getAllPopularEvents();
    String addEvent(Event newEvent);
    String addAllEvents(List<Event> events);
    String deleteEventById(Integer eventId);
    Event getEventById(Integer eventId);
}
