package com.example.demo.Service.Organizer;

import com.example.demo.Model.Group;
import com.example.demo.Model.Organizer;
import com.example.demo.Model.OrganizerRating;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrganizerServiceTest {

    private final OrganizerService organizerService = mock(OrganizerService.class);

    @Test
    void testGetAllGroupsOrganizedById() {
        // Your implementation here
    }

    @Test
    void testGetAllOrganizer() {
        // Your implementation here
    }

    @Test
    void testGetOrganizerRatings() {
        // Your implementation here
    }

    @Test
    void testAddRatings() {
        // Your implementation here
    }

    @Test
    void testGetOrganizerById() {
        // Your implementation here
    }

    @Test
    void testGetOrganizerByUserId() {
        // Your implementation here
    }

//    @Test
//    void testAddOrganizer() {
//        Organizer newOrganizer = new Organizer(); // Create a sample organizer
//        Group newGroup = new Group(); // Create a sample group
//        ResponseEntity<?> expectedResponse = ResponseEntity.ok().build();
//
//        when(organizerService.addOrganizer(newOrganizer, newGroup)).thenReturn(expectedResponse);
//
//        ResponseEntity<?> actualResponse = organizerService.addOrganizer(newOrganizer, newGroup);
//
//        assertEquals(expectedResponse, actualResponse);
//    }

    @Test
    void testRemoveOrganizerById() {
        Integer organizerId = 1; // Replace with a valid organizerId
        String expectedResponse = "Organizer removed successfully";

        when(organizerService.removeOrganizerById(organizerId)).thenReturn(expectedResponse);

        String actualResponse = organizerService.removeOrganizerById(organizerId);

        assertEquals(expectedResponse, actualResponse);
    }
}
