package com.example.demo.Service;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> loginUser(String userEmail, String userPassword) {
        Optional<User> user = userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);

        if (user.isPresent()) {
            System.out.println("User Role: " + user.get().getRole()); // Add this line for debugging
            if (user.get().getRole() == Role.ROLE_ADMIN) {
                return ResponseEntity.ok(user.get().getUserId());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not have the required role");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
