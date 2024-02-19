package com.example.demo.Service.Organizer;

import com.example.demo.Model.Group;
import com.example.demo.Model.Organizer;
import com.example.demo.Model.OrganizerRating;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OrganizerService {
    List<Group> getAllGroupsOrganizedById(Integer userId);
    List<Organizer> getAllOrganizer();
    OrganizerRating getOrganizerRatings(Integer organizerId);
    void addRatings(Integer userId, OrganizerRating.Ratings ratings);
    Organizer getOrganizerById(Integer organizerId);
    Organizer getOrganizerByUserId(Integer userId);
    ResponseEntity<?> addOrganizer(Organizer newOrganizer, Group newGroup);
    //String addParticipantToGroup();
    String removeOrganizerById(Integer organizerId);
}
