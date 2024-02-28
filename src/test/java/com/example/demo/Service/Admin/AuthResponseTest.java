package com.example.demo.Service.Admin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthResponseTest {

    @Test
    void testGetToken() {
        AuthResponse authResponse = new AuthResponse("testToken", "testMessage");
        assertEquals("testToken", authResponse.getToken());
    }

    @Test
    void testSetToken() {
        AuthResponse authResponse = new AuthResponse("initialToken", "testMessage");
        authResponse.setToken("updatedToken");
        assertEquals("updatedToken", authResponse.getToken());
    }

    @Test
    void testGetMessage() {
        AuthResponse authResponse = new AuthResponse("testToken", "testMessage");
        assertEquals("testMessage", authResponse.getMessage());
    }

    @Test
    void testSetMessage() {
        AuthResponse authResponse = new AuthResponse("testToken", "initialMessage");
        authResponse.setMessage("updatedMessage");
        assertEquals("updatedMessage", authResponse.getMessage());
    }

    @Test
    void testConstructor() {
        AuthResponse authResponse = new AuthResponse("constructorToken", "constructorMessage");
        assertEquals("constructorToken", authResponse.getToken());
        assertEquals("constructorMessage", authResponse.getMessage());
    }
}
