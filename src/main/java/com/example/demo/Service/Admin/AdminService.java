package com.example.demo.Service.Admin;

import com.example.demo.Model.*;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface AdminService {
    String addManyUsers(List<User> users);
    String replyForUserFeedback(AdminFeedback feedback) throws MessagingException;
    List<AdminFeedback> getAdminFeedBackByUserId(Integer userId);
    List<AdminFeedback> getAllAdminFeedBack();
    ResponseEntity<String> addEvent(Event newEvent, MultipartFile file) throws IOException;
    String addAllEvents(List<Event> events);
    ResponseEntity<?> addSpot(TouristSpot spot, MultipartFile spotPicture) throws IOException;
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
    ResponseEntity<String> removeTouristSpotById(Integer spotId);
    String removeOrganizerById(Integer organizerId);
    String removeAllInActiveEvents();

    User getUserById(Integer userId);
    Participant getParticipantById(Integer participantId);
    Group getGroupById(Integer groupId);
    Event getEventById(Integer eventId);
    TouristSpot getSpotById(Integer spotId);
    String deleteFeedBack(String id);
    Organizer getOrganizerById(Integer organizerId);

    AuthResponse addUser(User user) throws Exception;
    AuthResponse sigin(LoginRequest LoginRequest);
    User findUserByJwt(String jwt);
}
