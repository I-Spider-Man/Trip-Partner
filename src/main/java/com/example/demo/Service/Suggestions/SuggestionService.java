package com.example.demo.Service.Suggestions;

import com.example.demo.Model.Event;
import com.example.demo.Model.TouristSpot;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;

public interface SuggestionService {
    List<Event> nearByEventsForEvents(Integer eventId);
    List<TouristSpot> nearByTouristSpotsForEvents(Integer eventId);
    List<Event> nearByEventsForTouristSpot(Integer spotId);
    List<TouristSpot> nearByTouristSpotsForTouristSpot(Integer spotId);

}
