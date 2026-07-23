package com.bugtrack.bugtrack_backend.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.bugtrack.bugtrack_backend.dto.UpdateBugRequest;
import com.bugtrack.bugtrack_backend.entity.Bug;
import com.bugtrack.bugtrack_backend.entity.User;
import com.bugtrack.bugtrack_backend.repository.BugRepository;
import com.bugtrack.bugtrack_backend.repository.UserRepository;

@Service

public class BugService {
    private final BugRepository bugRepository;
    private final UserRepository userRepository;

    public BugService(BugRepository bugRepository,UserRepository userRepository){
        this.bugRepository = bugRepository;
        this.userRepository = userRepository;
    }

    public List<Bug> getAllBugs(){

        return bugRepository.findAll();
    
    }
    public Bug createBug(Bug bug,User user){
        bug.setCreatedBy(user);
        return bugRepository.save(bug);
    }
    public Bug updateBug(Long id, UpdateBugRequest request){

    Bug bug = bugRepository.findById(id)
            .orElseThrow(() -> 
                new RuntimeException("Bug not found")
            );

    bug.setTitle(request.getTitle());
    bug.setDescription(request.getDescription());
    bug.setStatus(request.getStatus());
    bug.setPriority(request.getPriority());

    return bugRepository.save(bug);
}

public Bug assignBug(Long bugId, Long userId){

    Bug bug = bugRepository.findById(bugId)
            .orElseThrow(() -> 
                new RuntimeException("Bug not found")
            );

    User user = userRepository.findById(userId)
            .orElseThrow(() ->
                new RuntimeException("User not found")
            );

    bug.setAssignedTo(user);

    return bugRepository.save(bug);
}
public Bug getBugById(Long id) {

    return bugRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Bug not found")
            );
}

public List<Bug> getMyBugs(String email) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new RuntimeException("User not found")
            );

    return bugRepository.findByAssignedTo(user);
}

public void deleteBug(Long id){

    Bug bug = bugRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Bug not found")
            );

    bugRepository.delete(bug);
}

}
