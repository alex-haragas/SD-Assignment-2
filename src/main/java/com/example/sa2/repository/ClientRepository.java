package com.example.sa2.repository;

import com.example.sa2.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClientRepository extends JpaRepository<Client,Integer> {
    Client findByUsername(String name);
}
