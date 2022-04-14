package com.example.sa2.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Food> foods;

    public Cart(){
        foods=new ArrayList<>();
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void addItem(Food f){
        foods.add(f);
    }

    public void removeItem(Food f){
        foods.remove(f);
    }
}
