package com.example.sa2;

import com.example.sa2.Service.ClientService;
import com.example.sa2.Service.RestaurantService;
import com.example.sa2.model.Admin;
import com.example.sa2.model.Food;
import com.example.sa2.model.Restaurant;
import com.example.sa2.repository.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RestaurantServiceTest {
    RestaurantService restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;

    @Before
    public void setup(){
        restaurantService=new RestaurantService();
    }
    @Test
    public void checkAdmin(){
        Admin sampleAdmin=new Admin("admin","Admin");
        Restaurant sampleRestaurant=new Restaurant("Name","loc",sampleAdmin);

        restaurantService.setRestaurantRepository(restaurantRepository);
        when(restaurantRepository.findByAdmin(sampleAdmin)).thenReturn(sampleRestaurant);

        Restaurant r=restaurantService.findByAdmin(sampleAdmin);
        assertEquals(r.getAdmin(),sampleRestaurant.getAdmin());
    }

}
