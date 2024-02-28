package com.example.demo.Service.GroupServices;

import com.example.demo.Model.Group;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GroupServiceTest {

    @MockBean
    private final GroupService groupService = mock(GroupService.class);

    @Test
    void testGetAllGroups() {
        // Your implementation here
    }

    @Test
    void testAddGroup() {
        Group newGroup = new Group(); // Create a sample group
        String expectedResponse = "Group added successfully";

        when(groupService.addGroup(newGroup)).thenReturn(expectedResponse);

        String actualResponse = groupService.addGroup(newGroup);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testRemoveGroupById() {
        Integer groupId = 1; // Replace with a valid groupId
        String expectedResponse = "Group removed successfully";

        when(groupService.removeGroupById(groupId)).thenReturn(expectedResponse);

        String actualResponse = groupService.removeGroupById(groupId);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetGroupById() {
        // Your implementation here
    }

    @Test
    void testGetActiveGroupById() {
        // Your implementation here
    }

    @Test
    void testGetGroupByOrganizerId() {
        // Your implementation here
    }

    @Test
    void testGetAllGroupBySpotName() {
        // Your implementation here
    }

    @Test
    void testGetAllGroupByEventName() {
        // Your implementation here
    }
}
