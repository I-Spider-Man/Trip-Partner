package com.example.demo.Controller;
import com.example.demo.Model.User;
import com.example.demo.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            return authService.loginUser(user.getUserEmail(), user.getUserPassword());
        } catch (Exception e) {
            // Handle exceptions, e.g., database errors
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}
