package com.example.sa2;

import com.example.sa2.Service.ClientService;
import com.example.sa2.model.Client;
import com.example.sa2.model.Food;
import com.example.sa2.repository.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    ClientService clientService;
    @Mock
    ClientRepository clientRepository;

    @Before
    public void setup(){
        clientService=new ClientService();
    }
    @Test
    public void checkFind(){
        Client sampleClient=new Client("Alex","12345");
        sampleClient.setAddress("Random");
        clientService.setClientRepository(clientRepository);
        when(clientRepository.findByUsername("Alex")).thenReturn(sampleClient);
        Client c=clientService.findClient("Alex");
        assertEquals("Alex",c.getUsername());
    }


}
