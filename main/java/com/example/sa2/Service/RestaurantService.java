package com.example.sa2.Service;

import com.example.sa2.model.Admin;
import com.example.sa2.model.Restaurant;
import com.example.sa2.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    public List<Restaurant> allRestaurants(){return restaurantRepository.findAll();}

    @Transactional
    public void addRestaurant(Restaurant r){restaurantRepository.save(r);}

    @Transactional
    public void deleteRestaurant(String name){restaurantRepository.deleteByName(name);}

    @Transactional
    public Restaurant findByName(String name){return restaurantRepository.findByName(name);}

    @Transactional
    public Restaurant findByAdmin(Admin a){return restaurantRepository.findByAdmin(a);}
}
