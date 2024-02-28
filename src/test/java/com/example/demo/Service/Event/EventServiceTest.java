package com.example.demo.Service.Event;

import com.example.demo.Model.Event;
import com.example.demo.Model.EventFeedback;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EventServiceTest {

    private EventService eventService = mock(EventService.class);

    @Test
    void getAllEvents() {
        // Your test logic here
        // Example: when(eventService.getAllEvents()).thenReturn(yourExpectedList);
        // assertEquals(yourExpectedList, eventService.getAllEvents());
    }

    @Test
    void getAllActiveEvents() {
        // Your test logic here
    }

    @Test
    void getAllPopularEvents() {
        // Your test logic here
    }

    @Test
    void addEvent() throws IOException {
        // Your test logic here
        // Example:
        // Event newEvent = new Event();
        // MultipartFile eventImage = new MockMultipartFile("image", "test.jpg", "image/jpeg", "image data".getBytes());
        // when(eventService.addEvent(newEvent, eventImage)).thenReturn(ResponseEntity.ok("Event added successfully"));
        // ResponseEntity<String> response = eventService.addEvent(newEvent, eventImage);
        // assertEquals("Event added successfully", response.getBody());
    }

    @Test
    void addAllEvents() {
        // Your test logic here
    }

    @Test
    void deleteEventById() {
        // Your test logic here
    }

    @Test
    void getEventById() {
        // Your test logic here
    }

    @Test
    void feedBackSubmission() {
        // Your test logic here
    }

    @Test
    void getEventByEventName() {
        // Your test logic here
    }

    @Test
    void getAllFeedBackByEventId() {
        // Your test logic here
    }
}
