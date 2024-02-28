package com.example.demo.Controller;

import com.example.demo.Controller.GroupController;
import com.example.demo.Model.Group;
import com.example.demo.Model.GroupMessage;
import com.example.demo.Service.GroupMessage.GroupMessageService;
import com.example.demo.Service.GroupServices.GroupService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GroupControllerTest {

    @Mock
    private GroupService groupService;

    @Mock
    private GroupMessageService groupMessageService;

    @InjectMocks
    private GroupController groupController;

    public GroupControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllGroup() {
        List<Group> expectedGroups = Arrays.asList(new Group(), new Group());

        when(groupService.getAllGroups()).thenReturn(expectedGroups);

        List<Group> actualGroups = groupController.getAllGroup();

        assertEquals(expectedGroups, actualGroups);
        verify(groupService, times(1)).getAllGroups();
    }

    @Test
    void testAddGroup() {
        Group newGroup = new Group();
        String expectedResponse = "Success";

        when(groupService.addGroup(newGroup)).thenReturn(expectedResponse);

        String actualResponse = groupController.addGroup(newGroup);

        assertEquals(expectedResponse, actualResponse);
        verify(groupService, times(1)).addGroup(newGroup);
    }

    @Test
    void testGetAllMessagesByGroupId() {
        Integer groupId = 1;
        List<GroupMessage.Message> expectedMessages = Arrays.asList(new GroupMessage.Message(groupId, null), new GroupMessage.Message(groupId, null));

        when(groupMessageService.getAllMessageByGroupId(groupId)).thenReturn(expectedMessages);

        ResponseEntity<List<GroupMessage.Message>> actualResponse = groupController.getAllMessagesByGroupId(groupId);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedMessages, actualResponse.getBody());
        verify(groupMessageService, times(1)).getAllMessageByGroupId(groupId);
    }

    @Test
    void testSaveMessageToGroup() {
        Integer groupId = 1;
        GroupMessage.Message message = new GroupMessage.Message(groupId, null);

        groupController.saveMessageToGroup(groupId, message);

        verify(groupMessageService, times(1)).saveMessageToGroupId(groupId, message);
    }

    @Test
    void testGetGroupById() {
        Integer groupId = 1;
        Group expectedGroup = new Group();

        when(groupService.getActiveGroupById(groupId)).thenReturn(expectedGroup);

        ResponseEntity<Group> actualResponse = groupController.getGroupById(groupId);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedGroup, actualResponse.getBody());
        verify(groupService, times(1)).getActiveGroupById(groupId);
    }

    
}
