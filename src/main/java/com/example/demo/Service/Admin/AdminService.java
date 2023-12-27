package com.example.demo.Service.Admin;

import com.example.demo.Model.*;
import com.example.demo.Service.Organizer.OrganizerService;
import com.example.demo.Service.TouristSpot.TouristSpotService;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.jws.soap.SOAPBinding;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.Part;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    String addManyUsers(List<User> users);
    String addEvent(Event newEvent);
    String addAllEvents(List<Event> events);
    String addSpot(TouristSpot spot);
    String addAllSpots(List<TouristSpot> spots);
    List<Participant> getAllParticipant();
    List<Participant> getAllBusyParticipants();
    List<Participant> getAllFreeParticipants();
    List<Participant> getAllParticipantByGroupId(Integer groupId);
    List<Group> getAllGroup();
    List<Group> getAllActiveGroups();
    List<Group> getAllInActiveGroups();
    List<User> getAllUser();
    List<Event> getAllEvent();
    List<Event> getAllInActiveEvents();
    List<Event> getAllActiveEvents();
    List<TouristSpot> getAllSpot();
    List<Organizer> getAllOrganizer();
    List<Organizer> getAllBusyOrganizers();
    List<Organizer> getAllFreeOrganizers();


    String removeUserById(Integer userId);
    String removeAllUser();
    String removeParticipantById(Integer participantId);
    String removeGroupById(Integer groupId);
    String removeEventById(Integer eventId);
    String removeTouristSpotById(Integer spotId);
    String removeOrganizerById(Integer organizerId);
    String removeAllInActiveEvents();

    User getUserById(Integer userId);
    Participant getParticipantById(Integer participantId);
    Group getGroupById(Integer groupId);
    Event getEventById(Integer eventId);
    TouristSpot getSpotById(Integer spotId);
    Organizer getOrganizerById(Integer organizerId);
}
