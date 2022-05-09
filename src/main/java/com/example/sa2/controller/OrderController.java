package com.example.sa2.controller;

import com.example.sa2.Service.*;
import com.example.sa2.model.*;
import com.example.sa2.security.pwt.AuthTokenFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private FoodService foodService;

    private static final Logger logger = LogManager.getLogger(AuthTokenFilter.class);

    @GetMapping("admin/{username}")
    public ResponseEntity<Collection<Order>> getAdminOrder(@PathVariable("username") String username){
        Admin a=adminService.findAdmin(username);
        System.out.println(a.getRestaurant().getName());
        List<Order> o= orderService.findByRestaurant(a.getRestaurant());
        logger.info("Connection successful");
        if(o.size()==0){
            return null;
        }
        else{
            return new ResponseEntity<>(o, HttpStatus.OK);
        }
    }

    @GetMapping("client/{username}")
    public ResponseEntity<Collection<Order>> getClientOrder(@PathVariable("username") String username){
        Client c=clientService.findClient(username);
        logger.info("Found user");
        List<Order> o=c.getOrders();


        return new ResponseEntity<>(o, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody Map<String,Object> map){
        Restaurant r=restaurantService.findByName((String)map.get("resName"));
        Client c=clientService.findClient((String) map.get("username"));
        System.out.println(map.get("foods"));
        List<String> names= (List<String>) map.get("foods");
        List<Food> foods=new ArrayList<>();
        for(String s:names){
            foods.add(foodService.findByNameAndRestaurant(s,r));
        }
        Order o=new Order(r,c,foods);
        orderService.addOrder(o);
        logger.info("Created new order");
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    @PostMapping("/decline")
    public ResponseEntity<Order> delcineOrder(@RequestBody Order o){
        Order og=orderService.findById(o.getId());
        if(!og.getStatus().equals("DELIVERED")) {
            og.setStatus("DECLINED");
            orderService.modify();
            logger.info("Changed status");
            return new ResponseEntity<>(og, HttpStatus.OK);
        }
        else{
            logger.info("Could not change status");
            return null;
        }
    }

    @PostMapping("/accept")
    public ResponseEntity<Order> acceptOrder(@RequestBody Order o){
        System.out.println(o.getId());
        Order og=orderService.findById(o.getId());
        if(og.getStatus().equals("PENDING")){
            og.setStatus("ACCEPTED");
            logger.info("Changed status");
        }
        else{
            if(og.getStatus().equals("ACCEPTED")){
                og.setStatus("IN DELIVERY");
                logger.info("Changed Status");
            }
            else{
                if(og.getStatus().equals("IN DELIVERY")){
                    og.setStatus("DELIVERED");
                    logger.info("Changed status");
                }
            }
        }
        orderService.modify();
        return new ResponseEntity<>(og,HttpStatus.OK);
    }

}
