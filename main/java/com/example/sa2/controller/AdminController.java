package com.example.sa2.controller;

import com.example.sa2.Service.AdminService;
import com.example.sa2.model.Admin;
import com.example.sa2.model.Client;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;



    @PostMapping("/add")
    public ResponseEntity addAdmin(@RequestBody Admin admin){
        String hash= BCrypt.hashpw(admin.getPassword(),BCrypt.gensalt());
        admin.setPassword(hash);
        if(adminService.addAdmin(admin)) {
            return ResponseEntity.noContent().build();
        }
        else{
            return null;
        }
    }
}
