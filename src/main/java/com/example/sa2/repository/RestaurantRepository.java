package com.example.sa2.repository;

import com.example.sa2.model.Admin;
import com.example.sa2.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    Restaurant findByName(String name);
    Restaurant findByAdmin(Admin admin);
    List<Restaurant> findAll();
    void deleteByName(String name);
}
