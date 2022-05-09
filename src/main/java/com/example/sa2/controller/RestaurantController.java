package com.example.sa2.controller;

import com.example.sa2.Service.AdminService;
import com.example.sa2.Service.RestaurantService;
import com.example.sa2.model.Admin;
import com.example.sa2.model.Restaurant;
import com.example.sa2.security.pwt.AuthTokenFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RestaurantService restaurantService;

    private static final Logger logger = LogManager.getLogger(AuthTokenFilter.class);


    @GetMapping("/{user}")
    public ResponseEntity<Restaurant> seeRestaurant(@PathVariable String user){
        Admin a = adminService.findAdmin(user);
        logger.info("Connected to the database");
        if(a.getRestaurant()!=null){
            return new ResponseEntity<>(a.getRestaurant(), HttpStatus.OK);
        }
        else {
            return null;
        }
    }

    @PostMapping("/all")
    public ResponseEntity<Collection<Restaurant>> getRestaurants(){
        List<Restaurant> l=restaurantService.allRestaurants();
        logger.info("Connected to the database");
        return new ResponseEntity<>(restaurantService.allRestaurants(),HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteRestaurant(@RequestBody Map<String,String> map) {
        restaurantService.deleteRestaurant(map.get("name"));
        logger.info("Deleted from the database");
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody Map<String,String> map) {
        String name=map.get("name");
        String location=map.get("loc");
        Admin a = adminService.findAdmin(map.get("usr"));
        if(restaurantService.findByName(name)==null && restaurantService.findByAdmin(a)==null){
            logger.info("Added new restaurant");
            restaurantService.addRestaurant(new Restaurant(name,location,a));
        }
        else{
            logger.error("Could not add restaurant");
            return null;
        }
        return ResponseEntity.noContent().build();
    }

}
