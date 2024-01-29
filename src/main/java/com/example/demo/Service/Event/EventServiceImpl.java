package com.example.demo.Service.Event;


import com.example.demo.Model.Event;
import com.example.demo.Model.EventStatus;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Service.Scheduling;
import com.example.demo.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private Scheduling scheduling;
    @Autowired
    private StorageService storageService;
    @Override
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @Override
    public List<Event> getAllActiveEvents() {
        return eventRepository.findAllByEventStatus(EventStatus.Active);
    }

    @Override
    public List<Event> getAllPopularEvents() {
        List<Event> popularEvents=eventRepository.findAllByEventStatus(EventStatus.Active)
                .stream()
                .sorted(Comparator.comparingInt(Event::getPeopleCount).reversed())
                .toList();
        return popularEvents.stream().limit(5).collect(Collectors.toList());
    }



    @Override
    public ResponseEntity<String> addEvent(Event newEvent,MultipartFile eventImage) {
        newEvent.setEventStatus(EventStatus.Active);
        Optional<Event> event=eventRepository.findAllByEventStatus(EventStatus.Active).stream().filter(event1 -> event1.getEventName().equals(newEvent.getEventName())).findAny();
        if(event.isPresent()) {
            if(event.get().getEventStatus() == EventStatus.InActive){
                eventRepository.save(newEvent);
                String fileName=System.currentTimeMillis()+"_"+newEvent.getEventId()+"_"+event.get().getEventName()+"_event";
                if(storageService.uploadFile(fileName,eventImage)){
                    event.get().setEventPicture(fileName);
                    eventRepository.save(event.get());
                }
                else {
                    return new ResponseEntity<>("Conflict on uploading picture",HttpStatus.CONFLICT);
                }
                scheduling.addActiveEventId(newEvent.getEventId());
                return new ResponseEntity<>("Event added successfully", HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("there is already one event is happening with the same name",HttpStatus.CONFLICT) ;
            }
        }
        else{
            eventRepository.save(newEvent);
            String fileName=System.currentTimeMillis()+"_"+newEvent.getEventId()+"_"+newEvent.getEventName()+"_event";
            if(storageService.uploadFile(fileName,eventImage)){
                newEvent.setEventPicture(fileName);
                eventRepository.save(newEvent);
            }
            else {
                eventRepository.delete(newEvent);
                return new ResponseEntity<>("Conflict on uploading picture",HttpStatus.CONFLICT);
            }
            scheduling.addActiveEventId(newEvent.getEventId());
            return new ResponseEntity<>("Event "+newEvent.getEventId()+" Added Successfully ",HttpStatus.CREATED);
        }

    }

    @Override
    public String addAllEvents(List<Event> events) {
        eventRepository.saveAll(events);
        return "Success";
    }

    @Override
    public String deleteEventById(Integer eventId) {
        Optional<Event> event=eventRepository.findById(eventId);
        if(event.isPresent()){
            storageService.deleteFile(event.get().getEventPicture());
            eventRepository.deleteById(eventId);
            return "Event "+event.get().getEventName()+" Removed Successfully";
        }else{
                return "Event with id "+eventId+" not present in database";
            }
    }

    @Override
    public Event getEventById(Integer eventId) {
        Optional<Event> event=eventRepository.findById(eventId);
        return event.orElse(null);
    }

    @Override
    public ResponseEntity<Event> getEventByEventName(String eventName) {
        Optional<Event> event=eventRepository.findByEventName(eventName);
        return new ResponseEntity<>(event.orElse(null),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> uploadEventPicture(Integer EventId, MultipartFile file) {
        Optional<Event> event=eventRepository.findById(EventId);
        if(event.isPresent()){
            String fileName=System.currentTimeMillis()+"_"+EventId+"_"+event.get().getEventName();
            storageService.deleteFile(event.get().getEventPicture());
            if(storageService.uploadFile(fileName,file)){
                event.get().setEventPicture(fileName);
                eventRepository.save(event.get());
                return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Conflict on uploading picture",HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Event not found in database",HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> viewEventPicture(Integer EventId) {
        Optional<Event> event=eventRepository.findById(EventId);
        if(event.isPresent()){
            storageService.viewFile(event.get().getEventPicture());
        }
        return null;
    }
}
