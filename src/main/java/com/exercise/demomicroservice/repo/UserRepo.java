package com.exercise.demomicroservice.repo;

import com.exercise.demomicroservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    // custom
    Optional<User> findById(String id);
    Optional<User> findByUsername(String username);
}
