package com.example.sa2.controller;

import com.example.sa2.Service.AdminService;
import com.example.sa2.Service.FoodService;
import com.example.sa2.model.Admin;
import com.example.sa2.model.Food;
import com.example.sa2.model.Restaurant;
import com.example.sa2.security.pwt.AuthTokenFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private AdminService adminService;

    private static final Logger logger = LogManager.getLogger(AuthTokenFilter.class);

    @GetMapping("/{username}")
    public ResponseEntity<Collection<Food>> getFood(@PathVariable String username){
        System.out.println(username);
        Admin a=adminService.findAdmin(username);
        List<Food> f= foodService.findByRestaurant(a.getRestaurant());
        logger.info("Taken info from database");
        if(f.size()==0){
            return null;
        }
        else{
            return new ResponseEntity<>(f, HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addFood(@RequestBody Map<String,String> map){
        Admin a=adminService.findAdmin(map.get("username"));
        if(foodService.findByNameAndRestaurant(map.get("name"),a.getRestaurant())==null){
            try{
                Double price=Double.parseDouble(map.get("price"));
                Food f=new Food(map.get("name"),map.get("description"),price,map.get("category"),a.getRestaurant());
                if (foodService.addFood(f)) {
                    logger.info("Added new food");
                    return ResponseEntity.noContent().build();
                }
                else{
                    logger.error("Could not add food");
                    return null;
                }
            }
            catch (NumberFormatException e){
                logger.fatal("Invalid number format");
                return null;
            }
        }
        return null;
    }
    @PostMapping("search")
    public ResponseEntity<Collection<Food>> findByRestaurantAndCategory(@RequestBody Map<String,String> map){
        Admin a=adminService.findAdmin(map.get("username"));
        List f=foodService.findByRestaurantAndCategory(a.getRestaurant(),map.get("sCateg"));
        logger.info("Connection successful");
        if(f.size()==0){
            return null;
        }
        else {
            return new ResponseEntity<>(f, HttpStatus.OK);
        }
    }

}
