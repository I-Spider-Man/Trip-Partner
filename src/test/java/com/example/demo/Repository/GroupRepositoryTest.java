package com.example.demo.Repository;

import com.example.demo.Model.Group;
import com.example.demo.Model.GroupStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    private Group savedGroup;

    @BeforeEach
    public void setUp() {
        // Create and save a group before each test
        Group group = new Group();
        group.setOrganizerId(1); // Set organizer ID
        group.setSpotName("TestSpot");
        group.setEventName("TestEvent");
        group.setGroupStatus(GroupStatus.Active); // Set group status
        savedGroup = groupRepository.save(group);
    }

    @AfterEach
    public void tearDown() {
        // Cleanup after each test
        groupRepository.deleteAll();
    }

    @Test
    public void testFindByOrganizerId() {
        // Act
        Optional<Group> foundGroupOptional = groupRepository.findByOrganizerId(1);

        // Assert
        assertNotNull(foundGroupOptional);
        assertTrue(foundGroupOptional.isPresent());
        assertEquals(savedGroup.getOrganizerId(), foundGroupOptional.get().getOrganizerId());
    }

    @Test
    public void testFindAllBySpotName() {
        // Act
        List<Group> foundGroups = groupRepository.findAllBySpotName("TestSpot");

        // Assert
        assertNotNull(foundGroups);
        assertEquals(1, foundGroups.size());
        assertEquals("TestSpot", foundGroups.get(0).getSpotName());
    }

    @Test
    public void testFindAllByEventName() {
        // Act
        List<Group> foundGroups = groupRepository.findAllByEventName("TestEvent");

        // Assert
        assertNotNull(foundGroups);
        assertEquals(1, foundGroups.size());
        assertEquals("TestEvent", foundGroups.get(0).getEventName());
    }

    @Test
    public void testFindAllByGroupStatus() {
        // Act
        List<Group> foundGroups = groupRepository.findAllByGroupStatus(GroupStatus.Active);

        // Assert
        assertNotNull(foundGroups);
        assertEquals(1, foundGroups.size());
        assertEquals(GroupStatus.Active, foundGroups.get(0).getGroupStatus());
    }
}
