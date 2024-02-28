package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SchedulingImplTest {

    @Mock
    private GroupRepository grpRepo;

    @Mock
    private TouristSpotRepository spotRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private OrganizerRepository organizerRepository;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private SchedulingImpl scheduling;

    @Test
    void checkGroupStatus() {
        // Your test logic here
        // Mock the necessary dependencies and use when().thenReturn() to simulate the behavior
        // of repository methods

        LocalDate currentDate = LocalDate.now();

        List<Group> mockActiveGroups = new ArrayList<>();
        // Add mock active groups to the list

        when(grpRepo.findAllByGroupStatus(GroupStatus.Active)).thenReturn(mockActiveGroups);

        // Mock other repository method calls and expected behavior as needed

        // Set the active groups using ReflectionTestUtils
        ReflectionTestUtils.setField(scheduling, "allActiveGroup", mockActiveGroups);

        // Call the method to test
        scheduling.checkGroupStatus();

    }
    @Test
    void getActiveOrganizerId() {
        // Your test logic here
        List<Integer> expectedActiveOrganizers = new ArrayList<>();
        // Add expected active organizers to the list

        ReflectionTestUtils.setField(scheduling, "activeOrganizers", expectedActiveOrganizers);

        List<Integer> actualActiveOrganizers = scheduling.getActiveOrganizerId();

        assertEquals(expectedActiveOrganizers, actualActiveOrganizers);
    }

    @Test
    void getActiveParticipantId() {
        // Your test logic here
        List<Integer> expectedActiveParticipants = new ArrayList<>();
        // Add expected active participants to the list

        ReflectionTestUtils.setField(scheduling, "activeParticipants", expectedActiveParticipants);

        List<Integer> actualActiveParticipants = scheduling.getActiveParticipantId();

        assertEquals(expectedActiveParticipants, actualActiveParticipants);
    }
    @Test
    void getActiveOrganizers() {
        // Your test logic here
        List<Integer> expectedActiveOrganizers = new ArrayList<>();
        // Add expected active organizers to the list

        ReflectionTestUtils.setField(scheduling, "activeOrganizers", expectedActiveOrganizers);

        List<Integer> actualActiveOrganizers = scheduling.getActiveOrganizers();

        assertEquals(expectedActiveOrganizers, actualActiveOrganizers);
    }

    @Test
    void getActiveParticipants() {
        // Your test logic here
        List<Integer> expectedActiveParticipants = new ArrayList<>();
        // Add expected active participants to the list

        ReflectionTestUtils.setField(scheduling, "activeParticipants", expectedActiveParticipants);

        List<Integer> actualActiveParticipants = scheduling.getActiveParticipants();

        assertEquals(expectedActiveParticipants, actualActiveParticipants);
    }

    @Test
    void getActiveGrpId() {
        // Your test logic here
        List<Integer> expectedActiveGrpId = new ArrayList<>();
        // Add expected active group IDs to the list

        ReflectionTestUtils.setField(scheduling, "activeGrpId", expectedActiveGrpId);

        List<Integer> actualActiveGrpId = scheduling.getActiveGrpId();

        assertEquals(expectedActiveGrpId, actualActiveGrpId);
    }
}
