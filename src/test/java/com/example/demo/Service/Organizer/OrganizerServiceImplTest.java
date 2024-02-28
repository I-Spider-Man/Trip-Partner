package com.example.demo.Service.Organizer;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.Scheduling;
import com.example.demo.Service.GroupServices.GroupService;
import com.example.demo.Service.OtpMailService.SMTP_mailService;
import com.example.demo.Service.UserServices.UserService;

import jakarta.mail.MessagingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrganizerServiceImplTest {

    @Mock
    private OrganizerRepository organizerRepository;

    @Mock
    private UserService userService;

    @Mock
    private OrganizerRatingRepository organizerRatingRepository;

    @Mock
    private Scheduling scheduling;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserExtraDetailsRepostiory userExtraDetailsRepostiory;

    @Mock
    private TouristSpotRepository spotRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private GroupService groupService;

    @Mock
    private SMTP_mailService mailService;

    @InjectMocks
    private OrganizerServiceImpl organizerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

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

    @Test
    void testAddOrganizer() throws MessagingException {
        // Your implementation here
    }

    @Test
    void testRemoveOrganizerById() {
        // Your implementation here
    }
}
