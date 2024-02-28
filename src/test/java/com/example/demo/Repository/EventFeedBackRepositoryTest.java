//package com.example.demo.Repository;
//
//import com.example.demo.Model.EventFeedback;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@DataMongoTest
//public class EventFeedBackRepositoryTest {
//
//    @Autowired
//    private EventFeedBackRepository eventFeedBackRepository;
//
//    private EventFeedback savedFeedback;
//
//    @BeforeEach
//    public void setUp() {
//        // Create and save a sample event feedback before each test
//        EventFeedback eventFeedback = new EventFeedback();
//        eventFeedback.setEventId(1); // Set event ID
//        eventFeedback.setFeedbackList("Test feedback");
//        savedFeedback = eventFeedBackRepository.save(eventFeedback);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        // Cleanup after each test
//        eventFeedBackRepository.deleteAll();
//    }
//
//    @Test
//    public void testFindByEventId() {
//        // Perform the test
//        Optional<EventFeedback> feedbackOptional = eventFeedBackRepository.findByEventId(1);
//
//        // Assert
//        assertNotNull(feedbackOptional);
//        assertTrue(feedbackOptional.isPresent());
//        assertEquals(savedFeedback.getEventId(), feedbackOptional.get().getEventId());
//        assertEquals(savedFeedback.getFeedback(), feedbackOptional.get().getFeedback());
//    }
//
//    @Test
//    public void testDeleteByEventId() {
//        // Perform the delete operation
//        eventFeedBackRepository.deleteByEventId(1);
//
//        // Verify deletion
//        assertFalse(eventFeedBackRepository.findByEventId(1).isPresent());
//    }
//}
