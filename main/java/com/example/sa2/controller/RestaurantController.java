package com.example.sa2.controller;

import com.example.sa2.Service.AdminService;
import com.example.sa2.Service.RestaurantService;
import com.example.sa2.model.Admin;
import com.example.sa2.model.Restaurant;
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

    @GetMapping("/{user}")
    public ResponseEntity<Restaurant> seeRestaurant(@PathVariable String user){
        Admin a = adminService.findAdmin(user);
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
        return new ResponseEntity<>(restaurantService.allRestaurants(),HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteRestaurant(@RequestBody Map<String,String> map) {
        System.out.println("name");
      //  restaurantService.deleteRestaurant(map.get("name"));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody Map<String,String> map) {
        String name=map.get("name");
        String location=map.get("loc");
        Admin a = adminService.findAdmin(map.get("usr"));
        if(restaurantService.findByName(name)==null && restaurantService.findByAdmin(a)==null){
            restaurantService.addRestaurant(new Restaurant(name,location,a));
        }
        else{
            return null;
        }
        return ResponseEntity.noContent().build();
    }

}
