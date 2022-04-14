package com.example.sa2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @OneToOne
    @JoinColumn(name="admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @OrderBy("category")
    private List<Food> foods;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"restaurant","client"})
    @OrderBy("status")
    private List<Order> orders;

    public Restaurant(String name, String location, Admin admin) {
        this.name = name;
        this.location = location;
        this.admin = admin;
        orders=new ArrayList<>();
        foods=new ArrayList<>();
    }

    public Restaurant() {

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Admin getAdmin() {
        return admin;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order o){
        orders.add(o);
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public void addFood(Food f){
        foods.add(f);
    }

    public void removeFood(Food f){
        foods.remove(f);
    }
}
