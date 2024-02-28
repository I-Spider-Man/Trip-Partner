package com.example.demo.Service.GroupServices;

import com.example.demo.Model.Event;
import com.example.demo.Model.EventFeedback;
import com.example.demo.Repository.EventFeedBackRepository;
import com.example.demo.Repository.EventImageRepository;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Service.Scheduling;
import com.example.demo.Service.StorageService;
import com.example.demo.Service.Event.EventServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventImageRepository eventImageRepository;

    @Mock
    private EventFeedBackRepository eventFeedBackRepository;

    @Mock
    private Scheduling scheduling;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllEvents() {
        // Your implementation here
    }

    @Test
    void testGetAllActiveEvents() {
        // Your implementation here
    }

    @Test
    void testGetAllPopularEvents() {
        // Your implementation here
    }

    @Test
    void testAddEvent() throws IOException {
        // Your implementation here
    }

    @Test
    void testAddAllEvents() {
        // Your implementation here
    }

    @Test
    void testDeleteEventById() {
        // Your implementation here
    }

    @Test
    void testGetEventById() {
        // Your implementation here
    }

    @Test
    void testFeedBackSubmission() {
        // Your implementation here
    }

    @Test
    void testGetEventByEventName() {
        // Your implementation here
    }

    @Test
    void testGetAllFeedBackByEventId() {
        // Your implementation here
    }


    @Test
    void testGetAllPicturesByEventId() {
        // Your implementation here
    }
}
