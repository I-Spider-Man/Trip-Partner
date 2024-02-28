package com.example.demo.Service;

import com.example.demo.Model.EventPicture;
import com.example.demo.Model.EventPicture.EventPictures;
import com.example.demo.Model.SpotPicture;
import com.example.demo.Model.UserImageResponse;
import com.example.demo.Model.UserImages;
import com.example.demo.Repository.EventImageRepository;
import com.example.demo.Repository.SpotImageRepository;
import com.example.demo.Repository.UserImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StorageServiceTest {

    @Mock
    private EventImageRepository eventImageRepository;

    @Mock
    private SpotImageRepository spotImageRepository;

    @Mock
    private UserImageRepository userImageRepository;

    @InjectMocks
    private StorageService storageService;

//    @Test
//    void testDeletePost() {
//        // Mock data
//        UserImages userImages = new UserImages();
//        userImages.setUserId(1);
//
//        UserImages.Posts post = new UserImages.Posts();
//        post.setDescription("Test description");
//        post.setPost("Test post content".getBytes());
//
//        userImages.setPostsList(Collections.singletonList(post));
//
//        when(userImageRepository.findByUserId(anyInt())).thenReturn(Optional.of(userImages));
//        when(userImageRepository.save(any(UserImages.class))).thenReturn(userImages);
//
//        // Call the method to be tested
//        String result = storageService.deletePost(1, "Test post content".getBytes());
//
//        // Verify interactions or assertions based on your specific logic
//        assertEquals("post deleted", result);
//        verify(userImageRepository, times(1)).findByUserId(anyInt());
//        verify(userImageRepository, times(1)).save(any(UserImages.class));
//    }

    @Test
    void testDeletePostWhenUserImagesNotFound() {
        // Mock data
        when(userImageRepository.findByUserId(anyInt())).thenReturn(Optional.empty());

        // Call the method to be tested
        String result = storageService.deletePost(1, "Test post content".getBytes());

        // Verify interactions or assertions based on your specific logic
        assertEquals("UserImages not found or postsList is null", result);
        verify(userImageRepository, times(1)).findByUserId(anyInt());
        verify(userImageRepository, never()).save(any(UserImages.class));
    }
    @Test
    void testAddUserPosts1() throws IOException {
        // Mock data
        Optional<UserImages> userImages = Optional.of(new UserImages());
        MultipartFile mockPost = mock(MultipartFile.class);
        when(mockPost.getBytes()).thenReturn("Test post content".getBytes());

        when(userImageRepository.findByUserId(anyInt())).thenReturn(userImages);
        when(userImageRepository.save(any(UserImages.class))).thenReturn(userImages.get());

        // Call the method to be tested
        ResponseEntity<String> result = storageService.addUserPosts(1, "Test description", mockPost);

        // Verify interactions or assertions based on your specific logic
        assertEquals(new ResponseEntity<>("post uploaded successfully", HttpStatus.OK), result);
        verify(userImageRepository, times(1)).findByUserId(anyInt());
        verify(userImageRepository, times(1)).save(any(UserImages.class));
    }

    @Test
    void testAddUserPostsWhenUserImagesNotFound() throws IOException {
        // Mock data
        Optional<UserImages> userImages = Optional.empty();
        MultipartFile mockPost = mock(MultipartFile.class);

        // Call the method to be tested
        ResponseEntity<String> result = storageService.addUserPosts(1, "Test description", mockPost);

        // Verify interactions or assertions based on your specific logic
        assertEquals(new ResponseEntity<>("Problem on uploading. try again...", HttpStatus.CONFLICT), result);
        verify(userImageRepository, times(1)).findByUserId(anyInt());
        verify(userImageRepository, never()).save(any(UserImages.class));
    }

    @Test
    void testAddEventImage1() throws IOException {
        // Mock data
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getBytes()).thenReturn("Test image content".getBytes());

        Optional<EventPicture> eventPicture = Optional.of(new EventPicture());
        when(eventImageRepository.findByEventId(anyInt())).thenReturn(eventPicture);
        when(eventImageRepository.save(any(EventPicture.class))).thenReturn(eventPicture.get());

        // Call the method to be tested
        String result = storageService.addEventImage(1, mockFile);

        // Verify interactions or assertions based on your specific logic
        assertNull(result);
        verify(eventImageRepository, times(1)).findByEventId(anyInt());
        verify(eventImageRepository, times(1)).save(any(EventPicture.class));
    }

    @Test
    void testAddEventImageWhenEventPictureNotFound() throws IOException {
        // Mock data
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getBytes()).thenReturn("Test image content".getBytes());

        Optional<EventPicture> eventPicture = Optional.empty();

        // Call the method to be tested
        String result = storageService.addEventImage(1, mockFile);

        // Verify interactions or assertions based on your specific logic
        assertNull(result);
        verify(eventImageRepository, times(1)).findByEventId(anyInt());
        verify(eventImageRepository, times(1)).save(any(EventPicture.class));
    }
    @Test
    void testAddUserPosts() throws IOException {
        // Mock data
        MultipartFile postFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", "Test post content".getBytes());
        UserImages userImages = new UserImages();
        userImages.setUserId(1);

        when(userImageRepository.findByUserId(anyInt())).thenReturn(Optional.of(userImages));
        when(userImageRepository.save(any(UserImages.class))).thenReturn(userImages);

        // Call the method to be tested
        ResponseEntity<String> result = storageService.addUserPosts(1, "Test description", postFile);

        // Verify interactions or assertions based on your specific logic
        assertEquals("post uploaded successfully", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userImageRepository, times(1)).findByUserId(anyInt());
        verify(userImageRepository, times(1)).save(any(UserImages.class));
    }

    @Test
    void testAddEventImage() throws IOException {
        // Mock data
        MultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "Test image content".getBytes());
        byte[] eventImage = file.getBytes();

        Optional<EventPicture> existingEventPicture = Optional.of(new EventPicture());
        when(eventImageRepository.findByEventId(anyInt())).thenReturn(existingEventPicture);
        when(eventImageRepository.save(any(EventPicture.class))).thenReturn(new EventPicture());

        // Call the method to be tested
        String result = storageService.addEventImage(1, file);

        // Verify interactions or assertions based on your specific logic
        assertNull(result);
        verify(eventImageRepository, times(1)).findByEventId(anyInt());
        verify(eventImageRepository, times(1)).save(any(EventPicture.class));
    }

//    @Test
//    void testAddSpotImage() throws IOException {
//        // Mock data
//        MultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "Test image content".getBytes());
//        byte[] spotImage = file.getBytes();
//
//        Optional<SpotPicture> existingSpotPicture = Optional.of(new SpotPicture());
//        when(spotImageRepository.findBySpotId(anyInt())).thenReturn(existingSpotPicture);
//        when(spotImageRepository.save(any(SpotPicture.class))).thenReturn(new SpotPicture());
//
//        // Call the method to be tested
//        String result = storageService.addSpotImage(1, file);
//
//        // Verify interactions or assertions based on your specific logic
//        assertNull(result);
//        verify(spotImageRepository, times(1)).findBySpotId(anyInt());
//        verify(spotImageRepository, times(1)).save(any(SpotPicture.class));
//    }

    @Test
    void testAddUserProfile() throws IOException {
        // Mock data
        MultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "Test image content".getBytes());
        byte[] userProfile = file.getBytes();

        UserImages userImages = new UserImages();
        userImages.setUserId(1);

        when(userImageRepository.findByUserId(anyInt())).thenReturn(Optional.of(userImages));
        when(userImageRepository.save(any(UserImages.class))).thenReturn(userImages);

        // Call the method to be tested
        storageService.addUserProfile(1, file);

        // Verify interactions or assertions based on your specific logic
        verify(userImageRepository, times(1)).findByUserId(anyInt());
        verify(userImageRepository, times(1)).save(any(UserImages.class));
    }

    @Test
    void testGetEventImage() {
        // Mock data
        byte[] testImage = "Test image content".getBytes();
        EventPicture eventPicture = new EventPicture();
        eventPicture.setEventPictures(new EventPicture.EventPictures(testImage));

        when(eventImageRepository.findByEventId(anyInt())).thenReturn(Optional.of(eventPicture));

        // Call the method to be tested
        List<String> result = storageService.getEventImage(1);

        // Verify interactions or assertions based on your specific logic
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetSpotImage() {
        // Mock data
        byte[] testImage = "Test image content".getBytes();
        SpotPicture spotPicture = new SpotPicture();
        spotPicture.setSpotPicturesList(Collections.singletonList(new SpotPicture.SpotPictures(testImage)));

        when(spotImageRepository.findBySpotId(anyInt())).thenReturn(Optional.of(spotPicture));

        // Call the method to be tested
        List<String> result = storageService.getSpotImage(1);

        // Verify interactions or assertions based on your specific logic
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetUserProfile() {
        // Mock data
        byte[] testProfile = "Test profile content".getBytes();
        UserImages userImages = new UserImages();
        userImages.setProfile(testProfile);

        when(userImageRepository.findByUserId(anyInt())).thenReturn(Optional.of(userImages));

        // Call the method to be tested
        String result = storageService.getUserProfile(1);

        // Verify interactions or assertions based on your specific logic
        assertNotNull(result);
    }
    @Test
    void testGetUserPosts() {
        // Mock data
        UserImages userImages = new UserImages();
        userImages.setUserId(1);
        UserImages.Posts post1 = new UserImages.Posts();
        post1.setDescription("Description1");
        post1.setPost("Test content 1".getBytes());
        userImages.getPostsList().add(post1);
        UserImages.Posts post2 = new UserImages.Posts();
        post2.setDescription("Description2");
        post2.setPost("Test content 2".getBytes());
        userImages.getPostsList().add(post2);

        when(userImageRepository.findByUserId(anyInt())).thenReturn(Optional.of(userImages));

        // Call the method to be tested
        List<UserImageResponse> result = storageService.getUserPosts(1);

        // Verify interactions or assertions based on your specific logic
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testDeleteSpotImage() {
        // Mock data
        SpotPicture spotPicture = new SpotPicture();
        when(spotImageRepository.findBySpotId(anyInt())).thenReturn(Optional.of(spotPicture));

        // Call the method to be tested
        storageService.deleteSpotImage(1);

        // Verify interactions or assertions based on your specific logic
        verify(spotImageRepository, times(1)).findBySpotId(anyInt());
        verify(spotImageRepository, times(1)).delete(any(SpotPicture.class));
    }

    @Test
    void testDeleteEventImage() {
        // Mock data
        EventPicture eventPicture = new EventPicture();
        when(eventImageRepository.findByEventId(anyInt())).thenReturn(Optional.of(eventPicture));

        // Call the method to be tested
        storageService.deleteEventImage(1);

        // Verify interactions or assertions based on your specific logic
        verify(eventImageRepository, times(1)).findByEventId(anyInt());
        verify(eventImageRepository, times(1)).delete(any(EventPicture.class));
    }

    // Add more test methods for other functionalities as needed
}
