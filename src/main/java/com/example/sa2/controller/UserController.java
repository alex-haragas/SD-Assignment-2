package com.example.sa2.controller;

import com.example.sa2.Service.AdminService;
import com.example.sa2.Service.ClientService;
import com.example.sa2.Service.UserService;
import com.example.sa2.model.Admin;
import com.example.sa2.model.Client;
import com.example.sa2.model.User;
import com.example.sa2.security.pwt.AuthTokenFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping ("/logIn")
public class UserController {

    @Autowired
    public UserService userService;
    @Autowired
    public ClientService clientService;
    @Autowired
    public AdminService adminService;

    private static final Logger logger = LogManager.getLogger(AuthTokenFilter.class);


    @GetMapping("/check")
    public ResponseEntity<String> logUser(@RequestBody User u){
        User foundUser=userService.findByUsername(u.getUsername());
        if(foundUser==null){
            logger.error("User not found");
            return null;
        }
        else{
            if(BCrypt.checkpw(u.getPassword(),foundUser.getPassword())){
                Client c=clientService.findClient(foundUser.getUsername());
                if(c==null){
                    Admin a=adminService.findAdmin(foundUser.getUsername());
                    logger.info("Found admin");
                    return new ResponseEntity<>( ("/admins/"+a.getUsername()) ,HttpStatus.OK);
                }
                else{
                    logger.info("Found client");
                    return new ResponseEntity<>( ("/clients/"+c.getUsername()) ,HttpStatus.OK);
                }
            }
        }
        logger.error("Wrong password/username");
        return null;
    }
}
