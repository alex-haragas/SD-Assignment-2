package com.example.sa2.controller;

import com.example.sa2.Service.AdminService;
import com.example.sa2.Service.ClientService;
import com.example.sa2.Service.UserService;
import com.example.sa2.model.Admin;
import com.example.sa2.model.Client;
import com.example.sa2.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URISyntaxException;


@RestController
@RequestMapping ("/logIn")
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    public ClientService clientService;
    @Autowired
    public AdminService adminService;

    @PostMapping("/check")
    public ResponseEntity<String> logUser(@RequestBody User u){
        User foundUser=userService.findByUsername(u.getUsername());
        if(foundUser==null){
            return null;
        }
        else{
            if(BCrypt.checkpw(u.getPassword(),foundUser.getPassword())){
                Client c=clientService.findClient(foundUser.getUsername());
                if(c==null){
                    Admin a=adminService.findAdmin(foundUser.getUsername());
                    return new ResponseEntity<>( ("/admins/"+a.getUsername()) ,HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<>( ("/clients/"+c.getUsername()) ,HttpStatus.OK);
                }
            }
        }
        return null;
    }
}
