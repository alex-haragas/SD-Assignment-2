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

    /**
     * Adds a new client, checks if one with the same username already exists
     * @param c the client you want to add
     * @return if the client was added
     */
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

    /**
     * Finds an admin by name
     * @param name the name of the admin
     * @return the admin found (null if nothing)
     */
    @Transactional
    public Client findClient(String name){return clientRepository.findByUsername(name);}

    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
