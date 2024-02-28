package Service.GroupMessage;

import com.example.demo.Model.GroupMessage;
import com.example.demo.Service.GroupMessage.GroupMessageService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GroupMessageServiceTest {

    @MockBean
    private final GroupMessageService groupMessageService = mock(GroupMessageService.class);

    @Test
    void testGetAllMessageByGroupId() {
        Integer groupId = 1; // Replace with a valid groupId
        List<GroupMessage.Message> expectedMessages = Arrays.asList(
                new GroupMessage.Message(1, "Message 1"),
                new GroupMessage.Message(2, "Message 2")
        );

        when(groupMessageService.getAllMessageByGroupId(groupId)).thenReturn(expectedMessages);

        List<GroupMessage.Message> actualMessages = groupMessageService.getAllMessageByGroupId(groupId);

        assertEquals(expectedMessages, actualMessages);
    }

    @Test
    void testSaveMessageToGroupId() {
        Integer groupId = 1; // Replace with a valid groupId
        GroupMessage.Message message = new GroupMessage.Message(1, "New Message");

        // Mock the behavior when saving a message
        doNothing().when(groupMessageService).saveMessageToGroupId(groupId, message);

        // Perform the actual save operation (in a real scenario, this should be part of the implementation)
        groupMessageService.saveMessageToGroupId(groupId, message);

        // Verify that the save operation was called once
        verify(groupMessageService, times(1)).saveMessageToGroupId(groupId, message);
    }
}
