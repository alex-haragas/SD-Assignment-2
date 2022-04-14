package com.example.sa2.model;

public class AdminFactory implements userFactory{
    @Override
    public User createUser(String username, String password) {
        return new Admin(username,password);
    }
}
