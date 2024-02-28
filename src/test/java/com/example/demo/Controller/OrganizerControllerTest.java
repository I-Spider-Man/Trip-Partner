package com.example.demo.Controller;

import com.example.demo.Model.Group;
import com.example.demo.Model.Organizer;
import com.example.demo.Model.OrganizerRating;
import com.example.demo.Model.RequestWrapper;
import com.example.demo.Service.Organizer.OrganizerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrganizerControllerTest {

    @Mock
    private OrganizerService organizerService;

    @InjectMocks
    private OrganizerController organizerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Additional setup if needed
    }

//    @Test
//    void testAddOrganizer() {
//        Organizer organizer = new Organizer();
//        Group group = new Group();
//        RequestWrapper wrapper = new RequestWrapper();
//
//        when(organizerService.addOrganizer(organizer, group)).thenReturn((ResponseEntity<?>) ResponseEntity.ok("Organizer added successfully"));
//
//        ResponseEntity<?> response = organizerController.addOrganizer(wrapper);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Organizer added successfully", response.getBody());
//        verify(organizerService, times(1)).addOrganizer(organizer, group);
//    }

    @Test
    void testGiveRatings() {
        Integer organizerId = 1;
        OrganizerRating.Ratings ratings = new OrganizerRating.Ratings();

        organizerController.giveRatings(organizerId, ratings);

        verify(organizerService, times(1)).addRatings(organizerId, ratings);
    }

    @Test
    void testOrganizerRating() {
        Integer organizerId = 1;
        OrganizerRating expectedRating = new OrganizerRating();

        when(organizerService.getOrganizerRatings(organizerId)).thenReturn(expectedRating);

        OrganizerRating actualRating = organizerController.organizerRating(organizerId);

        assertEquals(expectedRating, actualRating);
        verify(organizerService, times(1)).getOrganizerRatings(organizerId);
    }

    @Test
    void testAllGroups() {
        Integer userId = 1;
        List<Group> expectedGroups = Arrays.asList(new Group(), new Group());

        when(organizerService.getAllGroupsOrganizedById(userId)).thenReturn(expectedGroups);

        List<Group> actualGroups = organizerController.allGroups(userId);

        assertEquals(expectedGroups, actualGroups);
        verify(organizerService, times(1)).getAllGroupsOrganizedById(userId);
    }

    @Test
    void testGetOrganizerById() {
        Integer orgId = 1;
        Organizer expectedOrganizer = new Organizer();

        when(organizerService.getOrganizerById(orgId)).thenReturn(expectedOrganizer);

        Organizer actualOrganizer = organizerController.getOrganizerById(orgId);

        assertEquals(expectedOrganizer, actualOrganizer);
        verify(organizerService, times(1)).getOrganizerById(orgId);
    }

    @Test
    void testGetOrganizerByUserId() {
        Integer userId = 1;
        Organizer expectedOrganizer = new Organizer();

        when(organizerService.getOrganizerByUserId(userId)).thenReturn(expectedOrganizer);

        Organizer actualOrganizer = organizerController.getOrganizerByUserId(userId);

        assertEquals(expectedOrganizer, actualOrganizer);
        verify(organizerService, times(1)).getOrganizerByUserId(userId);
    }
}
