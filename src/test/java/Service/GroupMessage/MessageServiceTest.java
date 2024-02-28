package Service.GroupMessage;

import com.example.demo.Model.GroupMessage;
import com.example.demo.Repository.MessagesRepository;
import com.example.demo.Service.GroupMessage.MessageService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessagesRepository messagesRepository;

    @InjectMocks
    private MessageService messageService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    void testGetAllMessageByGroupId() {
        // Arrange
        Integer groupId = 1;
        List<GroupMessage.Message> expectedMessages = Arrays.asList(
                new GroupMessage.Message(1, "User1"),
                new GroupMessage.Message(2, "User2")
        );

        // Mocking the behavior of the messagesRepository
        when(messagesRepository.findByGroupId(groupId)).thenReturn(Optional.of(new GroupMessage(groupId, expectedMessages)));

        // Act
        List<GroupMessage.Message> result = messageService.getAllMessageByGroupId(groupId);

        // Assert
        assertEquals(expectedMessages, result);
        verify(messagesRepository, times(1)).findByGroupId(groupId);
    }

//    @Test
//    void testSaveMessageToGroupId() {
//        // Arrange
//        Integer groupId = 1;
//        GroupMessage.Message newMessage = new GroupMessage.Message(3, "User3");
//
//        // Mocking the behavior of the messagesRepository
//        when(messagesRepository.findByGroupId(groupId)).thenReturn(Optional.of(new GroupMessage(groupId, Arrays.asList(
//                new GroupMessage.Message(1, "User1"),
//                new GroupMessage.Message(2, "User2")
//        ))));
//
//        // Act
//        messageService.saveMessageToGroupId(groupId, newMessage);
//
//        // Assert
//        verify(messagesRepository, times(1)).findByGroupId(groupId);
//        verify(messagesRepository, times(1)).save(any(GroupMessage.class));
//    }

}
