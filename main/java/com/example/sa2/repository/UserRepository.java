package com.example.sa2.repository;

import com.example.sa2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String name);
}
