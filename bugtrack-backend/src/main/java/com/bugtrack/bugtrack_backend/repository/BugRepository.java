package com.bugtrack.bugtrack_backend.repository;
import com.bugtrack.bugtrack_backend.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bugtrack.bugtrack_backend.entity.Bug;
import java.util.List;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {
    List<Bug> findByAssignedTo(User user);
    

}

