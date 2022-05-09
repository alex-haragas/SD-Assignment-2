package com.example.sa2.Service;

import com.example.sa2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.sa2.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class UserService   {
    @Autowired
    private UserRepository userRepository;

    /**
     * Adds a new user (not used)
     * @param u the user
     */
    @Transactional
    public void addUser(User u){ userRepository.save(u);}

    /**
     * Finds all users (not used)
     * @return the list of users
     */
    @Transactional
    public List<User> findUsers(){return userRepository.findAll();}

    /**
     * Finds a user by username
     * @param name the name of the user
     * @return the user
     */
    @Transactional
    public User findByUsername(String name){return userRepository.findByUsername(name);}

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
