package com.example.demo.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.Model.Event;
import com.example.demo.Model.EventStatus;

@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    private Event savedEvent;

    @BeforeEach
    public void setUp() {
        // Create and save an event before each test
        Event event = new Event();
        event.setEventName("TestEvent");
        event.setEventStatus(EventStatus.InActive);
 
        savedEvent = eventRepository.save(event);
    }

    @AfterEach
    public void tearDown() {
        // Cleanup after each test
        eventRepository.deleteAll();
    }

    @Test
    public void testFindByEventName() {
        // Act
        Optional<Event> foundEventOptional = eventRepository.findByEventName("TestEvent");

        // Assert
        assertNotNull(foundEventOptional);
        assertTrue(foundEventOptional.isPresent());
        assertEquals(savedEvent.getEventName(), foundEventOptional.get().getEventName());
    }

    @Test
    public void testFindAllByEventStatus() {
        // Act
        List<Event> foundEvents = eventRepository.findAllByEventStatus(EventStatus.InActive);

        // Assert
        assertNotNull(foundEvents);
        assertEquals(1, foundEvents.size());
        assertEquals(EventStatus.InActive, foundEvents.get(0).getEventStatus());
    }

//    @Test
//    public void testFindAllByLocationPostalCode() {
//        // Act
//        List<Event> foundEvents = eventRepository.findAllByLocationPostalCode("12345");
//
//        // Assert
//        assertNotNull(foundEvents);
//        assertEquals(1, foundEvents.size());
//        assertEquals("12345", foundEvents.get(0).getLocationPostalCode());
//    }

    // Add more test methods as needed
}
