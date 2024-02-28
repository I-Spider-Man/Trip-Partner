package com.example.demo.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue; // Import assertTrue from Assertions

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

import jakarta.transaction.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

//@Rollback(false) // Disable automatic rollback
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        // Common setup for each test
        user = new User();
        user.setUserName("testUser");
        user.setUserEmail("test@example.com");
        user.setAboutUser("About test user");
        user.setGender("Male");
        user.setDateOfBirth(LocalDate.of(1990, 1, 1));
        user.setRole(Role.User_Role);
        user.setUserPassword("password123");
        user.setUserProfile("http://example.com/profile");
        user.setUserExtraDetails("Extra details for test user");
        userRepository.save(user);
    }

    @AfterEach
    public void tearDown() {
      
        
    }

    @Test
    public void testFindByUserName() {
        // Act
        List<User> foundUsers = userRepository.findAllByUserName("testUser");

        // Assert
        assertNotNull(foundUsers);
        assertEquals(1, foundUsers.size());
        assertEquals("testUser", foundUsers.get(0).getUserName());
    }

    @Test
    public void testFindByUserEmail() {
        // Act
        Optional<User> foundUserOptional = userRepository.findByUserEmail("test@example.com");

        // Assert
        assertNotNull(foundUserOptional);
        assertTrue(foundUserOptional.isPresent()); // Use assertTrue from Assertions
        assertEquals("test@example.com", foundUserOptional.get().getUserEmail());
    }

    @Test
    public void testFindByRole() {
        // Act
        List<User> foundUsers = userRepository.findAllByRole(Role.User_Role);

        // Assert
        assertNotNull(foundUsers);
        assertEquals(1, foundUsers.size());
        assertEquals(Role.User_Role.toString(), foundUsers.get(0).getRole());
    }

    // Add more test methods as needed
}
