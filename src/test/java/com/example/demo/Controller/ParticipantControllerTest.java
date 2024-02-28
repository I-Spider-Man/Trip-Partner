package com.example.demo.Controller;

import com.example.demo.Model.Group;
import com.example.demo.Model.Participant;
import com.example.demo.Model.ParticipantRating;
import com.example.demo.Service.ParticipantServices.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ParticipantControllerTest {

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantController participantController;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
        // Initialize Mockito annotations
    }

    @Test
    void testGetAllParticipantsInGroup() {
        Integer groupId = 1;
        List<Participant> expectedParticipants = Arrays.asList(new Participant(), new Participant());

        when(participantService.getAllParticipantsByGroupId(groupId)).thenReturn(expectedParticipants);

        List<Participant> actualParticipants = participantController.getAllParticipantsInGroup(groupId);

        assertEquals(expectedParticipants, actualParticipants);
        verify(participantService, times(1)).getAllParticipantsByGroupId(groupId);
    }

    @Test
    void testAllGroups() {
        Integer userId = 1;
        List<Group> expectedGroups = Arrays.asList(new Group(), new Group());

        when(participantService.getAllParticipatedGroups(userId)).thenReturn(expectedGroups);

        List<Group> actualGroups = participantController.allGroups(userId);

        assertEquals(expectedGroups, actualGroups);
        verify(participantService, times(1)).getAllParticipatedGroups(userId);
    }

    @Test
    void testAddRating() {
        Integer participantId = 1;
        ParticipantRating.Ratings ratings = new ParticipantRating.Ratings();

        participantController.addRating(participantId, ratings);

        verify(participantService, times(1)).setRating(participantId, ratings);
    }

    @Test
    void testGetRating() {
        Integer participantId = 1;
        ParticipantRating expectedRating = new ParticipantRating();

        when(participantService.getRatings(participantId)).thenReturn(expectedRating);

        ParticipantRating actualRating = participantController.getRating(participantId);

        assertEquals(expectedRating, actualRating);
        verify(participantService, times(1)).getRatings(participantId);
    }

    @Test
    void testLeaveGroupByParticipantId() {
        Integer participantId = 1;
        Integer groupId = 2;

        when(participantService.leaveGroupByParticipantId(participantId, groupId)).thenReturn(ResponseEntity.ok("Left group successfully"));

        ResponseEntity<String> response = participantController.leaveGroupByParticipantId(participantId, groupId);

        assertEquals(ResponseEntity.ok("Left group successfully"), response);
        verify(participantService, times(1)).leaveGroupByParticipantId(participantId, groupId);
    }

    @Test
    void testGetParticipantByUserId() {
        Integer userId = 1;
        Participant expectedParticipant = new Participant();

        when(participantService.getParticipantByUserId(userId)).thenReturn(expectedParticipant);

        Participant actualParticipant = participantController.getParticipantByUserId(userId);

        assertEquals(expectedParticipant, actualParticipant);
        verify(participantService, times(1)).getParticipantByUserId(userId);
    }

    @Test
    void testGetParticipantById() {
        Integer participantId = 1;
        Participant expectedParticipant = new Participant();

        when(participantService.getParticipantById(participantId)).thenReturn(expectedParticipant);

        Participant actualParticipant = participantController.getParticipantById(participantId);

        assertEquals(expectedParticipant, actualParticipant);
        verify(participantService, times(1)).getParticipantById(participantId);
    }

    @Test
    void testAddParticipant() {
        Participant newParticipant = new Participant();

        when(participantService.addParticipant(newParticipant)).thenReturn(ResponseEntity.ok("Participant added successfully"));

        ResponseEntity<String> response = participantController.addParticipant(newParticipant);

        assertEquals(ResponseEntity.ok("Participant added successfully"), response);
        verify(participantService, times(1)).addParticipant(newParticipant);
    }
}

