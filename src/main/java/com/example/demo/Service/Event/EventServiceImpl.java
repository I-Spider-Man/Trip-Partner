package com.example.demo.Service.Event;

import com.example.demo.Model.Event;
import com.example.demo.Model.EventStatus;
import com.example.demo.Model.GroupStatus;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Service.Scheduling;
import com.example.demo.Service.SchedulingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private Scheduling scheduling;
    @Override
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @Override
    public String addEvent(Event newEvent) {
        newEvent.setEventStatus(EventStatus.Active);
        Optional<Event> event=eventRepository.findByEventName(newEvent.getEventName());
        if(event.isPresent()) {
            if(event.get().getEventStatus() == EventStatus.InActive){
                eventRepository.save(newEvent);
                scheduling.addActiveEventId(event.get().getEventId());
                return "Event "+newEvent.getEventName()+" Added Successfully";
            }
            else{
                return "there is already one event is happening with the same name";
            }
        }
        else{
            eventRepository.save(newEvent);
            return "Event "+newEvent.getEventId()+" Added Successfully ";
        }

    }

    @Override
    public String deleteEventById(Integer eventId) {
        Optional<Event> event=eventRepository.findById(eventId);
        if(event.isPresent()){
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
}
