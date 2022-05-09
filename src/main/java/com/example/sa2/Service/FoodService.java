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

    /**
     * Add food
     * @param f the new food
     * @return true, no checking for identical name
     */
    @Transactional
    public boolean addFood(Food f){
        foodRepository.save(f);
        return true;
    }

    /**
     * Delets a food with a given name from a given restaurant
     * @param name the name of the food
     * @param r the restaurant in with the food is found in the menu
     */
    @Transactional
    public void deleteByNameAndRestaurant(String name, Restaurant r){
        foodRepository.deleteAllByNameAndRestaurant(name,r);
    }


    /**
     * Gives the menu for a restaurant
     * @param r the restaurant
     * @return the list of food
     */
    public List<Food> findByRestaurant(Restaurant r){
        return foodRepository.findByRestaurant(r);
    }

    /**
     * Finds a certain food in the menu of the restaurant
     * @param name the name of the food
     * @param r the restaurant where it searches
     * @return the food
     */
    public Food findByNameAndRestaurant(String name,Restaurant r){
        return foodRepository.findByNameAndRestaurant(name,r);
    }

    /**
     * Gets all food from a certain category
     * @param restaurant the restaurant where it has to look
     * @param categ the name of the category
     * @return the list of foods with that category
     */
   public List<Food> findByRestaurantAndCategory(Restaurant restaurant,String categ){
        return foodRepository.findByRestaurantAndCategory(restaurant,categ);
   }

    public void setFoodRepository(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
}
