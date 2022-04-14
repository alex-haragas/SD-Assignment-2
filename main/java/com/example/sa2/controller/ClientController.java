package com.example.sa2.controller;

import com.example.sa2.Service.ClientService;
import com.example.sa2.model.Client;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;


    @PostMapping("/add")
    public ResponseEntity addClient(@RequestBody Client client){
        String hash= BCrypt.hashpw(client.getPassword(),BCrypt.gensalt());
        client.setPassword(hash);
        if(!client.getAddress().equals("") && clientService.addClient(client)) {
            return ResponseEntity.noContent().build();
        }
        else{
            return null;
        }
    }

}
