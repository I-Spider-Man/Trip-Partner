package com.example.demo.Service.Admin;

import com.example.demo.Model.*;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminServiceTest {

    private AdminService adminService = mock(AdminService.class);

    @Test
    void addManyUsers() {
        // Your test logic here
    }

    @Test
    void replyForUserFeedback() throws MessagingException {
        // Your test logic here
    }

    @Test
    void getAdminFeedBackByUserId() {
        // Your test logic here
    }

    @Test
    void getAllAdminFeedBack() {
        // Your test logic here
    }

    @Test
    void addEvent() throws IOException {
        // Your test logic here
    }

    @Test
    void addAllEvents() {
        // Your test logic here
    }

    @Test
    void addSpot() throws IOException {
        // Your test logic here
    }

    @Test
    void addAllSpots() {
        // Your test logic here
    }

    // Add tests for other methods in a similar fashion

    @Test
    void removeUserById() {
        // Your test logic here
    }

    @Test
    void removeAllUser() {
        // Your test logic here
    }

    @Test
    void removeParticipantById() {
        // Your test logic here
    }

    @Test
    void removeGroupById() {
        // Your test logic here
    }

    @Test
    void removeEventById() {
        // Your test logic here
    }

    @Test
    void removeTouristSpotById() {
        // Your test logic here
    }

    // Add tests for other removal methods

    @Test
    void getUserById() {
        // Your test logic here
    }

    @Test
    void getParticipantById() {
        // Your test logic here
    }

    @Test
    void getGroupById() {
        // Your test logic here
    }

    @Test
    void getEventById() {
        // Your test logic here
    }

    @Test
    void getSpotById() {
        // Your test logic here
    }

    @Test
    void deleteFeedBack() {
        // Your test logic here
    }

    @Test
    void getOrganizerById() {
        // Your test logic here
    }

    @Test
    void addUser() throws Exception {
        // Your test logic here
    }

    @Test
    void signIn() {
        // Your test logic here
    }

    @Test
    void findUserByJwt() {
        // Your test logic here
    }
}

