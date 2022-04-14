package com.example.sa2.Service;

import com.example.sa2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sa2.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addUser(User u){ userRepository.save(u);}

    @Transactional
    public List<User> findUsers(){return userRepository.findAll();}

    @Transactional
    public User findByUsername(String name){return userRepository.findByUsername(name);}
}
