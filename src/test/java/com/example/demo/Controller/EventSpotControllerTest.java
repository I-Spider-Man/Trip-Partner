package com.example.demo.Controller;

import com.example.demo.Controller.EventSpotController;
import com.example.demo.Model.*;
import com.example.demo.Service.Event.EventServiceImpl;
import com.example.demo.Service.GroupServices.GroupService;
import com.example.demo.Service.Suggestions.SuggestionService;
import com.example.demo.Service.TouristSpot.TouristSpotServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EventSpotControllerTest {

    @Mock
    private EventServiceImpl eventService;

    @Mock
    private GroupService groupService;

    @Mock
    private TouristSpotServiceImpl spotService;

    @Mock
    private SuggestionService suggestionService;

    @InjectMocks
    private EventSpotController eventSpotController;

    public EventSpotControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllActiveEvents() {
        List<Event> activeEvents = Arrays.asList(new Event(), new Event());
        when(eventService.getAllActiveEvents()).thenReturn(activeEvents);

        List<Event> actualActiveEvents = eventSpotController.getAllActiveEvents();

        assertEquals(activeEvents, actualActiveEvents);
        verify(eventService, times(1)).getAllActiveEvents();
    }

    @Test
    void testGetEventByName() {
        String eventName = "EventName";
        ResponseEntity<Event> eventResponseEntity = ResponseEntity.ok(new Event());
        when(eventService.getEventByEventName(eventName)).thenReturn(eventResponseEntity);

        ResponseEntity<Event> actualEventResponse = eventSpotController.getEventByName(eventName);

        assertEquals(eventResponseEntity, actualEventResponse);
        verify(eventService, times(1)).getEventByEventName(eventName);
    }
    @Test
    void testSubmitEventFeedback() {
        Integer eventId = 1;
        EventFeedback.Feedback feedback = new EventFeedback.Feedback();
        ResponseEntity<String> expectedResponseEntity = ResponseEntity.ok("Success");

        when(eventService.feedBackSubmission(eventId, feedback)).thenReturn(expectedResponseEntity);

        ResponseEntity<String> actualResponseEntity = eventSpotController.submitEventFeedBack(eventId, feedback);

        assertEquals(expectedResponseEntity, actualResponseEntity);
        verify(eventService, times(1)).feedBackSubmission(eventId, feedback);
    }

    @Test
    void testGetAllFeedbackByEventId() {
        Integer eventId = 1;
        List<EventFeedback.Feedback> feedbackList = Arrays.asList(new EventFeedback.Feedback(), new EventFeedback.Feedback());
        ResponseEntity<List<EventFeedback.Feedback>> expectedResponseEntity = ResponseEntity.ok(feedbackList);

        when(eventService.getAllFeedBackByEventId(eventId)).thenReturn(expectedResponseEntity);

        ResponseEntity<List<EventFeedback.Feedback>> actualResponseEntity = eventSpotController.eventFeedBack(eventId);

        assertEquals(expectedResponseEntity, actualResponseEntity);
        verify(eventService, times(1)).getAllFeedBackByEventId(eventId);
    }

    @Test
    void testGetEventById() {
        Integer eventId = 1;
        Event expectedEvent = new Event();
        when(eventService.getEventById(eventId)).thenReturn(expectedEvent);

        Event actualEvent = eventSpotController.getEventById(eventId);

        assertEquals(expectedEvent, actualEvent);
        verify(eventService, times(1)).getEventById(eventId);
    }

    @Test
    void testGetAllGroupsByEventName() {
        String eventName = "EventName";
        List<Group> groups = Arrays.asList(new Group(), new Group());
        when(groupService.getAllGroupByEventName(eventName)).thenReturn(groups);

        List<Group> actualGroups = eventSpotController.getGrpByEventName(eventName);

        assertEquals(groups, actualGroups);
        verify(groupService, times(1)).getAllGroupByEventName(eventName);
    }
    @Test
    void testGetAllFeedbackBySpotId() {
        Integer spotId = 1;
        List<TouristSpotFeedback.Feedback> feedbackList = Arrays.asList(new TouristSpotFeedback.Feedback(), new TouristSpotFeedback.Feedback());
        ResponseEntity<List<TouristSpotFeedback.Feedback>> expectedResponseEntity = ResponseEntity.ok(feedbackList);

        when(spotService.getAllFeedbackBySpotId(spotId)).thenReturn(expectedResponseEntity);

        ResponseEntity<List<TouristSpotFeedback.Feedback>> actualResponseEntity = eventSpotController.getAllFeedBackOfSpot(spotId);

        assertEquals(expectedResponseEntity, actualResponseEntity);
        verify(spotService, times(1)).getAllFeedbackBySpotId(spotId);
    }

    @Test
    void testGetAllEventPicturesById() {
        Integer eventId = 1;
        List<String> pictureList = Arrays.asList("picture1.jpg", "picture2.jpg");
        
        when(eventService.getAllPicturesByEventId(eventId)).thenReturn(pictureList);

        List<String> actualPictureList = eventSpotController.getAllEventPictureById(eventId);

        assertEquals(pictureList, actualPictureList);
        verify(eventService, times(1)).getAllPicturesByEventId(eventId);
    }

//    @Test
//    void testUpdateEventPicture() throws IOException {
//        Integer eventId = 1;
//        MultipartFile eventPicture = new MockMultipartFile("eventPicture", "eventPicture.jpg", "image/jpeg", "eventPictureData".getBytes());
//
//        doNothing().when(spotService).addSpotPictures(eventId, eventPicture);
//
//        eventSpotController.updateEventPicture(eventId, eventPicture);
//
//        verify(spotService, times(1)).addSpotPictures(eventId, eventPicture);
//    }

    @Test
    void testSubmitSpotFeedback() {
        Integer spotId = 1;
        TouristSpotFeedback.Feedback feedback = new TouristSpotFeedback.Feedback();
        ResponseEntity<String> expectedResponseEntity = ResponseEntity.ok("Success");

        when(spotService.submitFeedback(spotId, feedback)).thenReturn(expectedResponseEntity);

        ResponseEntity<String> actualResponseEntity = eventSpotController.getAllSpotFeedBack(spotId, feedback);

        assertEquals(expectedResponseEntity, actualResponseEntity);
        verify(spotService, times(1)).submitFeedback(spotId, feedback);
    }

    @Test
    void testUpdateSpotPicture() throws IOException {
        Integer spotId = 1;
        MultipartFile spotPicture = new MockMultipartFile("spotPicture", "spotPicture.jpg", "image/jpeg", "spotPictureData".getBytes());

        doNothing().when(spotService).addSpotPictures(spotId, spotPicture);

        eventSpotController.updateSpotPicture(spotId, spotPicture);

        verify(spotService, times(1)).addSpotPictures(spotId, spotPicture);
    }

    @Test
    void testGetAllSpots() {
        List<TouristSpot> spotList = Arrays.asList(new TouristSpot(), new TouristSpot());

        when(spotService.getAllSpots()).thenReturn(spotList);

        List<TouristSpot> actualSpotList = eventSpotController.getAllSpots();

        assertEquals(spotList, actualSpotList);
        verify(spotService, times(1)).getAllSpots();
    }
    @Test
    void testGetSpotById() {
        Integer spotId = 1;
        TouristSpot expectedSpot = new TouristSpot();

        when(spotService.getSpotById(spotId)).thenReturn(expectedSpot);

        TouristSpot actualSpot = eventSpotController.getSpotById(spotId);

        assertEquals(expectedSpot, actualSpot);
        verify(spotService, times(1)).getSpotById(spotId);
    }

    @Test
    void testGetSpotBySpotName() {
        String spotName = "TestSpot";
        ResponseEntity<TouristSpot> expectedResponseEntity = ResponseEntity.ok(new TouristSpot());

        when(spotService.getSpotBySpotName(spotName)).thenReturn(expectedResponseEntity);

        ResponseEntity<TouristSpot> actualResponseEntity = eventSpotController.getSpotBySpotName(spotName);

        assertEquals(expectedResponseEntity, actualResponseEntity);
        verify(spotService, times(1)).getSpotBySpotName(spotName);
    }

    @Test
    void testGetAllPicturesBySpotId() {
        Integer spotId = 1;
        List<String> expectedPictures = Arrays.asList("pic1.jpg", "pic2.jpg");

        when(spotService.getSpotPictureById(spotId)).thenReturn(expectedPictures);

        List<String> actualPictures = eventSpotController.getAllPicturesBySpotId(spotId);

        assertEquals(expectedPictures, actualPictures);
        verify(spotService, times(1)).getSpotPictureById(spotId);
    }

    @Test
    void testGetGrpBySpotName() {
        String spotName = "TestSpot";
        List<Group> expectedGroups = Arrays.asList(new Group(), new Group());

        when(groupService.getAllGroupBySpotName(spotName)).thenReturn(expectedGroups);

        List<Group> actualGroups = eventSpotController.getGrpBySpotName(spotName);

        assertEquals(expectedGroups, actualGroups);
        verify(groupService, times(1)).getAllGroupBySpotName(spotName);
    }
    @Test
    void testGetAllPopularSpots() {
        List<TouristSpot> expectedSpots = Arrays.asList(new TouristSpot(), new TouristSpot());

        when(spotService.getAllPopularTouristSpot()).thenReturn(expectedSpots);

        List<TouristSpot> actualSpots = eventSpotController.getAllPopularSpots();

        assertEquals(expectedSpots, actualSpots);
        verify(spotService, times(1)).getAllPopularTouristSpot();
    }

    @Test
    void testGetAllPopularEvents() {
        List<Event> expectedEvents = Arrays.asList(new Event(), new Event());

        when(eventService.getAllPopularEvents()).thenReturn(expectedEvents);

        List<Event> actualEvents = eventSpotController.getAllPopularEvents();

        assertEquals(expectedEvents, actualEvents);
        verify(eventService, times(1)).getAllPopularEvents();
    }

    @Test
    void testGetAllNearByEventsForEvents() {
        Integer eventId = 1;
        List<Event> expectedEvents = Arrays.asList(new Event(), new Event());

        when(suggestionService.nearByEventsForEvents(eventId)).thenReturn(expectedEvents);

        List<Event> actualEvents = eventSpotController.getAllNearByEventsForEvents(eventId);

        assertEquals(expectedEvents, actualEvents);
        verify(suggestionService, times(1)).nearByEventsForEvents(eventId);
    }

    @Test
    void testGetAllNearBySpotsForEvents() {
        Integer eventId = 1;
        List<TouristSpot> expectedSpots = Arrays.asList(new TouristSpot(), new TouristSpot());

        when(suggestionService.nearByTouristSpotsForEvents(eventId)).thenReturn(expectedSpots);

        List<TouristSpot> actualSpots = eventSpotController.getAllNearBySpotsForEvents(eventId);

        assertEquals(expectedSpots, actualSpots);
        verify(suggestionService, times(1)).nearByTouristSpotsForEvents(eventId);
    }

    @Test
    void testGetAllNearByEventsForSpot() {
        Integer spotId = 1;
        List<Event> expectedEvents = Arrays.asList(new Event(), new Event());

        when(suggestionService.nearByEventsForTouristSpot(spotId)).thenReturn(expectedEvents);

        List<Event> actualEvents = eventSpotController.getAllNearByEventsForSpot(spotId);

        assertEquals(expectedEvents, actualEvents);
        verify(suggestionService, times(1)).nearByEventsForTouristSpot(spotId);
    }
    @Test
    void testGetAllNearBySpotsForSpots() {
        Integer spotId = 1;
        List<TouristSpot> expectedSpots = Arrays.asList(new TouristSpot(), new TouristSpot());

        when(suggestionService.nearByTouristSpotsForTouristSpot(spotId)).thenReturn(expectedSpots);

        List<TouristSpot> actualSpots = eventSpotController.getAllNearBySpotsForSpots(spotId);

        assertEquals(expectedSpots, actualSpots);
        verify(suggestionService, times(1)).nearByTouristSpotsForTouristSpot(spotId);
    }
    
}

