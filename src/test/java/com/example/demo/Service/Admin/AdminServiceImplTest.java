package com.example.demo.Service.Admin;
import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.Admin.AdminService;
import com.example.demo.Service.Admin.AdminServiceImpl;
import com.example.demo.Service.Event.EventService;
import com.example.demo.Service.GroupServices.GroupService;
import com.example.demo.Service.Organizer.OrganizerService;
import com.example.demo.Service.OtpMailService.SMTP_mailService;
import com.example.demo.Service.ParticipantServices.ParticipantService;
import com.example.demo.Service.TouristSpot.TouristSpotService;
import com.example.demo.Service.UserServices.UserService;
import com.example.demo.config.CustomUserDetailsService;
import com.example.demo.config.JwtProvider;

import jakarta.mail.MessagingException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AdminServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private AdminFeedBackRepository adminFeedBackRepository;

    @Mock
    private CustomUserDetailsService customerUserDetails;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private SMTP_mailService mailService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private ParticipantService participantService;

    @Mock
    private GroupService groupService;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private TouristSpotService touristSpotService;

    @Mock
    private OrganizerService organizerService;

    @Mock
    private OrganizerRepository organizerRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Test your methods here...

    @Test
    void testAddManyUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.saveAll(users)).thenReturn(users);

        String result = adminService.addManyUsers(users);

        assertEquals("Success", result);
        verify(userRepository, times(1)).saveAll(users);
    }
    @Test
    void testReplyForUserFeedback() throws MessagingException {
        AdminFeedback feedback = new AdminFeedback();
        feedback.setUserId(1);
        feedback.setIndicate(true);
        feedback.setAdminReply("Reply message");

        // Create a mock User with a valid email
        User userEntity = new User();
        userEntity.setUserEmail("user@example.com");

        Optional<User> user = Optional.of(userEntity);
        when(userRepository.findById(feedback.getUserId())).thenReturn(user);

        String result = adminService.replyForUserFeedback(feedback);

        assertEquals("Reply successful", result);
        verify(adminFeedBackRepository, times(1)).save(feedback);
        verify(mailService, times(1)).sendMailService(eq("user@example.com"), anyString(), eq("Reply message"));
    }


    @Test
    void testGetAdminFeedBackByUserId() {
        Integer userId = 1;
        List<AdminFeedback> expectedFeedbacks = new ArrayList<>();
        when(adminFeedBackRepository.findAllByUserId(userId)).thenReturn(expectedFeedbacks);

        List<AdminFeedback> result = adminService.getAdminFeedBackByUserId(userId);

        assertEquals(expectedFeedbacks, result);
        verify(adminFeedBackRepository, times(1)).findAllByUserId(userId);
    }

    @Test
    void testGetAllAdminFeedBack() {
        List<AdminFeedback> feedbacks = new ArrayList<>();
        when(adminFeedBackRepository.findAll()).thenReturn(feedbacks);

        List<AdminFeedback> result = adminService.getAllAdminFeedBack();

        Comparator<AdminFeedback> feedbackComparator = Comparator
                .comparing((AdminFeedback feedback) -> !feedback.getIndicate())
                .thenComparing((AdminFeedback feedback) -> feedback.getAdminReply() == null);
        List<AdminFeedback> sortedFeedbacks = feedbacks.stream()
                .sorted(feedbackComparator)
                .collect(Collectors.toList());

        assertEquals(sortedFeedbacks, result);
        verify(adminFeedBackRepository, times(1)).findAll();
    }

    @Test
    void testAddEvent() throws IOException {
        Event newEvent = new Event();
        MultipartFile file = mock(MultipartFile.class);
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Event added successfully");
        when(eventService.addEvent(newEvent, file)).thenReturn(expectedResponse);

        ResponseEntity<String> result = adminService.addEvent(newEvent, file);

        assertEquals(expectedResponse, result);
        verify(eventService, times(1)).addEvent(newEvent, file);
    }

    @Test
    void testAddAllEvents() {
        List<Event> events = new ArrayList<>();
        String expectedResponse = "All events added successfully";
        when(eventService.addAllEvents(events)).thenReturn(expectedResponse);

        String result = adminService.addAllEvents(events);

        assertEquals(expectedResponse, result);
        verify(eventService, times(1)).addAllEvents(events);
    }

//    @Test
//    void testAddSpot() throws IOException {
//        TouristSpot spot = new TouristSpot();
//        MultipartFile spotPicture = mock(MultipartFile.class);
//        ResponseEntity<?> expectedResponse = ResponseEntity.ok("Spot added successfully");
//        when(touristSpotService.addSpot(spot, spotPicture)).thenReturn((ResponseEntity<?>) expectedResponse);
//
//        ResponseEntity<?> result = adminService.addSpot(spot, spotPicture);
//
//        assertEquals(expectedResponse, result);
//        verify(touristSpotService, times(1)).addSpot(spot, spotPicture);
//    }
    @Test
    void testAddAllSpots() {
        List<TouristSpot> spots = new ArrayList<>();
        String expectedResponse = "All spots added successfully";
        when(touristSpotService.addAllSpots(spots)).thenReturn(expectedResponse);

        String result = adminService.addAllSpots(spots);

        assertEquals(expectedResponse, result);
        verify(touristSpotService, times(1)).addAllSpots(spots);
    }

    @Test
    void testGetAllParticipant() {
        List<Participant> participants = new ArrayList<>();
        when(participantService.getAllParticipants()).thenReturn(participants);

        List<Participant> result = adminService.getAllParticipant();

        assertEquals(participants, result);
        verify(participantService, times(1)).getAllParticipants();
    }

    @Test
    void testGetAllBusyParticipants() {
        List<Participant> busyParticipants = new ArrayList<>();
        when(participantRepository.findAllByParticipantStatus(UserStatus.Busy)).thenReturn(busyParticipants);

        List<Participant> result = adminService.getAllBusyParticipants();

        assertEquals(busyParticipants, result);
        verify(participantRepository, times(1)).findAllByParticipantStatus(UserStatus.Busy);
    }

    @Test
    void testGetAllFreeParticipants() {
        List<Participant> freeParticipants = new ArrayList<>();
        when(participantRepository.findAllByParticipantStatus(UserStatus.Free)).thenReturn(freeParticipants);

        List<Participant> result = adminService.getAllFreeParticipants();

        assertEquals(freeParticipants, result);
        verify(participantRepository, times(1)).findAllByParticipantStatus(UserStatus.Free);
    }

    @Test
    void testGetAllParticipantByGroupId() {
        Integer groupId = 1;
        List<Participant> participants = new ArrayList<>();
        when(participantService.getAllParticipantsByGroupId(groupId)).thenReturn(participants);

        List<Participant> result = adminService.getAllParticipantByGroupId(groupId);

        assertEquals(participants, result);
        verify(participantService, times(1)).getAllParticipantsByGroupId(groupId);
    }

    @Test
    void testGetAllGroup() {
        List<Group> groups = new ArrayList<>();
        when(groupService.getAllGroups()).thenReturn(groups);

        List<Group> result = adminService.getAllGroup();

        assertEquals(groups, result);
        verify(groupService, times(1)).getAllGroups();
    }
    @Test
    void testGetAllActiveGroups() {
        List<Group> activeGroups = new ArrayList<>();
        when(groupRepository.findAllByGroupStatus(GroupStatus.Active)).thenReturn(activeGroups);

        List<Group> result = adminService.getAllActiveGroups();

        assertEquals(activeGroups, result);
        verify(groupRepository, times(1)).findAllByGroupStatus(GroupStatus.Active);
    }

    @Test
    void testGetAllInActiveGroups() {
        List<Group> inActiveGroups = new ArrayList<>();
        when(groupRepository.findAllByGroupStatus(GroupStatus.InActive)).thenReturn(inActiveGroups);

        List<Group> result = adminService.getAllInActiveGroups();

        assertEquals(inActiveGroups, result);
        verify(groupRepository, times(1)).findAllByGroupStatus(GroupStatus.InActive);
    }

    @Test
    void testGetAllUser() {
        List<User> users = new ArrayList<>();
        when(userService.getAllUser()).thenReturn(users);

        List<User> result = adminService.getAllUser();

        assertEquals(users, result);
        verify(userService, times(1)).getAllUser();
    }

    @Test
    void testGetAllEvent() {
        List<Event> events = new ArrayList<>();
        when(eventService.getAllEvents()).thenReturn(events);

        List<Event> result = adminService.getAllEvent();

        assertEquals(events, result);
        verify(eventService, times(1)).getAllEvents();
    }

    @Test
    void testGetAllInActiveEvents() {
        List<Event> inActiveEvents = new ArrayList<>();
        when(eventRepository.findAllByEventStatus(EventStatus.InActive)).thenReturn(inActiveEvents);

        List<Event> result = adminService.getAllInActiveEvents();

        assertEquals(inActiveEvents, result);
        verify(eventRepository, times(1)).findAllByEventStatus(EventStatus.InActive);
    }

    @Test
    void testGetAllActiveEvents() {
        List<Event> activeEvents = new ArrayList<>();
        when(eventRepository.findAllByEventStatus(EventStatus.Active)).thenReturn(activeEvents);

        List<Event> result = adminService.getAllActiveEvents();

        assertEquals(activeEvents, result);
        verify(eventRepository, times(1)).findAllByEventStatus(EventStatus.Active);
    }
    @Test
    void testGetAllSpot() {
        List<TouristSpot> spots = new ArrayList<>();
        when(touristSpotService.getAllSpots()).thenReturn(spots);

        List<TouristSpot> result = adminService.getAllSpot();

        assertEquals(spots, result);
        verify(touristSpotService, times(1)).getAllSpots();
    }

    @Test
    void testGetAllOrganizer() {
        List<Organizer> organizers = new ArrayList<>();
        when(organizerService.getAllOrganizer()).thenReturn(organizers);

        List<Organizer> result = adminService.getAllOrganizer();

        assertEquals(organizers, result);
        verify(organizerService, times(1)).getAllOrganizer();
    }

    @Test
    void testGetAllBusyOrganizers() {
        List<Organizer> busyOrganizers = new ArrayList<>();
        when(organizerRepository.findAllByOrganizerStatus(UserStatus.Busy)).thenReturn(busyOrganizers);

        List<Organizer> result = adminService.getAllBusyOrganizers();

        assertEquals(busyOrganizers, result);
        verify(organizerRepository, times(1)).findAllByOrganizerStatus(UserStatus.Busy);
    }

    @Test
    void testGetAllFreeOrganizers() {
        List<Organizer> freeOrganizers = new ArrayList<>();
        when(organizerRepository.findAllByOrganizerStatus(UserStatus.Free)).thenReturn(freeOrganizers);

        List<Organizer> result = adminService.getAllFreeOrganizers();

        assertEquals(freeOrganizers, result);
        verify(organizerRepository, times(1)).findAllByOrganizerStatus(UserStatus.Free);
    }

    @Test
    void testRemoveUserById() {
        int userId = 1;
        String expectedResult = "User removed successfully";
        when(userService.removeUserById(userId)).thenReturn(expectedResult);

        String result = adminService.removeUserById(userId);

        assertEquals(expectedResult, result);
        verify(userService, times(1)).removeUserById(userId);
    }

    @Test
    void testRemoveAllUser() {
        String expectedResult = "removed all users";

        String result = adminService.removeAllUser();

        assertEquals(expectedResult, result);
        verify(userRepository, times(1)).deleteAll();
    }
    @Test
    void testRemoveParticipantById() {
        int participantId = 1;
        String expectedResult = "Participant removed successfully";
        when(participantService.removeParticipantById(participantId)).thenReturn(expectedResult);

        String result = adminService.removeParticipantById(participantId);

        assertEquals(expectedResult, result);
        verify(participantService, times(1)).removeParticipantById(participantId);
    }

    @Test
    void testRemoveGroupById() {
        int groupId = 1;
        String expectedResult = "Group removed successfully";
        when(groupService.removeGroupById(groupId)).thenReturn(expectedResult);

        String result = adminService.removeGroupById(groupId);

        assertEquals(expectedResult, result);
        verify(groupService, times(1)).removeGroupById(groupId);
    }

    @Test
    void testRemoveEventById() {
        int eventId = 1;
        String expectedResult = "Event removed successfully";
        when(eventService.deleteEventById(eventId)).thenReturn(expectedResult);

        String result = adminService.removeEventById(eventId);

        assertEquals(expectedResult, result);
        verify(eventService, times(1)).deleteEventById(eventId);
    }

    @Test
    void testRemoveTouristSpotById() {
        int spotId = 1;
        ResponseEntity<String> expectedResult = ResponseEntity.ok("Spot removed successfully");
        when(touristSpotService.removeSpotById(spotId)).thenReturn(expectedResult);

        ResponseEntity<String> result = adminService.removeTouristSpotById(spotId);

        assertEquals(expectedResult, result);
        verify(touristSpotService, times(1)).removeSpotById(spotId);
    }

    @Test
    void testRemoveOrganizerById() {
        int organizerId = 1;
        String expectedResult = "Organizer removed successfully";
        when(organizerService.removeOrganizerById(organizerId)).thenReturn(expectedResult);

        String result = adminService.removeOrganizerById(organizerId);

        assertEquals(expectedResult, result);
        verify(organizerService, times(1)).removeOrganizerById(organizerId);
    }

    @Test
    void testRemoveAllInActiveEvents() {
        List<Event> inactiveEvents = new ArrayList<>();
        when(adminService.getAllInActiveEvents()).thenReturn(inactiveEvents);

        String expectedResult = "InActive Events removed successfully";
        String result = adminService.removeAllInActiveEvents();

        assertEquals(expectedResult, result);
        verify(eventRepository, times(1)).deleteAll(inactiveEvents);
    }
    @Test
    void testGetUserById() {
        int userId = 1;
        User expectedUser = new User(); // Set your expected user object
        when(userService.getUserById(userId)).thenReturn(expectedUser);

        User result = adminService.getUserById(userId);

        assertEquals(expectedUser, result);
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testGetParticipantById() {
        int participantId = 1;
        Participant expectedParticipant = new Participant(); // Set your expected participant object
        when(participantService.getParticipantById(participantId)).thenReturn(expectedParticipant);

        Participant result = adminService.getParticipantById(participantId);

        assertEquals(expectedParticipant, result);
        verify(participantService, times(1)).getParticipantById(participantId);
    }

    @Test
    void testGetGroupById() {
        int groupId = 1;
        Group expectedGroup = new Group(); // Set your expected group object
        when(groupService.getGroupById(groupId)).thenReturn(expectedGroup);

        Group result = adminService.getGroupById(groupId);

        assertEquals(expectedGroup, result);
        verify(groupService, times(1)).getGroupById(groupId);
    }

    @Test
    void testGetEventById() {
        int eventId = 1;
        Event expectedEvent = new Event(); // Set your expected event object
        when(eventService.getEventById(eventId)).thenReturn(expectedEvent);

        Event result = adminService.getEventById(eventId);

        assertEquals(expectedEvent, result);
        verify(eventService, times(1)).getEventById(eventId);
    }

    @Test
    void testGetSpotById() {
        int spotId = 1;
        TouristSpot expectedSpot = new TouristSpot(); // Set your expected spot object
        when(touristSpotService.getSpotById(spotId)).thenReturn(expectedSpot);

        TouristSpot result = adminService.getSpotById(spotId);

        assertEquals(expectedSpot, result);
        verify(touristSpotService, times(1)).getSpotById(spotId);
    }

    @Test
    void testDeleteFeedBack() {
        String feedbackId = "1";
        doNothing().when(adminFeedBackRepository).deleteById(feedbackId);

        String result = adminService.deleteFeedBack(feedbackId);

        assertEquals("Feed Back Deleted.", result);
        verify(adminFeedBackRepository, times(1)).deleteById(feedbackId);
    }

    @Test
    void testGetOrganizerById() {
        int organizerId = 1;
        Organizer expectedOrganizer = new Organizer(); // Set your expected organizer object
        when(organizerService.getOrganizerById(organizerId)).thenReturn(expectedOrganizer);

        Organizer result = adminService.getOrganizerById(organizerId);

        assertEquals(expectedOrganizer, result);
        verify(organizerService, times(1)).getOrganizerById(organizerId);
    }
//    @Test
//    void testAddUser() throws Exception {
//        MockitoAnnotations.initMocks(this);
//
//        User newUser = new User();
//        newUser.setUserEmail("test@example.com");
//        newUser.setUserPassword("password123"); // Set your desired password
//
//        // Mock userRepository.findByUserEmail
//        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());
//
//        // Mock passwordEncoder.encode
//        when(passwordEncoder.encode(newUser.getUserPassword())).thenReturn("encodedPassword123");
//
//        // Mock userRepository.save
//        when(userRepository.save(newUser)).thenReturn(newUser);
//
//        // Mock JwtProvider.generateToken
//        when(JwtProvider.generateToken(any(Authentication.class))).thenReturn("mockedToken");
//
//        AuthResponse result = adminService.addUser(newUser);
//
//        // Verify userRepository.findByUserEmail is called once with the correct email
//        verify(userRepository, times(1)).findByUserEmail(newUser.getUserEmail());
//
//        // Verify passwordEncoder.encode is called once with the correct password
//        verify(passwordEncoder, times(1)).encode(newUser.getUserPassword());
//
//        // Verify userRepository.save is called once with the correct user object
//        verify(userRepository, times(1)).save(newUser);
//
//        Object jwtProvider = null;
//		// Verify JwtProvider.generateToken is called once with the correct authentication object
//        ((JwtProvider) verify(jwtProvider, times(1))).generateToken(any(Authentication.class));
//
//        // Verify the result
//        AuthResponse expectedResponse = new AuthResponse("mockedToken", "Register Success");
//        assertEquals(expectedResponse, result);
//    }
    @Test
    void testAddUser_UserAlreadyExists_ExceptionThrown() {
        User existingUser = new User();
        existingUser.setUserEmail("test@example.com");

        when(userRepository.findByUserEmail(existingUser.getUserEmail())).thenReturn(Optional.of(existingUser));

        assertThrows(Exception.class, () -> adminService.addUser(existingUser));

        verify(userRepository, times(1)).findByUserEmail(existingUser.getUserEmail());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any());
    }

//    @Test
//    void testFindUserByJwt_Success() {
//        String jwt = "testJwt";
//        String userEmail = "test@example.com";
//        User expectedUser = new User();
//        expectedUser.setUserEmail(userEmail);
//
//        when(JwtProvider.getEmailFromJwtToken(jwt)).thenReturn(userEmail);
//        when(userRepository.findByUserEmail(userEmail)).thenReturn(Optional.of(expectedUser));
//
//        User result = adminService.findUserByJwt(jwt);
//
//        assertNotNull(result);
//        assertEquals(expectedUser, result);
//        Object jwtProvider = null;
//		((JwtProvider) verify(jwtProvider, times(1))).getEmailFromJwtToken(jwt);
//        verify(userRepository, times(1)).findByUserEmail(userEmail);
//    }

//    @Test
//    void testFindUserByJwt_UserNotFound_ReturnsNull() {
//        String jwt = "testJwt";
//        String userEmail = "test@example.com";
//
//        when(JwtProvider.getEmailFromJwtToken(jwt)).thenReturn(userEmail);
//        when(userRepository.findByUserEmail(userEmail)).thenReturn(Optional.empty());
//
//        User result = adminService.findUserByJwt(jwt);
//
//        assertNull(result);
//        Object jwtProvider = null;
//		((JwtProvider) verify(jwtProvider, times(1))).getEmailFromJwtToken(jwt);
//        verify(userRepository, times(1)).findByUserEmail(userEmail);
//    }

//    @Test
//    void testSigin_Success() {
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail("test@example.com");
//        loginRequest.setPassword("password");
//
//        org.springframework.security.core.Authentication Authentication = mock(Authentication.class);
//        String jwt = "testJwt";
//        AuthResponse expectedResponse = new AuthResponse(jwt, "Login Success");
//
//        when(adminService.authencate(loginRequest.getEmail(), loginRequest.getPassword())).thenReturn(Authentication);
//        when(JwtProvider.generateToken(Authentication)).thenReturn(jwt);
//
//        AuthResponse result = adminService.sigin(loginRequest);
//
//        assertNotNull(result);
//        assertEquals(expectedResponse, result);
//        verify(adminService, times(1)).authencate(loginRequest.getEmail(), loginRequest.getPassword());
//        Object jwtProvider;
//		((JwtProvider) verify(jwtProvider, times(1))).generateToken(Authentication);
//    }

//    @Test
//    void testAuthencate_Success() {
//        String email = "test@example.com";
//        String password = "password";
//        UserDetails userDetails = mock(UserDetails.class);
//        Authentication expectedAuthentication = mock(Authentication.class);
//
//        when(CustomUserDetailsService.loadUserByUsername(email)).thenReturn(userDetails);
//        when(passwordEncoder.matches(password, userDetails.getPassword())).thenReturn(true);
//        when(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())).thenReturn(expectedAuthentication);
//
//        Authentication result = adminService.authencate(email, password);
//
//        assertNotNull(result);
//        assertEquals(expectedAuthentication, result);
//        verify(customUserDetailsService, times(1)).loadUserByUsername(email);
//        verify(passwordEncoder, times(1)).matches(password, userDetails.getPassword());
//        verify(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()), times(1));
//    }

//    @Test
//    void testAuthencate_InvalidUsername_ExceptionThrown() {
//        String email = "test@example.com";
//        String password = "password";
//
//        when(CustomUserDetailsService.loadUserByUsername(email)).thenReturn(null);
//
//        assertThrows(BadCredentialsException.class, () -> adminService.authencate(email, password));
//
//        verify(customUserDetailsService, times(1)).loadUserByUsername(email);
//        verify(passwordEncoder, never()).matches(anyString(), anyString());
//        verify(new UsernamePasswordAuthenticationToken(any(), any(), any()), never());
//    }

//    @Test
//    void testAuthencate_PasswordNotMatch_ExceptionThrown() {
//        String email = "test@example.com";
//        String password = "password";
//        UserDetails userDetails = mock(UserDetails.class);
//
//        when(customUserDetailsService.loadUserByUsername(email)).thenReturn(userDetails);
//        when(passwordEncoder.matches(password, userDetails.getPassword())).thenReturn(false);
//
//        assertThrows(BadCredentialsException.class, () -> adminService.authencate(email, password));
//
//        verify(customUserDetailsService, times(1)).loadUserByUsername(email);
//        verify(passwordEncoder, times(1)).matches(password, userDetails.getPassword());
//        verify(new UsernamePasswordAuthenticationToken(any(), any(), any()), never());
//    }
}
