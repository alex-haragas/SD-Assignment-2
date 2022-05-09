package com.example.sa2.repository;

import com.example.sa2.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String name);
}
