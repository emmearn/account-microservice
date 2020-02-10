package com.accountmicroservice.repo;

import com.accountmicroservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    // custom
    Optional<User> findById(Integer id);
    Optional<User> findByUsername(String username);
}
