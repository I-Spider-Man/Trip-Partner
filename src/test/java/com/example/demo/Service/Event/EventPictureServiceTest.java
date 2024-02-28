package com.example.demo.Service.Event;

import com.example.demo.Service.Event.EventPictureService;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EventPictureServiceTest {

    private EventPictureService eventPictureService = mock(EventPictureService.class);

    @Test
    void addEventPicture() throws IOException {
        // Your test logic here
        // Example:
        // Integer eventId = 1;
        // MultipartFile file = new MockMultipartFile("image", "test.jpg", "image/jpeg", "image data".getBytes());
        // doNothing().when(eventPictureService).addEventPicture(eventId, file);
        // eventPictureService.addEventPicture(eventId, file);
        // verify(eventPictureService, times(1)).addEventPicture(eventId, file);
    }

    @Test
    void getAllPicturesByEventId() {
        // Your test logic here
        // Example:
        // Integer eventId = 1;
        // List<String> expectedPictures = Arrays.asList("picture1.jpg", "picture2.png");
        // when(eventPictureService.getAllPicturesByEventId(eventId)).thenReturn(expectedPictures);
        // List<String> actualPictures = eventPictureService.getAllPicturesByEventId(eventId);
        // assertEquals(expectedPictures, actualPictures);
    }
}
