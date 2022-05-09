package com.example.sa2.repository;

import com.example.sa2.model.Food;
import com.example.sa2.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food,Integer> {
    List<Food> findByRestaurant(Restaurant restaurant);
    Food findByNameAndRestaurant(String name,Restaurant restaurant);
    List<Food> findByRestaurantAndCategory(Restaurant restaurant,String categ);
    void deleteAllByNameAndRestaurant(String name,Restaurant restaurant);
}
