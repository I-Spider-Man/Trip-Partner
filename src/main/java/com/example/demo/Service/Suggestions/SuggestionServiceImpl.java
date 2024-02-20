package com.example.demo.Service.Suggestions;

import com.example.demo.Model.Event;
import com.example.demo.Model.TouristSpot;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Repository.TouristSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SuggestionServiceImpl implements  SuggestionService{
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TouristSpotRepository touristSpotRepository;
    @Override
    public List<Event> nearByEventsForEvents(Integer eventId) {
        List<Event> suggestingEventList=new ArrayList<>();
        Optional<Event> event=eventRepository.findById(eventId);
        if(event.isPresent()){
            String city=event.get().getLocation().getCity();
            String state=event.get().getLocation().getState();
            String postalCode=event.get().getLocation().getPostalCode();
            List<Event> cityEvent=eventRepository.findAllByLocationCity(city);
            List<Event> stateEvent=eventRepository.findAllByLocationState(state);
            List<Event> postalCodeEvent=eventRepository.findAllByLocationPostalCode(postalCode);
            for (Event postalCodeEventItem : postalCodeEvent) {
                if (!suggestingEventList.contains(postalCodeEventItem)) {
                    suggestingEventList.add(postalCodeEventItem);
                }
            }
            for (Event cityEventItem : cityEvent) {
                if (!suggestingEventList.contains(cityEventItem)) {
                    suggestingEventList.add(cityEventItem);
                }
            }
            for (Event stateEventItem : stateEvent) {
                if (!suggestingEventList.contains(stateEventItem)) {
                    suggestingEventList.add(stateEventItem);
                }
            }
        }
        return suggestingEventList;
    }

    @Override
    public List<TouristSpot> nearByTouristSpotsForEvents(Integer eventId) {
        List<TouristSpot> suggestionSpotList=new ArrayList<>();
        Optional<Event> event=eventRepository.findById(eventId);
        if(event.isPresent()){
            String city=event.get().getLocation().getCity();
            String state=event.get().getLocation().getState();
            String postalCode=event.get().getLocation().getPostalCode();
            List<TouristSpot> cityTouristSpot=touristSpotRepository.findAllByLocationCity(city);
            List<TouristSpot> stateTouristSpot=touristSpotRepository.findAllByLocationState(state);
            List<TouristSpot> postalCodeTouristSpot=touristSpotRepository.findAllByLocationPostalCode(postalCode);
            for(TouristSpot spot:postalCodeTouristSpot){
                if(!suggestionSpotList.contains(spot)){
                    suggestionSpotList.add(spot);
                }
            }
            for(TouristSpot spot:cityTouristSpot){
                if(!suggestionSpotList.contains(spot)){
                    suggestionSpotList.add(spot);
                }
            }
            for(TouristSpot spot:stateTouristSpot){
                if(!suggestionSpotList.contains(spot)){
                    suggestionSpotList.add(spot);
                }
            }
        }
        return suggestionSpotList;
    }

    @Override
    public List<Event> nearByEventsForTouristSpot(Integer spotId) {
        List<Event> suggestingEventList=new ArrayList<>();
        Optional<TouristSpot> spot=touristSpotRepository.findById(spotId);
        if(spot.isPresent()){
            String city=spot.get().getLocation().getCity();
            String state=spot.get().getLocation().getState();
            String postalCode=spot.get().getLocation().getPostalCode();
            List<Event> cityEvent=eventRepository.findAllByLocationCity(city);
            List<Event> stateEvent=eventRepository.findAllByLocationState(state);
            List<Event> postalCodeEvent=eventRepository.findAllByLocationPostalCode(postalCode);
            for (Event postalCodeEventItem : postalCodeEvent) {
                if (!suggestingEventList.contains(postalCodeEventItem)) {
                    suggestingEventList.add(postalCodeEventItem);
                }
            }
            for (Event cityEventItem : cityEvent) {
                if (!suggestingEventList.contains(cityEventItem)) {
                    suggestingEventList.add(cityEventItem);
                }
            }
            for (Event stateEventItem : stateEvent) {
                if (!suggestingEventList.contains(stateEventItem)) {
                    suggestingEventList.add(stateEventItem);
                }
            }
        }
        return suggestingEventList;
    }

    @Override
    public List<TouristSpot> nearByTouristSpotsForTouristSpot(Integer spotId) {
        List<TouristSpot> suggestionSpotList=new ArrayList<>();
        Optional<TouristSpot> spot1=touristSpotRepository.findById(spotId);
        if(spot1.isPresent()){
            String city=spot1.get().getLocation().getCity();
            String state=spot1.get().getLocation().getState();
            String postalCode=spot1.get().getLocation().getPostalCode();
            List<TouristSpot> cityTouristSpot=touristSpotRepository.findAllByLocationCity(city);
            List<TouristSpot> stateTouristSpot=touristSpotRepository.findAllByLocationState(state);
            List<TouristSpot> postalCodeTouristSpot=touristSpotRepository.findAllByLocationPostalCode(postalCode);
            for(TouristSpot spot:postalCodeTouristSpot){
                if(!suggestionSpotList.contains(spot)){
                    suggestionSpotList.add(spot);
                }
            }
            for(TouristSpot spot:cityTouristSpot){
                if(!suggestionSpotList.contains(spot)){
                    suggestionSpotList.add(spot);
                }
            }
            for(TouristSpot spot:stateTouristSpot){
                if(!suggestionSpotList.contains(spot)){
                    suggestionSpotList.add(spot);
                }
            }
        }
        return suggestionSpotList;
    }
}
