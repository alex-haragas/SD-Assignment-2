package com.example.sa2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String status;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnoreProperties({"admin", "orders"})
    private Restaurant restaurant;

    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties({"orders"})

    private Client client;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "orders")
    private List<Food> foods;

    public Order(Restaurant restaurant, Client client, List<Food> foods) {
        this.restaurant = restaurant;
        this.client = client;
        for (Food f : foods) {
            f.addOrder(this);
        }
        this.foods = foods;

        status = "PENDING";
    }

    public Order() {

    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Client getClient() {
        return client;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public int getId(){return id;}
}
