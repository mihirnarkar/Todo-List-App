package com.example.User_Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.User_Service.model.User;
import com.example.User_Service.repository.UserRepository;
import com.example.User_Service.util.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginRequest) {
        // Extract email and password from request body
        String email = loginRequest.get("username");
        String password = loginRequest.get("password");

        // Fetch user by email
        User user = userRepository.findByEmail(email);

        // Validate email and password
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(token);
    }
}
