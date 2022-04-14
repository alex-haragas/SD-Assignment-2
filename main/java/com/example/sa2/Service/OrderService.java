package com.example.sa2.Service;

import com.example.sa2.model.Client;
import com.example.sa2.model.Order;
import com.example.sa2.model.Restaurant;
import com.example.sa2.repository.OrderRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRespository orderRespository;

    @Transactional
    public void addOrder(Order o){orderRespository.save(o);}

    @Transactional
    public List<Order> findByRestaurant(Restaurant r){return orderRespository.findByRestaurantOrderByStatus(r);}

    @Transactional
    public List<Order> findByClient(Client c){return  orderRespository.findByClient(c);}

    @Transactional
    public List<Order> findByStatus(String s){return orderRespository.findByStatus(s);}

    @Transactional
    public Order findById(int id){return orderRespository.findById(id);}

    @Transactional
    public void modify(){orderRespository.flush();}


}
