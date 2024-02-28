package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.Admin.AdminService;
import com.example.demo.Service.Admin.AuthResponse;
import com.example.demo.Service.Admin.LoginRequest;
import com.example.demo.Service.Event.EventService;
import com.example.demo.Service.GroupMessage.MessageService;
import com.example.demo.Service.StorageService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @Mock
    private MessageService messageService;

    @Mock
    private StorageService storageService;

    @Mock
    private EventService eventService;

    @InjectMocks
    private AdminController adminController;

    public AdminControllerTest() {
        MockitoAnnotations.openMocks(this);
    }
    

    @Test
    void testSignIn() {
        LoginRequest loginRequest = new LoginRequest();
        AuthResponse expectedResponse = new AuthResponse("token123", "admin");

        when(adminService.sigin(any(LoginRequest.class))).thenReturn(expectedResponse);

        AuthResponse actualResponse = adminController.signin(loginRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(adminService, times(1)).sigin(any(LoginRequest.class));
    }

    @Test
    void testAddUser() {
        User newUser = new User();
        AuthResponse expectedResponse = new AuthResponse("token123", "admin");

        try {
			when(adminService.addUser(any(User.class))).thenReturn(expectedResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        AuthResponse actualResponse = null;
		try {
			actualResponse = adminController.addUser(newUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        assertEquals(expectedResponse, actualResponse);
        try {
			verify(adminService, times(1)).addUser(any(User.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    void testManyUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        String expectedResponse = "Success";

        when(adminService.addManyUsers(anyList())).thenReturn(expectedResponse);

        String actualResponse = adminController.manyUsers(users);

        assertEquals(expectedResponse, actualResponse);
        verify(adminService, times(1)).addManyUsers(anyList());
    }

    @Test
    void testRemoveAllUsers() {
        String expectedResponse = "Success";

        when(adminService.removeAllUser()).thenReturn(expectedResponse);

        String actualResponse = adminController.removeAllUsers();

        assertEquals(expectedResponse, actualResponse);
        verify(adminService, times(1)).removeAllUser();
    }

    @Test
    void testAddEvent() {
        MultipartFile file = mock(MultipartFile.class);
        Event newEvent = new Event();
        String newEventJson = "{}";
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Success", HttpStatus.OK);

        try {
			when(adminService.addEvent(any(Event.class), eq(file))).thenReturn(expectedResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        ResponseEntity<String> actualResponse = adminController.addEvent(newEventJson, file);

        assertEquals(expectedResponse, actualResponse);
        try {
			verify(adminService, times(1)).addEvent(any(Event.class), eq(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    void testAddManyEvents() {
        List<Event> events = Arrays.asList(new Event(), new Event());
        String expectedResponse = "Success";

        when(adminService.addAllEvents(anyList())).thenReturn(expectedResponse);

        String actualResponse = adminController.addManyEvents(events);

        assertEquals(expectedResponse, actualResponse);
        verify(adminService, times(1)).addAllEvents(anyList());
    }


    @Test
    void testGetAllParticipants() {
        List<Participant> expectedParticipants = Arrays.asList(new Participant(), new Participant());

        when(adminService.getAllParticipant()).thenReturn(expectedParticipants);

        List<Participant> actualParticipants = adminController.getAllParticipants();

        assertEquals(expectedParticipants, actualParticipants);
        verify(adminService, times(1)).getAllParticipant();
    }

    @Test
    void testGetAllParticipantsByGroupId() {
        Integer groupId = 1;
        List<Participant> expectedParticipants = Arrays.asList(new Participant(), new Participant());

        when(adminService.getAllParticipantByGroupId(eq(groupId))).thenReturn(expectedParticipants);

        List<Participant> actualParticipants = adminController.getAllParticipantsByGroupId(groupId);

        assertEquals(expectedParticipants, actualParticipants);
        verify(adminService, times(1)).getAllParticipantByGroupId(eq(groupId));
    }

    @Test
    void testGetAllOrganizers() {
        List<Organizer> expectedOrganizers = Arrays.asList(new Organizer(), new Organizer());

        when(adminService.getAllOrganizer()).thenReturn(expectedOrganizers);

        List<Organizer> actualOrganizers = adminController.getAllOrganizers();

        assertEquals(expectedOrganizers, actualOrganizers);
        verify(adminService, times(1)).getAllOrganizer();
    }

  
    @Test
    void testGetAllAdminFeedBack() {
        List<AdminFeedback> feedbackList = Arrays.asList(new AdminFeedback(), new AdminFeedback());
        when(adminService.getAllAdminFeedBack()).thenReturn(feedbackList);

        List<AdminFeedback> actualFeedbackList = adminController.getAllAdminFeedBack();

        assertEquals(feedbackList, actualFeedbackList);
        verify(adminService, times(1)).getAllAdminFeedBack();
    }

    @Test
    void testGetAllAdminFeedBackByUserId() {
        Integer userId = 1;
        List<AdminFeedback> feedbackList = Arrays.asList(new AdminFeedback(), new AdminFeedback());
        when(adminService.getAdminFeedBackByUserId(userId)).thenReturn(feedbackList);

        List<AdminFeedback> actualFeedbackList = adminController.getAllAdminFeedBackByUserId(userId);

        assertEquals(feedbackList, actualFeedbackList);
        verify(adminService, times(1)).getAdminFeedBackByUserId(userId);
    }


    @Test
    void testPostReplyAdminFeedback() throws Exception {
        AdminFeedback feedback = new AdminFeedback();
        String expectedResponse = "Success";

        when(adminService.replyForUserFeedback(any(AdminFeedback.class))).thenReturn(expectedResponse);

        String actualResponse = adminController.postReply(feedback);

        assertEquals(expectedResponse, actualResponse);
        verify(adminService, times(1)).replyForUserFeedback(any(AdminFeedback.class));
    }
    @Test
    void testGetAllBusyOrganizer() {
        List<Organizer> busyOrganizers = Arrays.asList(new Organizer(), new Organizer());
        when(adminService.getAllBusyOrganizers()).thenReturn(busyOrganizers);

        List<Organizer> actualBusyOrganizers = adminController.getAllBusyOrganizer();

        assertEquals(busyOrganizers, actualBusyOrganizers);
        verify(adminService, times(1)).getAllBusyOrganizers();
    }

    @Test
    void testGetAllFreeOrganizer() {
        List<Organizer> freeOrganizers = Arrays.asList(new Organizer(), new Organizer());
        when(adminService.getAllFreeOrganizers()).thenReturn(freeOrganizers);

        List<Organizer> actualFreeOrganizers = adminController.getAllFreeOrganizer();

        assertEquals(freeOrganizers, actualFreeOrganizers);
        verify(adminService, times(1)).getAllFreeOrganizers();
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(adminService.getAllUser()).thenReturn(users);

        List<User> actualUsers = adminController.getAllUsers();

        assertEquals(users, actualUsers);
        verify(adminService, times(1)).getAllUser();
    }
    @Test
    void testGetAllGroups() {
        List<Group> groups = Arrays.asList(new Group(), new Group());
        when(adminService.getAllGroup()).thenReturn(groups);

        List<Group> actualGroups = adminController.getAllGroup();

        assertEquals(groups, actualGroups);
        verify(adminService, times(1)).getAllGroup();
    }

    @Test
    void testGetAllEvents() {
        List<Event> events = Arrays.asList(new Event(), new Event());
        when(adminService.getAllEvent()).thenReturn(events);

        List<Event> actualEvents = adminController.getAllEvent();

        assertEquals(events, actualEvents);
        verify(adminService, times(1)).getAllEvent();
    }

    @Test
    void testGetAllTouristSpots() {
        List<TouristSpot> touristSpots = Arrays.asList(new TouristSpot(), new TouristSpot());
        when(adminService.getAllSpot()).thenReturn(touristSpots);

        List<TouristSpot> actualTouristSpots = adminController.getAllTouristSpot();

        assertEquals(touristSpots, actualTouristSpots);
        verify(adminService, times(1)).getAllSpot();
    }

    @Test
    void testGetUserById() {
        Integer userId = 1;
        User user = new User();
        when(adminService.getUserById(userId)).thenReturn(user);

        User actualUser = adminController.getUserById(userId);

        assertEquals(user, actualUser);
        verify(adminService, times(1)).getUserById(userId);
    }
    @Test
    void testGetParticipantById() {
        Integer participantId = 1;
        Participant participant = new Participant();
        when(adminService.getParticipantById(participantId)).thenReturn(participant);

        Participant actualParticipant = adminController.getParticipantById(participantId);

        assertEquals(participant, actualParticipant);
        verify(adminService, times(1)).getParticipantById(participantId);
    }

    @Test
    void testGetAllBusyParticipants() {
        List<Participant> busyParticipants = Arrays.asList(new Participant(), new Participant());
        when(adminService.getAllBusyParticipants()).thenReturn(busyParticipants);

        List<Participant> actualBusyParticipants = adminController.getAllBusyParticipants();

        assertEquals(busyParticipants, actualBusyParticipants);
        verify(adminService, times(1)).getAllBusyParticipants();
    }

    @Test
    void testGetAllFreeParticipants() {
        List<Participant> freeParticipants = Arrays.asList(new Participant(), new Participant());
        when(adminService.getAllFreeParticipants()).thenReturn(freeParticipants);

        List<Participant> actualFreeParticipants = adminController.getAllFreeParticipants();

        assertEquals(freeParticipants, actualFreeParticipants);
        verify(adminService, times(1)).getAllFreeParticipants();
    }

    @Test
    void testGetGroupById() {
        Integer groupId = 1;
        Group group = new Group();
        when(adminService.getGroupById(groupId)).thenReturn(group);

        Group actualGroup = adminController.getGroupById(groupId);

        assertEquals(group, actualGroup);
        verify(adminService, times(1)).getGroupById(groupId);
    }	
    @Test
    void testGetAllActiveGroups() {
        List<Group> activeGroups = Arrays.asList(new Group(), new Group());
        when(adminService.getAllActiveGroups()).thenReturn(activeGroups);

        List<Group> actualActiveGroups = adminController.getAllActiveGroups();

        assertEquals(activeGroups, actualActiveGroups);
        verify(adminService, times(1)).getAllActiveGroups();
    }

    @Test
    void testGetAllInActiveGroups() {
        List<Group> inActiveGroups = Arrays.asList(new Group(), new Group());
        when(adminService.getAllInActiveGroups()).thenReturn(inActiveGroups);

        List<Group> actualInActiveGroups = adminController.getAllInActiveGroups();

        assertEquals(inActiveGroups, actualInActiveGroups);
        verify(adminService, times(1)).getAllInActiveGroups();
    }

    @Test
    void testGetEventById() {
        Integer eventId = 1;
        Event event = new Event();
        when(adminService.getEventById(eventId)).thenReturn(event);

        Event actualEvent = adminController.getEventById(eventId);

        assertEquals(event, actualEvent);
        verify(adminService, times(1)).getEventById(eventId);
    }

    @Test
    void testGetAllInActiveEvents() {
        List<Event> inActiveEvents = Arrays.asList(new Event(), new Event());
        when(adminService.getAllInActiveEvents()).thenReturn(inActiveEvents);

        List<Event> actualInActiveEvents = adminController.getAllInActiveEvents();

        assertEquals(inActiveEvents, actualInActiveEvents);
        verify(adminService, times(1)).getAllInActiveEvents();
    }
    @Test
    void testRemoveOrganizer() {
        Integer organizerId = 1;
        String expectedResponse = "Success";

        when(adminService.removeOrganizerById(anyInt())).thenReturn(expectedResponse);

        String actualResponse = adminController.removeOrganizer(organizerId);

        assertEquals(expectedResponse, actualResponse);
        verify(adminService, times(1)).removeOrganizerById(anyInt());
    }
    

    @Test
    void testRemoveParticipant() {
        Integer participantId = 1;
        String expectedResponse = "Success";

        when(adminService.removeParticipantById(anyInt())).thenReturn(expectedResponse);

        String actualResponse = adminController.removeParticipant(participantId);

        assertEquals(expectedResponse, actualResponse);
        verify(adminService, times(1)).removeParticipantById(anyInt());
    }

    @Test
    void testRemoveGroup() {
        Integer groupId = 1;
        String expectedResponse = "Success";

        when(adminService.removeGroupById(anyInt())).thenReturn(expectedResponse);

        String actualResponse = adminController.removeGroup(groupId);

        assertEquals(expectedResponse, actualResponse);
        verify(adminService, times(1)).removeGroupById(anyInt());
    }

    @Test
    void testRemoveEvent() {
        Integer eventId = 1;
        String expectedResponse = "Success";

        when(adminService.removeEventById(anyInt())).thenReturn(expectedResponse);

        String actualResponse = adminController.removeEvent(eventId);

        assertEquals(expectedResponse, actualResponse);
        verify(adminService, times(1)).removeEventById(anyInt());
    }

    @Test
    void testRemoveInActiveEvents() {
        String expectedResponse = "Success";

        when(adminService.removeAllInActiveEvents()).thenReturn(expectedResponse);

        String actualResponse = adminController.removeInActiveEvents();

        assertEquals(expectedResponse, actualResponse);
        verify(adminService, times(1)).removeAllInActiveEvents();
    }

    @Test
    void testRemoveTouristSpot() {
        Integer spotId = 1;
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Success", HttpStatus.OK);

        when(adminService.removeTouristSpotById(anyInt())).thenReturn(expectedResponse);

        ResponseEntity<String> actualResponse = adminController.removeTouristSpot(spotId);

        assertEquals(expectedResponse, actualResponse);
        verify(adminService, times(1)).removeTouristSpotById(anyInt());
    }

    @Test
    void testRemoveFeedBack() {
        String feedbackId = "123";
        String expectedResponse = "Success";

        when(adminService.deleteFeedBack(anyString())).thenReturn(expectedResponse);

        String actualResponse = adminController.removeFeedBack(feedbackId);

        assertEquals(expectedResponse, actualResponse);
        verify(adminService, times(1)).deleteFeedBack(anyString());
    }
}
