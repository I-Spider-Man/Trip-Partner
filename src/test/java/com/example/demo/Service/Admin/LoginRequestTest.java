package com.example.demo.Service.Admin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void testGetEmail() {
        LoginRequest loginRequest = new LoginRequest("test@example.com", "testPassword");
        assertEquals("test@example.com", loginRequest.getEmail());
    }

    @Test
    void testSetEmail() {
        LoginRequest loginRequest = new LoginRequest("initial@example.com", "testPassword");
        loginRequest.setEmail("updated@example.com");
        assertEquals("updated@example.com", loginRequest.getEmail());
    }

    @Test
    void testGetPassword() {
        LoginRequest loginRequest = new LoginRequest("test@example.com", "testPassword");
        assertEquals("testPassword", loginRequest.getPassword());
    }

    @Test
    void testSetPassword() {
        LoginRequest loginRequest = new LoginRequest("test@example.com", "initialPassword");
        loginRequest.setPassword("updatedPassword");
        assertEquals("updatedPassword", loginRequest.getPassword());
    }

    @Test
    void testConstructor() {
        LoginRequest loginRequest = new LoginRequest("test@example.com", "testPassword");
        assertEquals("test@example.com", loginRequest.getEmail());
        assertEquals("testPassword", loginRequest.getPassword());
    }
}

