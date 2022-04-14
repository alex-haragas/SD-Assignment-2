package com.example.sa2.Service;

import com.example.sa2.model.Client;
import com.example.sa2.repository.ClientRepository;
import com.example.sa2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public boolean addClient(Client c){
        if(clientRepository.findByUsername(c.getUsername())!=null){;
            return false;
        }
        else{
            clientRepository.save(c);
            return true;
        }
    }

    @Transactional
    public Client findClient(String name){return clientRepository.findByUsername(name);}
}
