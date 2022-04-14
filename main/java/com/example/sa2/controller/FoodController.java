package com.example.sa2.controller;

import com.example.sa2.Service.AdminService;
import com.example.sa2.Service.FoodService;
import com.example.sa2.model.Admin;
import com.example.sa2.model.Food;
import com.example.sa2.model.Restaurant;
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

    @GetMapping("/{username}")
    public ResponseEntity<Collection<Food>> getFood(@PathVariable String username){
        System.out.println(username);
        Admin a=adminService.findAdmin(username);
        List<Food> f= foodService.findByRestaurant(a.getRestaurant());
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
                    return ResponseEntity.noContent().build();
                }
                else{
                    return null;
                }
            }
            catch (NumberFormatException e){
                return null;
            }
        }
        return null;
    }
    @PostMapping("search")
    public ResponseEntity<Collection<Food>> findByRestaurantAndCategory(@RequestBody Map<String,String> map){
        Admin a=adminService.findAdmin(map.get("username"));
        List f=foodService.findByRestaurantAndCategory(a.getRestaurant(),map.get("sCateg"));
        if(f.size()==0){
            return null;
        }
        else {
            return new ResponseEntity<>(f, HttpStatus.OK);
        }
    }

}
