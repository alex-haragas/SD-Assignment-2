package com.example.sa2.Service;

import com.example.sa2.model.Food;
import com.example.sa2.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sa2.repository.FoodRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Transactional
    public boolean addFood(Food f){
        foodRepository.save(f);
        return true;
    }

    @Transactional
    public void deleteByNameAndRestaurant(String name, Restaurant r){
        foodRepository.deleteAllByNameAndRestaurant(name,r);
    }

    public List<Food> findByRestaurant(Restaurant r){
        return foodRepository.findByRestaurant(r);
    }

    public Food findByNameAndRestaurant(String name,Restaurant r){
        return foodRepository.findByNameAndRestaurant(name,r);
    }

   public List<Food> findByRestaurantAndCategory(Restaurant restaurant,String categ){
        return foodRepository.findByRestaurantAndCategory(restaurant,categ);
   }

}
