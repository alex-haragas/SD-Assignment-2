package com.example.sa2.model;

public class ClientFactory implements userFactory{
    @Override
    public User createUser(String username, String password) {
        return new Client(username,password);
    }

    public Client createClient(String username,String password,String address){
        Client c=(Client) this.createUser(username,password);
        c.setAddress(address);
        return c;
    }
}
