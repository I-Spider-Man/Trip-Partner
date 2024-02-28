package com.example.demo.Repository;

import com.example.demo.Model.AdminFeedback;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@AutoConfigureMockMvc
public class AdminFeedBackRepositoryTest {

    @Autowired
    private AdminFeedBackRepository adminFeedBackRepository;

    @Test
    public void testFindAllByUserId() {
        // Create and save a sample admin feedback
        AdminFeedback adminFeedback = new AdminFeedback();
        adminFeedback.setUserId(1); // Set user ID
        adminFeedback.setFeedBack("Test feedback");
        adminFeedBackRepository.save(adminFeedback);

        // Perform the test
        List<AdminFeedback> feedbackList = adminFeedBackRepository.findAllByUserId(1);

        // Assert
        assertNotNull(feedbackList);
        assertEquals(1, feedbackList.size());
        assertEquals("Test feedback", feedbackList.get(0).getFeedBack());
    }
}
