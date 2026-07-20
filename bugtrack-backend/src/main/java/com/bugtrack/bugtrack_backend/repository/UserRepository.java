package com.bugtrack.bugtrack_backend.repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugtrack.bugtrack_backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}

