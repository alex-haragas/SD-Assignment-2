package com.example.sa2.security.services;


import com.example.sa2.model.Admin;
import com.example.sa2.model.Client;
import com.example.sa2.repository.AdminRepository;
import com.example.sa2.repository.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = null;
        Client client = null;
        admin = adminRepository.findByUsername(username);
        if (admin != null) {
            logger.info("admin " + username + " has been found");
            return new UserDetailsImpl(admin);
        } else {
            client = clientRepository.findByUsername(username);
            if (client != null) {
                logger.info("client " + username + " has been found");
                return new UserDetailsImpl(client);
            } else {
                String error = "User Not Found with username: " + username;

                logger.error(error);
                throw new UsernameNotFoundException(error);
            }
        }
    }
}
