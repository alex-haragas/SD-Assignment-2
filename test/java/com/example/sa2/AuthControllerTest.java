package com.example.sa2;

import com.example.sa2.Service.AdminService;
import com.example.sa2.Service.ClientService;
import com.example.sa2.controller.AuthController;
import com.example.sa2.model.Client;
import com.example.sa2.model.User;
import com.example.sa2.security.pwt.JwtUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class AuthControllerTest {
    AuthController authController;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    ClientService clientService;
    @Mock
    AdminService adminService;

    @Mock
    PasswordEncoder encoder;
    @Mock
    JwtUtils jwtUtils;
    
    @Before
    public void setup(){
        authController=new AuthController();
    }
    
    @Test
    public void testSingUpClient(){
        Client sampleClient=new Client("Alex","12345");
        authController.setClientService(clientService);
        authController.setAdminService(adminService);
        authController.setEncoder(encoder);
        authController.setAuthenticationManager(authenticationManager);
        authController.setJwtUtils(jwtUtils);

        when(clientService.addClient(sampleClient)).thenReturn(true);
        authController.registerClient(sampleClient);
    }

    @Test(expected = Exception.class)
    public void testSingUpClientError(){
        Client sampleClient=new Client("Alex","12345");
        authController.setClientService(clientService);
        authController.setAdminService(adminService);
        authController.setEncoder(encoder);
        authController.setAuthenticationManager(authenticationManager);
        authController.setJwtUtils(jwtUtils);
        Exception e=new Exception();
        when(clientService.addClient(sampleClient)).thenThrow(e);
        authController.registerClient(sampleClient);
    }
}
