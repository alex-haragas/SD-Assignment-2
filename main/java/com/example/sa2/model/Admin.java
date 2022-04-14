package com.example.sa2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="admin")
@PrimaryKeyJoinColumn(name="adminId")
public class Admin extends User{

    @OneToOne(mappedBy = "admin",fetch = FetchType.LAZY)
    @JsonIgnoreProperties("admin")
    private Restaurant restaurant;

    public Admin(String username,String password,Restaurant restaurant){
        this.username=username;
        this.password=password;
        this.restaurant=restaurant;
    }

    public Admin(String username,String password){
        this.username=username;
        this.password=password;
        this.restaurant=null;
    }

    public Admin() {

    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void removeRestaurant(){
        this.restaurant=null;
    }
}
