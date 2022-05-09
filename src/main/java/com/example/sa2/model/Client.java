package com.example.sa2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="client")
@PrimaryKeyJoinColumn(name="clientId")
public class Client extends User{

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"client","restaurant"})
    private List<Order> orders;

    public Client(String username,String password){
        this.username=username;
        this.password=password;
        this.address=" ";
        this.orders=new ArrayList<>();
    }

    public Client() {

    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order o){
        orders.add(o);
    }

    public void removeOrder(Order o){
        orders.remove(o);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
