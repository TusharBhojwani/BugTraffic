package com.bugtrack.bugtrack_backend.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bugtrack.bugtrack_backend.entity.User;
import com.bugtrack.bugtrack_backend.service.UserService;
import com.bugtrack.bugtrack_backend.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")

public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }
    
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {

    String token = userService.login(request);

    return ResponseEntity.ok(token);
}
}
