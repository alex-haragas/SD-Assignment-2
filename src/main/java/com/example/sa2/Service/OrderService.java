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

    /**
     * Add a new Order
     * @param o the order to be added
     */
    @Transactional
    public void addOrder(Order o){orderRespository.save(o);}

    /**
     * Gets all order from a restaurant
     * @param r the restaurant
     * @return the list of orders
     */
    @Transactional
    public List<Order> findByRestaurant(Restaurant r){return orderRespository.findByRestaurantOrderByStatus(r);}

    /**
     * Gets all client's orders
     * @param c the client
     * @return the list of orders
     */
    @Transactional
    public List<Order> findByClient(Client c){return  orderRespository.findByClient(c);}

    /**
     * Gets all orders that have a certain status
     * @param s the status
     * @return the list of orders
     */
    @Transactional
    public List<Order> findByStatus(String s){return orderRespository.findByStatus(s);}

    /**
     * Gets a certain order
     * @param id the id of the order
     * @return the order
     */
    @Transactional
    public Order findById(int id){return orderRespository.findById(id);}

    /**
     * Save the database
     */
    @Transactional
    public void modify(){orderRespository.flush();}

    public void setOrderRespository(OrderRespository orderRespository) {
        this.orderRespository = orderRespository;
    }
}
