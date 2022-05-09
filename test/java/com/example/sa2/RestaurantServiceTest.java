package com.example.sa2;

import com.example.sa2.Service.AdminService;
import com.example.sa2.Service.ClientService;
import com.example.sa2.Service.RestaurantService;
import com.example.sa2.model.Admin;
import com.example.sa2.model.Food;
import com.example.sa2.model.Restaurant;
import com.example.sa2.repository.RestaurantRepository;
import com.example.sa2.security.pwt.JwtUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {
    RestaurantService restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;
    @Autowired
    ClientService clientService;
    @Autowired
    AdminService adminService;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

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
