package com.bugtrack.bugtrack_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.bugtrack.bugtrack_backend.dto.AssignBugRequest;
import com.bugtrack.bugtrack_backend.dto.UpdateBugRequest;
import com.bugtrack.bugtrack_backend.entity.Bug;
import com.bugtrack.bugtrack_backend.entity.User;
import com.bugtrack.bugtrack_backend.repository.UserRepository;
import com.bugtrack.bugtrack_backend.service.BugService;
import com.bugtrack.bugtrack_backend.dto.AssignBugRequest;
import java.util.List;


@RestController
@RequestMapping("/api/bugs")
@CrossOrigin(origins = "http://localhost:5173")
public class BugController {

    private final BugService bugService;
    private final UserRepository userRepository;

    public BugController(
            BugService bugService,
            UserRepository userRepository) {

        this.bugService = bugService;
        this.userRepository = userRepository;
    }


    @PostMapping
    public ResponseEntity<Bug> createBug(
            @RequestBody Bug bug,
            Authentication authentication) {


        String email = authentication.getName();


        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> 
                    new RuntimeException("User not found"));


        Bug savedBug = bugService.createBug(bug, user);


        return ResponseEntity.ok(savedBug);
    }

    @PutMapping("/{id}")
public ResponseEntity<Bug> updateBug(
        @PathVariable Long id,
        @RequestBody UpdateBugRequest request
){

    Bug updatedBug = bugService.updateBug(id, request);

    return ResponseEntity.ok(updatedBug);
}

@PutMapping("/{id}/assign")
public ResponseEntity<Bug> assignBug(
        @PathVariable Long id,
        @RequestBody AssignBugRequest request) {

    Bug assignedBug = bugService.assignBug(
            id,
            request.getUserId()
    );

    return ResponseEntity.ok(assignedBug);
}
@GetMapping
public ResponseEntity<List<Bug>> getAllBugs(){

    List<Bug> bugs = bugService.getAllBugs();

    return ResponseEntity.ok(bugs);
}

@GetMapping("/{id}")
public ResponseEntity<Bug> getBugById(@PathVariable Long id) {

    Bug bug = bugService.getBugById(id);

    return ResponseEntity.ok(bug);
}

@GetMapping("/my")
public ResponseEntity<List<Bug>> getMyBugs(Authentication authentication) {

    String email = authentication.getName();

    List<Bug> bugs = bugService.getMyBugs(email);

    return ResponseEntity.ok(bugs);
}

@DeleteMapping("/{id}")
public ResponseEntity<String> deleteBug(@PathVariable Long id){

    bugService.deleteBug(id);

    return ResponseEntity.ok("Bug deleted successfully");
}

}
