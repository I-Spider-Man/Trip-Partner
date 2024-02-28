package com.example.demo.Service.UserServices;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import com.example.demo.Model.AdminFeedback;
import com.example.demo.Model.Organizer;
import com.example.demo.Model.Participant;
import com.example.demo.Model.User;
import com.example.demo.Service.Admin.LoginRequest;
import com.example.demo.Service.UserServices.UserService;

public class UserServiceTest {

    @Test
    public void testUploadPost() throws IOException {
        UserService userService = mock(UserService.class);
        
        Integer userId = 1;
        String description = "Test Description";
        MockMultipartFile post = new MockMultipartFile("file", "test.txt", "text/plain", "Test Data".getBytes());

        when(userService.uploadPost(userId, description, post)).thenReturn(ResponseEntity.ok("Post uploaded successfully"));

        ResponseEntity<String> response = userService.uploadPost(userId, description, post);

        assertEquals(ResponseEntity.ok("Post uploaded successfully"), response);
    }

    @Test
    public void testGetAllUser() {
        UserService userService = mock(UserService.class);

        when(userService.getAllUser()).thenReturn(new ArrayList<>());

        List<User> userList = userService.getAllUser();

        assertNotNull(userList);
        assertEquals(0, userList.size());
    } @Test
    public void testUpdateUserEmail() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        String userEmail = "newemail@example.com";

        when(userService.updateUserEmail(userId, userEmail)).thenReturn(ResponseEntity.ok("Email updated successfully"));

        ResponseEntity<String> response = userService.updateUserEmail(userId, userEmail);

        assertEquals(ResponseEntity.ok("Email updated successfully"), response);
    }

    @Test
    public void testChangePassword() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        String newPassword = "newPassword123";

        when(userService.changePassword(userId, newPassword)).thenReturn(ResponseEntity.ok("Password changed successfully"));

        ResponseEntity<String> response = userService.changePassword(userId, newPassword);

        assertEquals(ResponseEntity.ok("Password changed successfully"), response);
    }

    @Test
    public void testUpdateUserProfile() throws IOException {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Test Data".getBytes());

        when(userService.updateUserProfile(userId, file)).thenReturn(ResponseEntity.ok("Profile updated successfully"));

        ResponseEntity<String> response = userService.updateUserProfile(userId, file);

        assertEquals(ResponseEntity.ok("Profile updated successfully"), response);
    }

    @Test
    public void testGetUserById() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        User expectedUser = new User(); // Create a sample user for testing

        when(userService.getUserById(userId)).thenReturn(expectedUser);

        User user = userService.getUserById(userId);

        assertEquals(expectedUser, user);
    }

    @Test
    public void testAddingFollower() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        Integer followingId = 2;

        when(userService.addingFollower(userId, followingId)).thenReturn(ResponseEntity.ok("Follower added successfully"));

        ResponseEntity<String> response = userService.addingFollower(userId, followingId);

        assertEquals(ResponseEntity.ok("Follower added successfully"), response);
    }

    @Test
    public void testBlockingFollower() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        Integer blockingId = 2;

        when(userService.blockingFollower(userId, blockingId)).thenReturn(ResponseEntity.ok("Follower blocked successfully"));

        ResponseEntity<String> response = userService.blockingFollower(userId, blockingId);

        assertEquals(ResponseEntity.ok("Follower blocked successfully"), response);
    }

    @Test
    public void testUnBlockingUser() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        Integer blockedUserId = 2;

        when(userService.unBlockingUser(userId, blockedUserId)).thenReturn(ResponseEntity.ok("User unblocked successfully"));

        ResponseEntity<String> response = userService.unBlockingUser(userId, blockedUserId);

        assertEquals(ResponseEntity.ok("User unblocked successfully"), response);
    }
    @Test
    public void testGetAllFollowers() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        List<User> expectedFollowers = new ArrayList<>(); // Create a sample list of followers for testing

        when(userService.getAllFollowers(userId)).thenReturn(expectedFollowers);

        List<User> followers = userService.getAllFollowers(userId);

        assertEquals(expectedFollowers, followers);
    }

    @Test
    public void testGetAllFollowing() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        List<User> expectedFollowing = new ArrayList<>(); // Create a sample list of following users for testing

        when(userService.getAllFollowing(userId)).thenReturn(expectedFollowing);

        List<User> following = userService.getAllFollowing(userId);

        assertEquals(expectedFollowing, following);
    }

    @Test
    public void testPostAdminFeedBack() {
        UserService userService = mock(UserService.class);

        AdminFeedback feedback = new AdminFeedback(); // Create a sample feedback for testing

        when(userService.postAdminFeedBack(feedback)).thenReturn("Feedback posted successfully");

        String response = userService.postAdminFeedBack(feedback);

        assertEquals("Feedback posted successfully", response);
    }

    @Test
    public void testGetAllBlocked() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        List<User> expectedBlockedUsers = new ArrayList<>(); // Create a sample list of blocked users for testing

        when(userService.getAllBlocked(userId)).thenReturn(expectedBlockedUsers);

        List<User> blockedUsers = userService.getAllBlocked(userId);

        assertEquals(expectedBlockedUsers, blockedUsers);
    }

    @Test
    public void testGetFollowersId() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        List<Integer> expectedFollowerIds = new ArrayList<>(); // Create a sample list of follower ids for testing

        when(userService.getFollowersId(userId)).thenReturn(expectedFollowerIds);

        List<Integer> followerIds = userService.getFollowersId(userId);

        assertEquals(expectedFollowerIds, followerIds);
    }

    @Test
    public void testGetFollowingId() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        List<Integer> expectedFollowingIds = new ArrayList<>(); // Create a sample list of following user ids for testing

        when(userService.getFollowingId(userId)).thenReturn(expectedFollowingIds);

        List<Integer> followingIds = userService.getFollowingId(userId);

        assertEquals(expectedFollowingIds, followingIds);
    }

    @Test
    public void testGetBlockedId() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        List<Integer> expectedBlockedIds = new ArrayList<>(); // Create a sample list of blocked user ids for testing

        when(userService.getBlockedId(userId)).thenReturn(expectedBlockedIds);

        List<Integer> blockedIds = userService.getBlockedId(userId);

        assertEquals(expectedBlockedIds, blockedIds);
    }
    @Test
    public void testUnFollowing() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        Integer followingId = 2;

        when(userService.unFollowing(userId, followingId)).thenReturn(ResponseEntity.ok("Unfollowed successfully"));

        ResponseEntity<String> response = userService.unFollowing(userId, followingId);

        assertEquals(ResponseEntity.ok("Unfollowed successfully"), response);
    }

    @Test
    public void testUpdateUser() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        User updateUser = new User(); // Create a sample user for testing

        when(userService.updateUser(userId, updateUser)).thenReturn(ResponseEntity.ok(updateUser));

        ResponseEntity<User> response = userService.updateUser(userId, updateUser);

        assertEquals(ResponseEntity.ok(updateUser), response);
    }

    @Test
    public void testGetOrganizerData() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        Organizer organizerData = new Organizer(); // Create a sample organizer data for testing

        when(userService.getOrganizerData(userId)).thenReturn(ResponseEntity.ok(organizerData));

        ResponseEntity<Organizer> response = userService.getOrganizerData(userId);

        assertEquals(ResponseEntity.ok(organizerData), response);
    }

    @Test
    public void testGetParticipantData() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;
        Participant participantData = new Participant(); // Create a sample participant data for testing

        when(userService.getParticipantData(userId)).thenReturn(ResponseEntity.ok(participantData));

        ResponseEntity<Participant> response = userService.getParticipantData(userId);

        assertEquals(ResponseEntity.ok(participantData), response);
    }

    @Test
    public void testAddUser() {
        UserService userService = mock(UserService.class);

        User user = new User(); // Create a sample user for testing

        when(userService.addUser(user)).thenReturn(ResponseEntity.ok("User added successfully"));

        ResponseEntity<String> response = userService.addUser(user);

        assertEquals(ResponseEntity.ok("User added successfully"), response);
    }

    @Test
    public void testRemoveUserById() {
        UserService userService = mock(UserService.class);

        Integer userId = 1;

        when(userService.removeUserById(userId)).thenReturn("User removed successfully");

        String response = userService.removeUserById(userId);

        assertEquals("User removed successfully", response);
    }

    @Test
    public void testGetAllByUserName() {
        UserService userService = mock(UserService.class);

        String userName = "testUser";
        List<User> expectedUsers = new ArrayList<>(); // Create a sample list of users for testing

        when(userService.getAllByUserName(userName)).thenReturn(expectedUsers);

        List<User> users = userService.getAllByUserName(userName);

        assertEquals(expectedUsers, users);
    }

    @Test
    public void testGetByUserEmail() {
        UserService userService = mock(UserService.class);

        String userEmail = "test@example.com";
        User expectedUser = new User(); // Create a sample user for testing

        when(userService.getByUserEmail(userEmail)).thenReturn(expectedUser);

        User user = userService.getByUserEmail(userEmail);

        assertEquals(expectedUser, user);
    }

    @Test
    public void testForgotPassword() {
        UserService userService = mock(UserService.class);

        String userEmail = "test@example.com";

        when(userService.forgotPassword(userEmail)).thenReturn(ResponseEntity.ok("Password reset email sent"));

        ResponseEntity<String> response = userService.forgotPassword(userEmail);

        assertEquals(ResponseEntity.ok("Password reset email sent"), response);
    }

//    @Test
//    public void testSigninUser() {
//        UserService userService = mock(UserService.class);
//
//        LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
//
//        when(userService.signinUser(loginRequest)).thenReturn((ResponseEntity<?>) ResponseEntity.ok("User signed in successfully"));
//
//        ResponseEntity<?> response = userService.signinUser(loginRequest);
//
//        assertEquals(ResponseEntity.ok("User signed in successfully"), response);
//    }

    // Similar tests for other methods in UserService interface...

//    @Test
//    public void testSigninUser() {
//        UserService userService = mock(UserService.class);
//
//        LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
//
//        when(userService.signinUser(loginRequest)).thenReturn(ResponseEntity.ok("User signed in successfully"));
//
//        ResponseEntity<?> response = userService.signinUser(loginRequest);
//
//        assertEquals(ResponseEntity.ok("User signed in successfully"), response);
//    }
}
