package com.example.sa2.repository;

import com.example.sa2.model.Client;
import com.example.sa2.model.Food;
import com.example.sa2.model.Order;
import com.example.sa2.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRespository extends JpaRepository<Order,Integer> {
    List<Order> findByRestaurantOrderByStatus(Restaurant restaurant);
    List<Order> findByClient(Client client);
    List<Order> findByStatus(String status);
    Order findById(int id);
}
