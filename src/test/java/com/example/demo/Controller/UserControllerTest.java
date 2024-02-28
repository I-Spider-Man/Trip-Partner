package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.Admin.LoginRequest;
import com.example.demo.Service.OtpMailService.SMTP_mailService;
import com.example.demo.Service.StorageService;
import com.example.demo.Service.UserServices.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private SMTP_mailService mailService;

    @Mock
    private UserService userService;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
        // Initialize Mockito annotations
    }

    @Test
    void testGetAllUser() {
        List<User> expectedUsers = Arrays.asList(new User(), new User());

        when(userService.getAllUser()).thenReturn(expectedUsers);

        List<User> actualUsers = userController.getAllUser();

        assertEquals(expectedUsers, actualUsers);
        verify(userService, times(1)).getAllUser();
    }
    @Test
    void testGetUserById() {
        Integer userId = 1;
        User expectedUser = new User();

        when(userService.getUserById(userId)).thenReturn(expectedUser);

        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(expectedUser, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testUserFollowingUser() {
        Integer userId = 1;
        Integer followingId = 2;

        when(userService.addingFollower(userId, followingId)).thenReturn(ResponseEntity.ok("User followed successfully"));

        ResponseEntity<String> response = userController.userFollowingUser(userId, followingId);

        assertEquals(ResponseEntity.ok("User followed successfully"), response);
        verify(userService, times(1)).addingFollower(userId, followingId);
    }

    @Test
    void testUserUnFollowing() {
        Integer userId = 1;
        Integer followingId = 2;

        when(userService.unFollowing(userId, followingId)).thenReturn(ResponseEntity.ok("User unfollowed successfully"));

        ResponseEntity<String> response = userController.userUnFollowing(userId, followingId);

        assertEquals(ResponseEntity.ok("User unfollowed successfully"), response);
        verify(userService, times(1)).unFollowing(userId, followingId);
    }

    @Test
    void testUserBlocking() {
        Integer userId = 1;
        Integer blockingId = 2;

        when(userService.blockingFollower(userId, blockingId)).thenReturn(ResponseEntity.ok("User blocked successfully"));

        ResponseEntity<String> response = userController.userBlocking(userId, blockingId);

        assertEquals(ResponseEntity.ok("User blocked successfully"), response);
        verify(userService, times(1)).blockingFollower(userId, blockingId);
    }

    @Test
    void testUserUnBlocking() {
        Integer userId = 1;
        Integer blockedUserId = 2;

        when(userService.unBlockingUser(userId, blockedUserId)).thenReturn(ResponseEntity.ok("User unblocked successfully"));

        ResponseEntity<String> response = userController.userUnBlocking(userId, blockedUserId);

        assertEquals(ResponseEntity.ok("User unblocked successfully"), response);
        verify(userService, times(1)).unBlockingUser(userId, blockedUserId);
    }

    @Test
    void testUpdateUser() {
        Integer userId = 1;
        User updatedUser = new User();

        when(userService.updateUser(userId, updatedUser)).thenReturn(ResponseEntity.ok(updatedUser));

        ResponseEntity<User> response = userController.updateUser(userId, updatedUser);

        assertEquals(updatedUser, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).updateUser(userId, updatedUser);
    }

    @Test
    void testGetOrganizerData() {
        Integer userId = 1;
        Organizer expectedOrganizer = new Organizer();

        when(userService.getOrganizerData(userId)).thenReturn(ResponseEntity.ok(expectedOrganizer));

        ResponseEntity<Organizer> response = userController.getOrganizerData(userId);

        assertEquals(expectedOrganizer, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).getOrganizerData(userId);
    }

    @Test
    void testGetFollowersId() {
        Integer userId = 1;
        List<Integer> expectedFollowersId = Arrays.asList(2, 3);

        when(userService.getFollowersId(userId)).thenReturn(expectedFollowersId);

        List<Integer> actualFollowersId = userController.getFollowersId(userId);

        assertEquals(expectedFollowersId, actualFollowersId);
        verify(userService, times(1)).getFollowersId(userId);
    }
    @Test
    void testGetFollowingId() {
        Integer userId = 1;
        List<Integer> expectedFollowingIds = Arrays.asList(2, 3);

        when(userService.getFollowingId(userId)).thenReturn(expectedFollowingIds);

        List<Integer> actualFollowingIds = userController.getFollowingId(userId);

        assertEquals(expectedFollowingIds, actualFollowingIds);
        verify(userService, times(1)).getFollowingId(userId);
    }

    @Test
    void testGetBlockedId() {
        Integer userId = 1;
        List<Integer> expectedBlockedIds = Arrays.asList(4, 5);

        when(userService.getBlockedId(userId)).thenReturn(expectedBlockedIds);

        List<Integer> actualBlockedIds = userController.getBlockedId(userId);

        assertEquals(expectedBlockedIds, actualBlockedIds);
        verify(userService, times(1)).getBlockedId(userId);
    }

    @Test
    void testGetAllFollowers() {
        Integer userId = 1;
        List<User> expectedFollowers = Arrays.asList(new User(), new User());

        when(userService.getAllFollowers(userId)).thenReturn(expectedFollowers);

        List<User> actualFollowers = userController.getAllFollowers(userId);

        assertEquals(expectedFollowers, actualFollowers);
        verify(userService, times(1)).getAllFollowers(userId);
    }

    @Test
    void testGetAllFollowing() {
        Integer userId = 1;
        List<User> expectedFollowing = Arrays.asList(new User(), new User());

        when(userService.getAllFollowing(userId)).thenReturn(expectedFollowing);

        List<User> actualFollowing = userController.getAllFollowing(userId);

        assertEquals(expectedFollowing, actualFollowing);
        verify(userService, times(1)).getAllFollowing(userId);
    }

    @Test
    void testGetAllBlocked() {
        Integer userId = 1;
        List<User> expectedBlocked = Arrays.asList(new User(), new User());

        when(userService.getAllBlocked(userId)).thenReturn(expectedBlocked);

        List<User> actualBlocked = userController.getAllBlocked(userId);

        assertEquals(expectedBlocked, actualBlocked);
        verify(userService, times(1)).getAllBlocked(userId);
    }
    @Test
    void testGetParticipantData() {
        Integer userId = 1;
        Participant expectedParticipant = new Participant();

        when(userService.getParticipantData(userId)).thenReturn(ResponseEntity.ok(expectedParticipant));

        ResponseEntity<Participant> actualResponse = userController.getParticipantData(userId);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedParticipant, actualResponse.getBody());
        verify(userService, times(1)).getParticipantData(userId);
    }

    @Test
    void testPostAdminFeedback() {
        AdminFeedback feedback = new AdminFeedback();

        String expectedResponse = "Feedback received successfully";
        when(userService.postAdminFeedBack(feedback)).thenReturn(expectedResponse);

        String actualResponse = userController.postAdminFeedBack(feedback);

        assertEquals(expectedResponse, actualResponse);
        verify(userService, times(1)).postAdminFeedBack(feedback);
    }

    @Test
    void testAddUser() {
        User newUser = new User();

        ResponseEntity<String> expectedResponse = ResponseEntity.ok("User added successfully");
        when(userService.addUser(newUser)).thenReturn(expectedResponse);

        ResponseEntity<String> actualResponse = userController.addUser(newUser);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("User added successfully", actualResponse.getBody());
        verify(userService, times(1)).addUser(newUser);
    }
    @Test
    void testUpdateProfile() throws IOException {
        Integer userId = 1;
        MultipartFile profilePicture = mock(MultipartFile.class);

        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Profile updated successfully");
        when(userService.updateUserProfile(userId, profilePicture)).thenReturn(expectedResponse);

        ResponseEntity<String> actualResponse = userController.updateProfile(userId, profilePicture);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Profile updated successfully", actualResponse.getBody());
        verify(userService, times(1)).updateUserProfile(userId, profilePicture);
    }

    @Test
    void testUpdateEmail() {
        Integer userId = 1;
        String userEmail = "newEmail@example.com";

        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Email updated successfully");
        when(userService.updateUserEmail(userId, userEmail)).thenReturn(expectedResponse);

        ResponseEntity<String> actualResponse = userController.updateEmail(userId, userEmail);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Email updated successfully", actualResponse.getBody());
        verify(userService, times(1)).updateUserEmail(userId, userEmail);
    }

    @Test
    void testChangePassword() {
        Integer userId = 1;
        String userPassword = "newPassword";

        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Password changed successfully");
        when(userService.changePassword(userId, userPassword)).thenReturn(expectedResponse);

        ResponseEntity<String> actualResponse = userController.changePassword(userId, userPassword);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Password changed successfully", actualResponse.getBody());
        verify(userService, times(1)).changePassword(userId, userPassword);
    }

    @Test
    void testGetUserPosts() {
        Integer userId = 1;
        List<UserImageResponse> expectedPosts = Arrays.asList(new UserImageResponse(), new UserImageResponse());

        when(storageService.getUserPosts(userId)).thenReturn(expectedPosts);

        List<UserImageResponse> actualPosts = userController.getUserPosts(userId);

        assertEquals(expectedPosts, actualPosts);
        verify(storageService, times(1)).getUserPosts(userId);
    }

    @Test
    void testGetUserProfile() {
        Integer userId = 1;
        String expectedProfile = "base64encodedstring";

        when(storageService.getUserProfile(userId)).thenReturn(expectedProfile);

        String actualProfile = userController.getUserProfile(userId);

        assertEquals(expectedProfile, actualProfile);
        verify(storageService, times(1)).getUserProfile(userId);
    }

    @Test
    void testSendEmail() {
        String email = "test@example.com";
        String expectedResponse = "Email sent successfully";

        when(mailService.sendOTPService(email)).thenReturn(expectedResponse);

        String actualResponse = userController.sendEmail(email);

        assertEquals(expectedResponse, actualResponse);
        verify(mailService, times(1)).sendOTPService(email);
    }
    @Test
    void testForgotPassword() {
        String userEmail = "test@example.com";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Password reset email sent successfully");

        when(userService.forgotPassword(userEmail)).thenReturn(expectedResponse);

        ResponseEntity<String> actualResponse = userController.forgotPassword(userEmail);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Password reset email sent successfully", actualResponse.getBody());
        verify(userService, times(1)).forgotPassword(userEmail);
    }

    @Test
    void testDeletePost() {
        Integer userId = 1;
        String post = "base64encodedstring";
        String expectedResponse = "Post deleted successfully";

        when(storageService.deletePost(userId, Base64.getDecoder().decode(post))).thenReturn(expectedResponse);

        String actualResponse = userController.deletePost(userId, post);

        assertEquals(expectedResponse, actualResponse);
        verify(storageService, times(1)).deletePost(userId, Base64.getDecoder().decode(post));
    }

    @Test
    void testPostUserPosts() throws IOException {
        Integer userId = 1;
        String description = "Test post description";
        MultipartFile post = mock(MultipartFile.class);
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Post uploaded successfully");

        when(userService.uploadPost(userId, description, post)).thenReturn(expectedResponse);

        ResponseEntity<String> actualResponse = userController.postUserPosts(userId, description, post);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Post uploaded successfully", actualResponse.getBody());
        verify(userService, times(1)).uploadPost(userId, description, post);
    }

    @Test
    void testGetUserByEmail() {
        String userEmail = "test@example.com";
        User expectedUser = new User();

        when(userService.getByUserEmail(userEmail)).thenReturn(expectedUser);

        ResponseEntity<User> actualUser = userController.getUserByEmail(userEmail);

        assertEquals(expectedUser, actualUser.getBody());
        assertEquals(HttpStatus.OK, actualUser.getStatusCode());
        verify(userService, times(1)).getByUserEmail(userEmail);
    }

    @Test
    void testGetAllUserByUserName() {
        String userName = "testUser";
        List<User> expectedUsers = Arrays.asList(new User(), new User());

        when(userService.getAllByUserName(userName)).thenReturn(expectedUsers);

        List<User> actualUsers = userController.getAllUserByUserName(userName);

        assertEquals(expectedUsers, actualUsers);
        verify(userService, times(1)).getAllByUserName(userName);
    }

//    @Test
//    void testSignInUser() {
//        LoginRequest userLoginRequest = mock(LoginRequest.class);
//        ResponseEntity<?> expectedResponse = ResponseEntity.ok("User signed in successfully");
//
//        when(userService.signinUser(userLoginRequest)).thenReturn((ResponseEntity<?>) expectedResponse);
//
//        ResponseEntity<?> actualResponse = userController.signInUser(userLoginRequest);
//
//        assertEquals(expectedResponse, actualResponse);
//        verify(userService, times(1)).signinUser(userLoginRequest);
//    }
}